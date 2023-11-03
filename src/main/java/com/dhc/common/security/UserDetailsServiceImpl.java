package com.dhc.common.security;

import com.dhc.administrator.dao.AdministratorRepository;
import com.dhc.administrator.entity.Administrator;
import com.dhc.administrator.service.AdministratorQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author donghongchen
 * @create 2023/10/27 16:17
 * @Description: 实现UserDetailsService接口
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdministratorRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator administrator = administratorRepository.findByLoginName(username);
        if (Objects.isNull(administrator)) {
            throw new RuntimeException("账号不存在");
        }
        if (Boolean.FALSE.equals(administrator.getEnable())) {
            throw new RuntimeException("账号已停用");
        }

        //权限配置
        List<SimpleGrantedAuthority> authorities = administrator.getPermits()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new CustomAdministrator(administrator, authorities);
    }

}
