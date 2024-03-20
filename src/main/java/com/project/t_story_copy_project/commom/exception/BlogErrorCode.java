package com.project.t_story_copy_project.commom.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BlogErrorCode implements ErrorCode{
    NOT_MATCH_USER(HttpStatus.BAD_REQUEST,"유저가 일치하지 않습니다."),
    NOT_FOUND_BLOG(HttpStatus.NOT_FOUND,"블로그를 찾을 수 없습니다.");
    private final HttpStatus httpStatus;
    private final String message;

}
