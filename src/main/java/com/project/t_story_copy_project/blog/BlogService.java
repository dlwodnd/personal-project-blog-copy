package com.project.t_story_copy_project.blog;

import com.project.t_story_copy_project.blog.models.dto.BlogModifyDto;
import com.project.t_story_copy_project.blog.models.dto.BlogRegisterDto;
import com.project.t_story_copy_project.blog.models.vo.BlogInfoVo;
import com.project.t_story_copy_project.commom.entity.BlogEntity;
import com.project.t_story_copy_project.commom.entity.UserEntity;
import com.project.t_story_copy_project.commom.exception.BlogErrorCode;
import com.project.t_story_copy_project.commom.exception.CustomException;
import com.project.t_story_copy_project.commom.exception.UserErrorCode;
import com.project.t_story_copy_project.commom.repository.BlogRepository;
import com.project.t_story_copy_project.commom.repository.CatRepository;
import com.project.t_story_copy_project.commom.repository.UserRepository;
import com.project.t_story_copy_project.commom.utils.MyFileUtils;
import com.project.t_story_copy_project.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlogService {
    private final AuthenticationFacade authenticationFacade;
    private final MyFileUtils myFileUtils;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CatRepository catRepository;
    @Transactional
    public BlogInfoVo registerBlog(BlogRegisterDto dto, MultipartFile blogProfileImg){
        log.info("getLoginUserPk : {}", authenticationFacade.getLoginUserPk());
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk())
                .orElseThrow(() -> new CustomException(UserErrorCode.NOT_FOUND_USER));
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
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk())
                .orElseThrow(() -> new CustomException(UserErrorCode.NOT_FOUND_USER));
        BlogEntity blogEntity = blogRepository.findById(dto.getBlogPk())
                .orElseThrow(() -> new CustomException(BlogErrorCode.NOT_FOUND_BLOG));
        if (!blogEntity.getUserEntity().equals(userEntity)){
            throw new CustomException(BlogErrorCode.NOT_MATCH_USER);
        }
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
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk())
                .orElseThrow(() -> new CustomException(UserErrorCode.NOT_FOUND_USER));
        BlogEntity blogEntity = blogRepository.findById(blogPk)
                .orElseThrow(() -> new CustomException(BlogErrorCode.NOT_FOUND_BLOG));
        if (!blogEntity.getUserEntity().equals(userEntity)){
            throw new CustomException(BlogErrorCode.NOT_MATCH_USER);
        }
        blogRepository.delete(blogEntity);
    }

}
