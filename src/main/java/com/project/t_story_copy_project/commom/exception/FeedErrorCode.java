package com.project.t_story_copy_project.commom.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum FeedErrorCode implements ErrorCode{
    NOT_FOUND_CAT(HttpStatus.NOT_FOUND,"카테고리를 찾을 수 없습니다."),
    NOT_FOUND_FEED_PIC(HttpStatus.NOT_FOUND,"피드 사진을 찾을 수 없습니다."),
    NOT_FOUND_FEED(HttpStatus.NOT_FOUND,"피드를 찾을 수 없습니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
