package com.project.t_story_copy_project.blog;

import com.project.t_story_copy_project.blog.models.dto.BlogModifyDto;
import com.project.t_story_copy_project.blog.models.dto.BlogRegisterDto;
import com.project.t_story_copy_project.blog.models.dto.CatInfoDto;
import com.project.t_story_copy_project.blog.models.dto.CatInsDto;
import com.project.t_story_copy_project.blog.models.vo.BlogInfoVo;
import com.project.t_story_copy_project.blog.models.vo.CatInfoVo;
import com.project.t_story_copy_project.commom.exception.CustomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;

    //블로그 등록
    @PostMapping
    public ResponseEntity<CustomResponse<BlogInfoVo>> registerBlog(
            @RequestPart @Valid BlogRegisterDto dto
            ,@RequestPart MultipartFile blogThumbnail){


        return ResponseEntity.ok(new CustomResponse<>(blogService.registerBlog(dto,blogThumbnail)));
    }
    //블로그 정보 수정
    @PutMapping
    public ResponseEntity<CustomResponse<BlogInfoVo>> modifyBlog(
            @RequestPart @Valid BlogModifyDto dto){
        return ResponseEntity.ok(new CustomResponse<>(blogService.modifyBlog(dto)));
    }
    //블로그 삭제
    @DeleteMapping("/{blogPk}")
    public ResponseEntity<CustomResponse<String>> closeBlog(@PathVariable long blogPk){
        blogService.closeBlog(blogPk);
        return ResponseEntity.ok(new CustomResponse<>("블로그 삭제 성공"));
    }
    //카테고리 등록
    @PostMapping("/category")
    public ResponseEntity<CustomResponse<List<CatInfoVo>>> registerCategory(@RequestBody CatInsDto dto){
        long start = System.currentTimeMillis();
        List<CatInfoVo> vo = blogService.registerCategory(dto);
        long end = System.currentTimeMillis();
        System.out.println("실행 시간 : " + ( end - start )/1000.0 );
        return ResponseEntity.ok(new CustomResponse<>(vo));
    }
    //댓글 허용 설정 토글
    @PatchMapping("/cmtAccess")
    public ResponseEntity<CustomResponse<Long>> cmtAccess(@RequestParam long blogPk){
        return ResponseEntity.ok(new CustomResponse<>(blogService.cmtAccess(blogPk)));
    }
}
