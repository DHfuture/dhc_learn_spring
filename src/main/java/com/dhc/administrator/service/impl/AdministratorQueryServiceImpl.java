package com.dhc.administrator.service.impl;

import com.dhc.administrator.dao.AdministratorRepository;
import com.dhc.administrator.entity.Administrator;
import com.dhc.administrator.service.AdministratorQueryService;
import com.dhc.administrator.service.model.request.AdministratorRequestVO;
import com.dhc.administrator.service.model.response.AdministratorAccessTokenVO;
import com.dhc.common.security.CustomAdministrator;
import com.dhc.common.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author donghongchen
 * @create 2023/9/5 15:16
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdministratorQueryServiceImpl implements AdministratorQueryService {

    private final AdministratorRepository administratorRepository;

    private final AuthenticationManager authenticationManager;

    @Override
    public AdministratorRepository getRepository() {
        return administratorRepository;
    }

    @Override
    public Optional<Administrator> findByLoginName(String loginName) {
        return Optional.ofNullable(administratorRepository.findByLoginName(loginName));
    }

    @Override
    public AdministratorAccessTokenVO login(AdministratorRequestVO.Login requestVO) {
        // 将表单数据封装到 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(requestVO.getLoginName(), requestVO.getPassword());
        // authenticate方法会调用loadUserByUsername
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        // 校验成功，强转对象
        CustomAdministrator customUser = (CustomAdministrator) authenticate.getPrincipal();
        Administrator administrator = customUser.getAdministrator();
        // 校验通过返回token
        String token = JwtUtil.generateToken(administrator.getId(), administrator.getLoginName());

        AdministratorAccessTokenVO vo = new AdministratorAccessTokenVO();
        vo.setAdministrator(administrator);
        vo.setAccessToken(token);


        return vo;
    }
}

