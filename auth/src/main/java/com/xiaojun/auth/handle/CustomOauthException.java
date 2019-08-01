package com.xiaojun.auth.handle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author xiaojun
 * @date 2019/7/28 20:18
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
class CustomOauthException extends OAuth2Exception {
    private static final long serialVersionUID = -6291669360537286850L;

    CustomOauthException(String msg) {
        super(msg);
    }
}