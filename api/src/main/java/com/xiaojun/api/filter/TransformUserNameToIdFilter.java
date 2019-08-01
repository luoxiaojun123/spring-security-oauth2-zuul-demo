package com.xiaojun.api.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author long.luo
 */
@Slf4j
@Component
public class TransformUserNameToIdFilter extends ZuulFilter {

    private static final String USER_ID = "user_id";

    String X_USER_ID = "x_user_id";

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 8;
    }

    @Override
    public boolean shouldFilter() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2Authentication) {
            String userName = (String) authentication.getPrincipal();
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.set(USER_ID, userName);
            return true;
        }

        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(X_USER_ID, ctx.get(USER_ID).toString());
        return null;
    }

}
