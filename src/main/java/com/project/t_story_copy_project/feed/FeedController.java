package com.project.t_story_copy_project.feed;

import com.project.t_story_copy_project.blog.models.vo.CatInfoVo;
import com.project.t_story_copy_project.commom.ResVo;
import com.project.t_story_copy_project.commom.exception.CustomResponse;
import com.project.t_story_copy_project.feed.models.dto.CmtDelGuestDto;
import com.project.t_story_copy_project.feed.models.dto.FeedCmtInsDto;
import com.project.t_story_copy_project.feed.models.dto.FeedCmtPutDto;
import com.project.t_story_copy_project.feed.models.dto.FeedInsDto;
import com.project.t_story_copy_project.feed.models.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService feedService;

    //카테고리 리스트 불러오기
    @GetMapping("/category")
    public ResponseEntity<CustomResponse<CatVo>> getCategory(@RequestParam Long blogPk){
        return ResponseEntity.ok(new CustomResponse<>(feedService.getCategory(blogPk)));
    }
    //카테고리 이름 pk 불러오기
    @GetMapping("/category-list")
    public ResponseEntity<CustomResponse<List<CatSimpleVo>>> getCategoryList(@RequestParam Long blogPk){
        return ResponseEntity.ok(new CustomResponse<>(feedService.getCategoryList(blogPk)));
    }


    //글 작성 버튼을 누르면 전에 작성한 피드가 있으면 불러오고 없으면 빈 피드를 불러온다.
    @GetMapping("/empty")
    public ResponseEntity<CustomResponse<ResVo>> getEmptyFeed(@RequestParam Long blogPk){
        return ResponseEntity.ok(new CustomResponse<>(feedService.getEmptyFeed(blogPk)));
    }
    //피드 삭제
    @DeleteMapping("/{feedPk}")
    public ResponseEntity<CustomResponse<ResVo>> deleteFeed(@PathVariable Long feedPk, @RequestParam Long blogPk){
        return ResponseEntity.ok(new CustomResponse<>(feedService.deleteFeed(feedPk,blogPk)));
    }

    //피드 사진 등록
    @PostMapping("/pic")
    public ResponseEntity<CustomResponse<FeedPicInfoVo>> postFeedPic(
            @RequestParam Long feedPk
            ,@RequestPart MultipartFile pic){
        return ResponseEntity.ok(new CustomResponse<>(feedService.postFeedPic(feedPk,pic)));
    }
    //피드 사진 삭제
    @DeleteMapping("/pic")
    public ResponseEntity<CustomResponse<ResVo>> deleteFeedPic(@RequestParam Long feedPicPk){
        return ResponseEntity.ok(new CustomResponse<>(feedService.deleteFeedPic(feedPicPk)));
    }
    //피드 수정
    @PutMapping
    public ResponseEntity<CustomResponse<ResVo>> putFeed(@RequestBody FeedInsDto dto){
        return ResponseEntity.ok(new CustomResponse<>(feedService.putFeed(dto)));
    }
    //피드 좋아요
    @PatchMapping("/fav")
    public ResponseEntity<CustomResponse<ResVo>> patchFeedFav(@RequestParam Long feedPk){
        return ResponseEntity.ok(new CustomResponse<>(feedService.patchFeedFav(feedPk)));
    }
    //피드 댓글 등록
    @PostMapping("/cmt")
    public ResponseEntity<CustomResponse<ResVo>> postFeedCmt(@RequestBody FeedCmtInsDto dto){
        return ResponseEntity.ok(new CustomResponse<>(feedService.postFeedCmt(dto)));
    }

    //피드 댓글 수정
    @PutMapping("/cmt")
    public ResponseEntity<CustomResponse<ResVo>> putFeedCmt(@RequestBody FeedCmtPutDto dto){
        return ResponseEntity.ok(new CustomResponse<>(feedService.putFeedCmt(dto)));
    }

    //피드 댓글 삭제
    @DeleteMapping("/cmt")
    public ResponseEntity<CustomResponse<ResVo>> deleteFeedCmt(@RequestParam Long feedCmtPk, @RequestBody String guestPw){
        return ResponseEntity.ok(new CustomResponse<>(feedService.deleteFeedCmt(feedCmtPk, guestPw)));
    }

    //전체 피드 리스트 출력
    @GetMapping("/main")
    public ResponseEntity<CustomResponse<FeedSimpleInfoVoList>> getFeedList(@RequestParam Pageable pageable){
        return ResponseEntity.ok(new CustomResponse<>(feedService.getFeedAll(pageable)));
    }
    //구독한 블로그 피드 리스트
    @GetMapping("/sub")
    public ResponseEntity<CustomResponse<FeedSimpleInfoVoList>> getFeedSubList(@RequestParam Pageable pageable){
        return ResponseEntity.ok(new CustomResponse<>(feedService.getFeedSub(pageable)));
    }
}
