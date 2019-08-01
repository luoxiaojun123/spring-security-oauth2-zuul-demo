package com.xiaojun.auth.password;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author long.luo
 */
@Data
public class CustomUser extends User {

    private static final long serialVersionUID = -8017517995597295963L;
    private Integer id;

    public CustomUser(Integer id, String username, String password,
                      Collection<? extends GrantedAuthority> authorities) {
        this(id, username, password, true, true, true, true, authorities);
    }

    public CustomUser(Integer id,
                      String userName,
                      String password,
                      boolean enabled,
                      boolean accountNonExpired,
                      boolean credentialsNonExpired,
                      boolean accountNonLocked,
                      Collection<? extends GrantedAuthority> authorities) {
        super(userName,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);

        this.id = id;
    }
}
