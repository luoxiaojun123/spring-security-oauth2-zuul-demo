package com.xiaojun.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaojun
 * @date 2018/12/1 22:16
 */
@RestController
@Slf4j
public class TestController {
    private static final String X_USER_ID = "x_user_id";

    @GetMapping("/test")
    public String testAdmin(@RequestHeader(X_USER_ID) String userName) {
        log.info(userName);
        return "success:" + userName;
    }
}
