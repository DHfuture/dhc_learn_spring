package com.dhc.administrator.service.impl;

import com.dhc.administrator.dao.AdministratorRepository;
import com.dhc.administrator.entity.Administrator;
import com.dhc.administrator.service.AdministratorUpdateService;
import com.dhc.administrator.service.model.request.AdministratorRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author donghongchen
 * @create 2023/10/24 14:26
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class AdministratorUpdateServiceImpl implements AdministratorUpdateService {

    private final AdministratorRepository administratorRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Administrator create(AdministratorRequestVO.Create requestVO) {
        var model = new Administrator();
        set(model, requestVO);
        model.setLevel(requestVO.getLevel());
        return administratorRepository.save(model);
    }

    @Override
    public void update(Long id, AdministratorRequestVO.Update requestVO) {
        if (!administratorRepository.existsById(id)) {
            throw new RuntimeException("数据不存在");
        }
        var model = new Administrator();
        model.setId(id);
        set(model, requestVO);
        administratorRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        if (!administratorRepository.existsById(id)) {
            throw new RuntimeException("数据不存在");
        }
        administratorRepository.deleteById(id);
    }

    private void set(Administrator model, AdministratorRequestVO.Common requestVO) {
        model.setLoginName(requestVO.getLoginName());
        model.setNickname(requestVO.getNickname());
        model.setPassword(passwordEncoder.encode(requestVO.getPassword()));
        model.setPermits(requestVO.getPermits());
    }

}
