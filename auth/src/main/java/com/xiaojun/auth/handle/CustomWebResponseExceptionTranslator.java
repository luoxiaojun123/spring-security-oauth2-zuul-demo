package com.xiaojun.auth.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author xiaojun
 * @date 2019/7/28 20:53
 */
@Component
@Slf4j
public class CustomWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        log.info("认证异常", e);
        ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
        OAuth2Exception body = responseEntity.getBody();
        String msg = "认证失败";
        if (!ObjectUtils.isEmpty(body)) {
            if (body.toString().contains(OAuth2Exception.INVALID_GRANT)) {
                if (body.toString().contains("User account is locked")){
                    msg = "账号被锁定，请2小时后重试";
                }else {
                    msg = "用户名或密码错误";
                }
            } else if (body.toString().contains(OAuth2Exception.INVALID_CLIENT)) {
                msg = "无授权应用";
            } else if (body.toString().contains(OAuth2Exception.INVALID_SCOPE)) {
                msg = "授权应用异常";
            } else if (body.toString().contains(OAuth2Exception.UNSUPPORTED_GRANT_TYPE)) {
                msg = "认证类型错误";
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(responseEntity.getHeaders().toSingleValueMap());
        // translate the exception
        return new ResponseEntity<>(new CustomOauthException(msg), headers, responseEntity.getStatusCode());
    }
}