package com.project.t_story_copy_project.user;

import com.project.t_story_copy_project.commom.AppProperties;
import com.project.t_story_copy_project.commom.entity.UserEntity;
import com.project.t_story_copy_project.commom.entity.jpa_enum.SocialEnum;
import com.project.t_story_copy_project.commom.entity.jpa_enum.UserRoleEnum;
import com.project.t_story_copy_project.commom.exception.CustomException;
import com.project.t_story_copy_project.commom.exception.UserErrorCode;
import com.project.t_story_copy_project.commom.repository.UserRepository;
import com.project.t_story_copy_project.commom.utils.CookieUtils;
import com.project.t_story_copy_project.commom.utils.MyFileUtils;
import com.project.t_story_copy_project.security.AuthenticationFacade;
import com.project.t_story_copy_project.security.JwtTokenProvider;
import com.project.t_story_copy_project.security.MyPrincipal;
import com.project.t_story_copy_project.user.models.dto.UserLoginDto;
import com.project.t_story_copy_project.user.models.dto.UserSingUpDto;
import com.project.t_story_copy_project.user.models.vo.UserLoginVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MyFileUtils myFileUtils;
    private final CookieUtils cookieUtils;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AppProperties appProperties;
    private final AuthenticationFacade authenticationFacade;


    public void userSignUp(UserSingUpDto dto, MultipartFile profileImg){
        UserEntity userEntity = UserEntity.builder()
                .userEmail(dto.getEmailCertificationVo().getEmail())
                .userPw(passwordEncoder.encode(dto.getPassword()))
                .userPic(null)
                .role(UserRoleEnum.USER)
                .socialType(SocialEnum.NORMAL)
                .userName(dto.getName())
                .build();
        userRepository.save(userEntity);
        if (profileImg != null) {
            String target = "/user/" + userEntity.getUserPk();
            String fileNm = myFileUtils.transferTo(profileImg, target);
            userEntity.changeUserPic(fileNm);
        }

    }
    public UserLoginVo userLogin(HttpServletRequest request, HttpServletResponse response, UserLoginDto dto){
        UserEntity userEntity = userRepository.findByUserEmail(dto.getEmail())
                .orElseThrow(() -> new CustomException(UserErrorCode.NOT_FOUND_USER));
        if (!passwordEncoder.matches(dto.getPassword(), userEntity.getUserPw())){
            throw new CustomException(UserErrorCode.INVALID_PASSWORD);
        }
        MyPrincipal myPrincipal = MyPrincipal.builder()
                .userPk(userEntity.getUserPk())
                .role(userEntity.getRole().name())
                .build();
        String at = jwtTokenProvider.generateAccessToken(myPrincipal);
        String rt = jwtTokenProvider.generateRefreshToken(myPrincipal);
        int rtCookieMaxAge = (int) appProperties.getJwt().getRefreshTokenExpiry() / 1000;
        cookieUtils.deleteCookie(response, "rt");
        cookieUtils.setCookie(response, "rt", rt, rtCookieMaxAge);
        return UserLoginVo.builder()
                .userPk(userEntity.getUserPk())
                .email(userEntity.getUserEmail())
                .nickname(userEntity.getNickname())
                .accessToken(at)
                .build();
    }
    public String changeProfilePic(MultipartFile profileImg){
        UserEntity userEntity = userRepository.findById(authenticationFacade.getLoginUserPk())
                .orElseThrow(() -> new CustomException(UserErrorCode.NOT_FOUND_USER));
        String target = "/user/" + userEntity.getUserPk();
        myFileUtils.delAllFiles(target);
        String fileNm = myFileUtils.transferTo(profileImg, target);
        userEntity.changeUserPic(fileNm);
        return fileNm;
    }

    public Boolean checkUserDuplicationNickname(String nickname){
        return userRepository.findByNickname(nickname).isEmpty();
    }



}
