package com.xiaojun.auth.password;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Security 登录身份证认证
 *
 * @author long.luo
 * @date 2018-08-07
 */
@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"customer".equals(username)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new CustomUser(1, "customer", "$2a$10$3.KvTyyR6dcQYoTC5JMc8u7GPYE7W8xN19ijEHkbN0Gn4ktOt7TlG",
                true, true, true, false, authorities);
    }
}
