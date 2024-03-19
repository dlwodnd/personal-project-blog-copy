package com.project.t_story_copy_project.commom.utils;

import org.springframework.context.annotation.Bean;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CommonUtils {
    @Bean
    public String createCertificationNumber() throws NoSuchAlgorithmException {
        String result;

        do {
            int num = SecureRandom.getInstanceStrong().nextInt(999999);
            result = String.valueOf(num);
        } while (result.length() != 6);

        return result;
    }
}
