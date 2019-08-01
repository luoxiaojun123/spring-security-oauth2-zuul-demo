package com.xiaojun.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojun
 * @date 2018/12/1 22:16
 */
@RestController
@Slf4j
public class TestController {


    @GetMapping("/api/permit/test")
    public String testPermit() {
        return getUserName();
    }

    @GetMapping("/client/test")
    public String testClient() {
        return getUserName();
    }

    private String getUserName() {
        String message = "success";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            String principal = (String) authentication.getPrincipal();
            System.out.println(principal);
            message = message + ":" + principal;
        }
        log.info("success");
        return message;
    }
}
