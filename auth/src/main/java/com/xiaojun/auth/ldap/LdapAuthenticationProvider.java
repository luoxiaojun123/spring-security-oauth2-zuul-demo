package com.xiaojun.auth.ldap;

import com.xiaojun.auth.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ldap 认证提供者
 *
 * @author long.luo
 * @date 2018/12/19 9:35
 */
@Component("ldapAuthenticationProvider")
public class LdapAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LdapService ldapService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LdapAuthenticationToken token = (LdapAuthenticationToken) authentication;
        LdapUser ldapUser = (LdapUser) token.getPrincipal();
        String password = (String) token.getCredentials();

        // ldap 认证
        boolean isPass = ldapService.authenticate(ldapUser.getUsername(), password);
        if (!isPass) {
            throw new BadCredentialsException("Bad credentials");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new LdapAuthenticationToken(ldapUser.getUsername(), password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LdapAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
