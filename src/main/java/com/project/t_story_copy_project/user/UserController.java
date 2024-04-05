package com.project.t_story_copy_project.user;

import com.project.t_story_copy_project.commom.exception.CustomException;
import com.project.t_story_copy_project.commom.exception.CustomResponse;
import com.project.t_story_copy_project.commom.exception.UserErrorCode;
import com.project.t_story_copy_project.user.models.dto.UserLoginDto;
import com.project.t_story_copy_project.user.models.dto.UserSingUpDto;
import com.project.t_story_copy_project.user.models.vo.UserLoginVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")//유저 회원가입
    public ResponseEntity<CustomResponse<Void>> userSignUp(@Valid @RequestPart UserSingUpDto dto
            , @RequestPart(required = false) MultipartFile profileImg){
        userService.userSignUp(dto,profileImg);
        return ResponseEntity.ok(new CustomResponse<>());
    }
    @PostMapping("/login") //유저 로그인
    public ResponseEntity<CustomResponse<UserLoginVo>> userLogin(HttpServletRequest request
            , HttpServletResponse response
            , @Valid @RequestBody UserLoginDto dto){
        UserLoginVo userLoginVo = userService.userLogin(request,response,dto);
        CustomResponse<UserLoginVo> customResponse = new CustomResponse<>(userLoginVo);
        return ResponseEntity.ok(customResponse);
    }
    @PatchMapping("/profile-pic")//유저 프로필 사진 변경
    public ResponseEntity<CustomResponse<String>> changeProfilePic(@RequestPart MultipartFile profileImg){
        String saveFile = userService.changeProfilePic(profileImg);
        CustomResponse<String> customResponse = new CustomResponse<>(saveFile);
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/nickname-check")//닉네임 중복체크
    public ResponseEntity<CustomResponse<String>> checkUserDuplicationNickname(@RequestParam String nickname){
        boolean result = userService.checkUserDuplicationNickname(nickname);
        if (!result){
            throw new CustomException(UserErrorCode.DUPLICATION_NICKNAME);
        }
        CustomResponse<String> customResponse = new CustomResponse<>(nickname);
        return ResponseEntity.ok(customResponse);
    }
}
