package com.project.t_story_copy_project.feed;

import com.project.t_story_copy_project.commom.Const;
import com.project.t_story_copy_project.commom.ResVo;
import com.project.t_story_copy_project.commom.entity.*;
import com.project.t_story_copy_project.commom.entity.embeddable.FeedFavComposite;
import com.project.t_story_copy_project.commom.exception.BlogErrorCode;
import com.project.t_story_copy_project.commom.exception.CustomException;
import com.project.t_story_copy_project.commom.exception.FeedErrorCode;
import com.project.t_story_copy_project.commom.exception.UserErrorCode;
import com.project.t_story_copy_project.commom.repository.*;
import com.project.t_story_copy_project.commom.utils.MyFileUtils;
import com.project.t_story_copy_project.feed.models.dto.CmtDelGuestDto;
import com.project.t_story_copy_project.feed.models.dto.FeedCmtInsDto;
import com.project.t_story_copy_project.feed.models.dto.FeedCmtPutDto;
import com.project.t_story_copy_project.feed.models.dto.FeedInsDto;
import com.project.t_story_copy_project.feed.models.vo.*;
import com.project.t_story_copy_project.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;
    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CatRepository catRepository;
    private final FeedFavRepository feedFavRepository;
    private final FeedCmtRepository feedCmtRepository;
    private final HashtagRepository hashtagRepository;
    private final FeedPicsRepository feedPicsRepository;
    private final BlogSubRepository blogSubRepository;

    //카테고리 리스트 불러오기
    public CatVo getCategory(long blogPk){
        BlogEntity blogEntity = checkUserBlog(blogPk);

        List<CatFeedInfoVo> catFeedInfoVoList = blogEntity.getCatEntityList().stream()
                .map(catEntity -> {
                    long feedCount = feedRepository.countByCatEntity(catEntity);
                    if (!catEntity.getCatEntityList().isEmpty()){
                        feedCount = feedCount + catEntity.getCatEntityList().stream()
                                .mapToLong(feedRepository::countByCatEntity)
                                .sum();
                    }
                    return CatFeedInfoVo.builder()
                            .catPk(catEntity.getCatPk())
                            .catName(catEntity.getCatNm())
                            .feedCount(feedCount)
                            .catSubInfoVoList(catEntity.getCatEntityList() == null || catEntity.getCatEntityList().isEmpty() ? null :
                                    catEntity.getCatEntityList().stream()
                                            .map(subCatEntity -> {
                                                long subFeedCount = feedRepository.countByCatEntity(subCatEntity);
                                                return CatSubFeedInfoVo.builder()
                                                        .catPk(subCatEntity.getCatPk())
                                                        .catName(subCatEntity.getCatNm())
                                                        .topSeq(subCatEntity.getCatEntity().getSeq())
                                                        .feedCount(subFeedCount)
                                                        .build();
                                            })
                                            .toList()
                            ).build();
                })
                .toList();
        return CatVo.builder()
                .catAll("전체")
                .feedCount(feedRepository.countAllByBlogEntity(blogEntity))
                .catFeedInfoVoList(catFeedInfoVoList)
                .build();
    }
    //카테고리 이름 pk 불러오기
    public List<CatSimpleVo> getCategoryList(Long blogPk){
        BlogEntity blogEntity = checkUserBlog(blogPk);
        return blogEntity.getCatEntityList().stream()
                .map(catEntity -> CatSimpleVo.builder()
                        .catPk(catEntity.getCatPk())
                        .catName(catEntity.getCatNm())
                        .build())
                .toList();
    }
    //비어있는 피드 생성
    public ResVo getEmptyFeed(Long blogPk){
        BlogEntity blogEntity = checkUserBlog(blogPk);
        if (feedRepository.findAllByComplete(0L).isPresent()){
            return new ResVo(0L);
        }
        else {
            FeedEntity feedEntity = FeedEntity.builder()
                    .blogEntity(blogEntity)
                    .build();
            feedRepository.save(feedEntity);
            return new ResVo(feedEntity.getFeedPk());
        }
    }

    //피드 삭제
    public ResVo deleteFeed(long feedPk, long blogPk){
        BlogEntity blogEntity = checkUserBlog(blogPk);
        FeedEntity feedEntity = checkUserFeed(feedPk,blogEntity.getBlogPk());
        String targetPath = "/feed/" + feedEntity.getFeedPk();
        myFileUtils.delFiles(targetPath);
        feedRepository.delete(feedEntity);
        return new ResVo(1L);
    }
    //피드 사진 등록
    public FeedPicInfoVo postFeedPic(Long feedPk, MultipartFile pic){
        FeedEntity feedEntity = feedRepository.findById(feedPk)
                .orElseThrow(() -> new CustomException(FeedErrorCode.NOT_FOUND_FEED));
        String targetPath = "feed/" + feedEntity.getFeedPk();
        String saveFileName = myFileUtils.transferTo(pic, targetPath);
        FeedPicsEntity feedPicsEntity = FeedPicsEntity.builder()
                .feedEntity(feedEntity)
                .feedPic(saveFileName)
                .build();
        feedPicsRepository.save(feedPicsEntity);

        return FeedPicInfoVo.builder()
                .feedPicPk(feedPicsEntity.getFeedPicsPk())
                .picName(saveFileName)
                .build();
    }
    //피드 사진 삭제
    public ResVo deleteFeedPic(long feedPicPk){
        FeedPicsEntity feedPicsEntity = feedPicsRepository.findById(feedPicPk)
                .orElseThrow(() -> new CustomException(FeedErrorCode.NOT_FOUND_FEED_PIC));
        String targetPath = "/feed/" + feedPicsEntity.getFeedEntity().getFeedPk();
        myFileUtils.delFile(targetPath, feedPicsEntity.getFeedPic());
        feedPicsRepository.delete(feedPicsEntity);
        return new ResVo(1L);
    }
    //피드 수정
    public ResVo putFeed(FeedInsDto dto){
        BlogEntity blogEntity = checkUserBlog(dto.getBlogPk());
        FeedEntity feedEntity = checkUserFeed(dto.getFeedPk(), blogEntity.getBlogPk());
        CatEntity catEntity = catRepository.findById(dto.getCatPk())
                .orElseThrow(() -> new CustomException(FeedErrorCode.NOT_FOUND_CAT));
        feedEntity.editFeedEntity(dto,catEntity);
        feedRepository.save(feedEntity);
        List<HashTagEntity> hashTagEntityList = new ArrayList<>();
        for(HashTagInfoVo hashTag : dto.getHashTagList()){
            HashTagEntity hashtagEntity = HashTagEntity.builder()
                    .hashTagPk(hashTag.getHashTagPk())
                    .feedEntity(feedEntity)
                    .hashTagNm(hashTag.getHashTagName())
                    .build();
            hashTagEntityList.add(hashtagEntity);
        }
        feedEntity.modifyHashTagEntityList(hashTagEntityList);
        return new ResVo(1L);
    }
    //피드 좋아요
    public ResVo patchFeedFav(long feedPk){
        FeedEntity feedEntity = feedRepository.findById(feedPk)
                .orElseThrow(() -> new CustomException(FeedErrorCode.NOT_FOUND_FEED));
        UserEntity userEntity = checkUser();
        FeedFavComposite feedFavComposite = FeedFavComposite.builder()
                .feedPk(feedEntity.getFeedPk())
                .userPk(userEntity.getUserPk())
                .build();
        FeedFavEntity feedFavEntity = feedFavRepository.findById(feedFavComposite)
                .orElse(null);
        if (feedFavEntity == null){
            feedFavEntity = FeedFavEntity.builder()
                    .feedFavComposite(feedFavComposite)
                    .feedEntity(feedEntity)
                    .userEntity(checkUser())
                    .build();
            feedFavRepository.save(feedFavEntity);
            return new ResVo(1L);
        }
        else {
            feedFavRepository.delete(feedFavEntity);
            return new ResVo(2L);
        }

    }
    //피드 댓글 등록
    public ResVo postFeedCmt(FeedCmtInsDto dto) {
        FeedEntity feedEntity = feedRepository.findById(dto.getFeedPk())
                .orElseThrow(() -> new CustomException(FeedErrorCode.NOT_FOUND_FEED));
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk())
                .orElse(null);
        if (userEntity == null){
            if (feedEntity.getBlogEntity().getCmtOnlyLogin() == 1){
                throw new CustomException(FeedErrorCode.NEED_LOGIN);
            }
            if (dto.getCmtNm() == null || dto.getCmtPw() == null){
                throw new CustomException(FeedErrorCode.GUEST_USER_NEED_ID_AND_PW);
            }
            else {
                FeedCommentEntity feedCommentEntity = FeedCommentEntity.builder()
                        .feedEntity(feedEntity)
                        .cmtPw(dto.getCmtPw())
                        .cmtNm(dto.getCmtNm())
                        .cmt(dto.getCmt())
                        .build();
                feedCmtRepository.save(feedCommentEntity);
                return new ResVo(1L);
            }
        }
        else {
            FeedCommentEntity feedCommentEntity = FeedCommentEntity.builder()
                    .feedEntity(feedEntity)
                    .userEntity(userEntity)
                    .cmt(dto.getCmt())
                    .cmtPrivate(dto.getCmtPrivate())
                    .build();
            feedCmtRepository.save(feedCommentEntity);
            return new ResVo(1L);
        }
    }
    //피드 댓글 수정
    public ResVo putFeedCmt(FeedCmtPutDto dto){
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk())
                .orElse(null);
        FeedCommentEntity feedCommentEntity = feedCmtRepository.findById(dto.getCmtPk())
                .orElseThrow(() -> new CustomException(FeedErrorCode.NOT_FOUND_FEED_CMT));
        if (userEntity == null){
            if (!feedCommentEntity.getCmtPw().equals(dto.getCmtPw())){
                throw new CustomException(FeedErrorCode.MISS_MATCH_PW);
            }
        }
        feedCommentEntity.modifyFeedComment(dto);
        feedCmtRepository.save(feedCommentEntity);
        return new ResVo(1L);

    }

    public ResVo deleteFeedCmt(long cmtPk , String cmtPw){
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk())
                .orElse(null);
        FeedCommentEntity feedCommentEntity = feedCmtRepository.findById(cmtPk)
                .orElseThrow(() -> new CustomException(FeedErrorCode.NOT_FOUND_FEED_CMT));

        if (userEntity == null){
            if (!feedCommentEntity.getCmtPw().equals(cmtPw)){
                throw new CustomException(FeedErrorCode.MISS_MATCH_PW);
            }
        }
        feedCmtRepository.delete(feedCommentEntity);

        return new ResVo(1L);
    }

    //피드 리스트
    public FeedSimpleInfoVoList getFeedAll (Pageable pageable){
        Page<FeedEntity> feedEntities = feedRepository.findAllByCompleteAndFeedPrivateOrderByCreatedAtDesc(Const.FEED_POST_COMPLETE, Const.PUBLIC,pageable);

        return FeedSimpleInfoVoList.builder()
                .maxPage((long) feedEntities.getTotalPages())
                .hasNext(feedEntities.hasNext())
                .hasPrevious(feedEntities.hasPrevious())
                .feedSimpleInfoVoList(feedEntities.getContent().stream().map(
                        feedEntity -> {
                            String html = feedEntity.getContents();
                            Document doc = Jsoup.parse(html);
                            String contents = doc.body().text();
                            return FeedSimpleInfoVo.builder()
                                .feedPk(feedEntity.getFeedPk())
                                .feedTitle(feedEntity.getTitle())
                                .feedContent(contents)
                                .feedImg(feedEntity.getFeedPicsEntityList().isEmpty() ? null :
                                        feedEntity.getFeedPicsEntityList().get(0).getFeedPic())
                                .favCount(feedFavRepository.countByFeedEntity(feedEntity))
                                .cmtCount(feedCmtRepository.countByFeedEntity(feedEntity))
                                .createdAt(feedEntity.getCreatedAt().toString())
                                .hashTagList(hashtagRepository.findTop3ByFeedEntity(feedEntity).stream()
                                        .map(HashTagEntity::getHashTagNm).toList())
                                .blogPk(feedEntity.getBlogEntity().getBlogPk())
                                .blogTitle(feedEntity.getBlogEntity().getBlogTitle())
                                .blogImg(feedEntity.getBlogEntity().getBlogPic())
                                .build();}
                ).toList(
                )).build();

    }
    //구독한 블로그의 피드 리스트
    public FeedSimpleInfoVoList getFeedSub(Pageable pageable){
        UserEntity userEntity = checkUser();
        List<BlogEntity> blogEntityList = blogSubRepository.findAllByUserEntity(userEntity).stream().map(BlogSubEntity::getBlogEntity).toList();
        Page<FeedEntity> feedEntities = feedRepository.findAllByCompleteAndFeedPrivateAndBlogEntityInOrderByCreatedAtDesc(Const.FEED_POST_COMPLETE, Const.PUBLIC, blogEntityList, pageable);
        return FeedSimpleInfoVoList.builder()
                .maxPage((long) feedEntities.getTotalPages())
                .hasNext(feedEntities.hasNext())
                .hasPrevious(feedEntities.hasPrevious())
                .feedSimpleInfoVoList(feedEntities.getContent().stream().map(
                        feedEntity -> {
                            String html = feedEntity.getContents();
                            Document doc = Jsoup.parse(html);
                            String contents = doc.body().text();
                            return FeedSimpleInfoVo.builder()
                                    .feedPk(feedEntity.getFeedPk())
                                    .feedTitle(feedEntity.getTitle())
                                    .feedContent(contents)
                                    .feedImg(feedEntity.getFeedPicsEntityList().isEmpty() ? null :
                                            feedEntity.getFeedPicsEntityList().get(0).getFeedPic())
                                    .favCount(feedFavRepository.countByFeedEntity(feedEntity))
                                    .cmtCount(feedCmtRepository.countByFeedEntity(feedEntity))
                                    .createdAt(feedEntity.getCreatedAt().toString())
                                    .hashTagList(hashtagRepository.findTop3ByFeedEntity(feedEntity).stream()
                                            .map(HashTagEntity::getHashTagNm).toList())
                                    .blogPk(feedEntity.getBlogEntity().getBlogPk())
                                    .blogTitle(feedEntity.getBlogEntity().getBlogTitle())
                                    .blogImg(feedEntity.getBlogEntity().getBlogPic())
                                    .build();}
                ).toList(
                )).build();


    }



//===============================================================================================================================
    private FeedEntity checkUserFeed(long feedPk, long blogPk){
        FeedEntity feedEntity = feedRepository.findById(feedPk)
                .orElseThrow(() -> new CustomException(FeedErrorCode.NOT_FOUND_FEED));
        if (!feedEntity.getBlogEntity().getBlogPk().equals(blogPk)){
            throw new CustomException(BlogErrorCode.NOT_MATCH_USER);
        }
        return feedEntity;
    }

    private BlogEntity checkUserBlog(long blogPk){
        BlogEntity blogEntity = blogRepository.findById(blogPk)
                .orElseThrow(() -> new CustomException(BlogErrorCode.NOT_FOUND_BLOG));
        if (!blogEntity.getUserEntity().equals(checkUser())){
            throw new CustomException(BlogErrorCode.NOT_MATCH_USER);
        }
        return blogEntity;
    }

    private UserEntity checkUser(){
        return userRepository.findById(authenticationFacade.getLoginUserPk())
                .orElseThrow(() -> new CustomException(UserErrorCode.NOT_FOUND_USER));
    }



}
