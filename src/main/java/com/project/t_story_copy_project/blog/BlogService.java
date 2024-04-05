package com.project.t_story_copy_project.blog;

import com.project.t_story_copy_project.blog.models.dto.BlogModifyDto;
import com.project.t_story_copy_project.blog.models.dto.BlogRegisterDto;
import com.project.t_story_copy_project.blog.models.dto.CatInfoDto;
import com.project.t_story_copy_project.blog.models.dto.CatInsDto;
import com.project.t_story_copy_project.blog.models.vo.BlogInfoVo;
import com.project.t_story_copy_project.blog.models.vo.CatInfoVo;
import com.project.t_story_copy_project.blog.models.vo.CatSubInfoVo;
import com.project.t_story_copy_project.commom.ResVo;
import com.project.t_story_copy_project.commom.entity.BlogEntity;
import com.project.t_story_copy_project.commom.entity.BlogSubEntity;
import com.project.t_story_copy_project.commom.entity.CatEntity;
import com.project.t_story_copy_project.commom.entity.UserEntity;
import com.project.t_story_copy_project.commom.entity.embeddable.BlogSubComposite;
import com.project.t_story_copy_project.commom.exception.BlogErrorCode;
import com.project.t_story_copy_project.commom.exception.CatErrorCode;
import com.project.t_story_copy_project.commom.exception.CustomException;
import com.project.t_story_copy_project.commom.exception.UserErrorCode;
import com.project.t_story_copy_project.commom.repository.BlogRepository;
import com.project.t_story_copy_project.commom.repository.BlogSubRepository;
import com.project.t_story_copy_project.commom.repository.CatRepository;
import com.project.t_story_copy_project.commom.repository.UserRepository;
import com.project.t_story_copy_project.commom.utils.MyFileUtils;
import com.project.t_story_copy_project.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogService {
    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CatRepository catRepository;
    private final BlogSubRepository blogSubRepository;
    @Transactional
    public BlogInfoVo registerBlog(BlogRegisterDto dto, MultipartFile blogProfileImg){
        log.info("getLoginUserPk : {}", authenticationFacade.getLoginUserPk());
        UserEntity userEntity = checkUser();
        BlogEntity blogEntity = BlogEntity.builder()
                .userEntity(userEntity)
                .blogTitle(dto.getBlogTitle())
                .blogAddress(dto.getBlogAddress())
                .blogNickname(dto.getBlogNickname())
                .build();
        blogRepository.saveAndFlush(blogEntity);
        String target = "/blog/" + blogEntity.getBlogPk();
        String fileNm = myFileUtils.transferTo(blogProfileImg, target);
        blogEntity.changeBlogPic(fileNm);

        return BlogInfoVo.builder()
                .blogPk(blogEntity.getBlogPk())
                .blogInfo(blogEntity.getBlogInfo())
                .blogTitle(blogEntity.getBlogTitle())
                .blogAddress(blogEntity.getBlogAddress())
                .blogPic(blogEntity.getBlogPic())
                .blogNickname(blogEntity.getBlogNickname())
                .build();
    }
    @Transactional
    public BlogInfoVo modifyBlog(BlogModifyDto dto) {
        BlogEntity blogEntity = checkUserBlog(dto.getBlogPk());
        blogEntity.modifyBlogInfo(dto);

        return BlogInfoVo.builder()
                .blogPk(blogEntity.getBlogPk())
                .blogInfo(blogEntity.getBlogInfo())
                .blogTitle(blogEntity.getBlogTitle())
                .blogAddress(blogEntity.getBlogAddress())
                .blogPic(blogEntity.getBlogPic())
                .blogNickname(blogEntity.getBlogNickname())
                .build();
    }

    @Transactional
    public void closeBlog(long blogPk){
        BlogEntity blogEntity = checkUserBlog(blogPk);
        blogRepository.delete(blogEntity);
    }

    @Transactional(readOnly = true)
    public List<CatInfoVo> registerCategory(CatInsDto dto){

        BlogEntity blogEntity = checkUserBlog(dto.getBlogPk());
        CatEntity topCatEntity = null;
        List<CatEntity> catEntityList = new ArrayList<>();
        for(CatInfoDto catInfo : dto.getCatInfoList()){
            topCatEntity = null;
            if (catInfo.getTopCatSeq() != null && catInfo.getTopCatSeq() != 0){
                topCatEntity = catRepository.findBySeq(catInfo.getTopCatSeq())
                        .orElseThrow(() -> new CustomException(CatErrorCode.NOT_FOUND_TOP_CAT));
                if (topCatEntity.getCatEntity() != null){
                    throw new CustomException(CatErrorCode.ALREADY_SUB_CAT);
                }
            }
            if (catInfo.getCatPk() != null && catInfo.getCatPk() != 0){
                CatEntity catEntity = catRepository.findById(catInfo.getCatPk())
                        .orElseThrow(() -> new CustomException(CatErrorCode.NOT_FOUND_CAT));
                if (!catEntity.getBlogEntity().equals(blogEntity)){
                    throw new CustomException(CatErrorCode.NOT_MATCH_BLOG);
                }
                catEntity.editCatEntity(catInfo, topCatEntity);
                catEntityList.add(catEntity);
            }

        }
        blogEntity.modifyCatEntityList(catEntityList);
        blogRepository.saveAndFlush(blogEntity);

        for(CatInfoDto catInfo : dto.getCatInfoList()){
            topCatEntity = null;
            if (catInfo.getCatPk() != null && catInfo.getCatPk() != 0){
                continue;
            }
            if (catInfo.getTopCatSeq() != null && catInfo.getTopCatSeq() != 0){
                topCatEntity = catRepository.findBySeq(catInfo.getTopCatSeq())
                        .orElseThrow(() -> new CustomException(CatErrorCode.NOT_FOUND_TOP_CAT));
                if (topCatEntity.getCatEntity() != null){
                    throw new CustomException(CatErrorCode.ALREADY_SUB_CAT);
                }
            }
            CatEntity existingCatEntity = catRepository.findByCatEntityAndCatOrder(topCatEntity, catInfo.getCatOrder());
            if (existingCatEntity != null) {
                throw new CustomException(CatErrorCode.DUPLICATE_CAT_ORDER);
            }
            CatEntity catEntity = CatEntity.builder()
                    .blogEntity(blogEntity)
                    .seq(catInfo.getCatSeq())
                    .catNm(catInfo.getCatName())
                    .catEntity(topCatEntity)
                    .catOrder(catInfo.getCatOrder())
                    .build();
            catEntityList.add(catEntity);
        }
        catRepository.saveAll(catEntityList);



        return catEntityList.stream()
                .filter(catEntity -> catEntity.getCatEntity() == null)
                .map(catEntity -> CatInfoVo.builder()
                        .catPk(catEntity.getCatPk())
                        .catName(catEntity.getCatNm())
                        .catOrder(catEntity.getCatOrder())
                        .catSeq(catEntity.getSeq())
                        .catSubInfoVoList(catEntity.getCatEntityList() == null || catEntity.getCatEntityList().isEmpty() ? null :
                                catEntity.getCatEntityList().stream()
                                .map(catSubEntity -> CatSubInfoVo.builder()
                                        .catPk(catSubEntity.getCatPk())
                                        .catName(catSubEntity.getCatNm())
                                        .catOrder(catSubEntity.getCatOrder())
                                        .catSeq(catSubEntity.getSeq())
                                        .topSeq(catSubEntity.getCatEntity().getSeq())
                                        .build())
                                .toList())
                        .build())
                .toList();
    }
    public Long cmtAccess(long blogPk){
        BlogEntity blogEntity = checkUserBlog(blogPk);
        return blogEntity.cmtAccess();
    }

    //블로그 구독
    public ResVo subscribe(long blogPk){
        BlogEntity blogEntity = blogRepository.findById(blogPk)
                .orElseThrow(() -> new CustomException(BlogErrorCode.NOT_FOUND_BLOG));
        UserEntity userEntity = checkUser();
        BlogSubComposite blogSubComposite = BlogSubComposite.builder()
                .blogPk(blogPk)
                .userPk(userEntity.getUserPk())
                .build();
        if (blogSubRepository.findById(blogSubComposite).isPresent()){
            blogSubRepository.deleteById(blogSubComposite);
            return new ResVo(2L);
        }
        else {
            BlogSubEntity blogSubEntity = BlogSubEntity.builder()
                    .blogSubComposite(blogSubComposite)
                    .blogEntity(blogEntity)
                    .userEntity(userEntity)
                    .build();
            blogSubRepository.save(blogSubEntity);
            return new ResVo(1L);
        }
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
