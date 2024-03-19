package com.project.t_story_copy_project.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;
}
