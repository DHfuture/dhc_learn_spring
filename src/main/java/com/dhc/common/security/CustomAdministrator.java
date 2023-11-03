package com.dhc.common.security;

import com.dhc.administrator.entity.Administrator;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Author donghongchen
 * @create 2023/10/27 16:10
 * @Description:
 */
@Getter
public class CustomAdministrator extends User {

    private Administrator administrator;

    public CustomAdministrator(Administrator administrator, Collection<? extends GrantedAuthority> authorities) {
        super(administrator.getLoginName(), administrator.getPassword(), authorities);
        this.administrator = administrator;
    }


    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
