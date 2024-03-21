package com.project.t_story_copy_project.feed;

import com.project.t_story_copy_project.blog.models.vo.CatInfoVo;
import com.project.t_story_copy_project.commom.ResVo;
import com.project.t_story_copy_project.commom.entity.*;
import com.project.t_story_copy_project.commom.entity.embeddable.FeedFavComposite;
import com.project.t_story_copy_project.commom.exception.BlogErrorCode;
import com.project.t_story_copy_project.commom.exception.CustomException;
import com.project.t_story_copy_project.commom.exception.FeedErrorCode;
import com.project.t_story_copy_project.commom.exception.UserErrorCode;
import com.project.t_story_copy_project.commom.repository.*;
import com.project.t_story_copy_project.commom.utils.MyFileUtils;
import com.project.t_story_copy_project.feed.models.dto.FeedInsDto;
import com.project.t_story_copy_project.feed.models.vo.*;
import com.project.t_story_copy_project.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
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

    //카테고리 리스트 불러오기
    public List<CatFeedInfoVo> getCategory(long blogPk){
        BlogEntity blogEntity = checkUserBlog(blogPk);

        return blogEntity.getCatEntityList().stream()
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
