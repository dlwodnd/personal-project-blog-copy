package com.project.t_story_copy_project.mail;

import com.project.t_story_copy_project.commom.exception.CustomException;
import com.project.t_story_copy_project.commom.exception.CustomResponse;
import com.project.t_story_copy_project.commom.exception.EmailErrorCode;
import com.project.t_story_copy_project.mail.models.EmailCertificationDto;
import com.project.t_story_copy_project.mail.models.EmailCertificationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mails")
public class EmailController {
    private final EmailService emailService;
    @PostMapping("/send-certification")
    public ResponseEntity<CustomResponse<Void>> sendCertificationNumber(@Validated @RequestBody EmailCertificationDto dto) throws Exception {
        if (emailService.userEmailDuplicationCheck(dto.getEmail())) {
            throw new CustomException(EmailErrorCode.ALREADY_USED_EMAIL);
        }
        emailService.sendEmailForCertification(dto.getEmail());
        return ResponseEntity.ok(new CustomResponse());
    }
    @GetMapping("/verify")
    public ResponseEntity<CustomResponse<EmailCertificationVo>> verifyCertificationNumber(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "certificationNumber") String certificationNumber) {
        emailService.verifyEmail(email, certificationNumber);

        return ResponseEntity.ok(new CustomResponse<>(EmailCertificationVo.builder()
                .email(email)
                .result(1)
                .build()));
    }

}
