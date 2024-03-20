package com.project.t_story_copy_project.commom.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.validation.FieldError;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CustomResponse<T> {
    private int codeNum;
    private String message;
    private String timestamp = LocalDateTime.now().toString();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;
    public CustomResponse() {
        this.codeNum = 200;
        this.message = "OK";
    }
    public CustomResponse(T data) {
        this.codeNum = 200;
        this.message = "OK";
        this.data = data;
    }

    // Errors가 없다면 응답이 내려가지 않게 처리
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ValidationError> valid;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {
        // @Valid 로 에러가 들어왔을 때, 어느 필드에서 에러가 발생했는 지에 대한 응답 처리
        private final String field;
        private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}
