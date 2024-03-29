package com.xiaojun.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码校验
 *
 * @author long.luo
 * @date 2018/8/6 14:48
 */
@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    /**
     * 登录地址
     */
    private static final String LOGIN_URL = "/user/login";

    /**
     * 请求方式
     */
    private static final String POST = "post";

    /**
     * 固定验证码
     */
    private static final String IMAGE_CODE = "1234";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("请求URI:[{}],请求类型:[{}]", request.getRequestURI(), request.getMethod());
        if (StringUtils.equals(LOGIN_URL, request.getRequestURI()) && StringUtils.endsWithIgnoreCase(POST, request.getMethod())) {
            try {
                validate(new ServletWebRequest(request));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 执行下一个过滤器
        filterChain.doFilter(request, response);
    }

    /**
     * 校验
     */
    private void validate(ServletWebRequest request) throws Exception {
        String imageCodeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        if (StringUtils.isBlank(imageCodeInRequest)) {
            throw new Exception("验证码不能为空");
        }
        if (StringUtils.isBlank(IMAGE_CODE)) {
            throw new Exception("验证码为空");
        }
        if (!StringUtils.equals(IMAGE_CODE, imageCodeInRequest)) {
            throw new Exception("验证码错误");
        }
    }
}
