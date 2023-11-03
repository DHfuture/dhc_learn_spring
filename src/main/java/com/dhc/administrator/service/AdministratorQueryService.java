package com.dhc.administrator.service;

import com.dhc.administrator.dao.AdministratorRepository;
import com.dhc.administrator.entity.Administrator;
import com.dhc.administrator.service.model.request.AdministratorRequestVO;
import com.dhc.administrator.service.model.response.AdministratorAccessTokenVO;
import com.dhc.common.service.BaseService;

import java.util.List;
import java.util.Optional;

/**
 * @Author donghongchen
 * @create 2023/9/5 15:16
 * @Description:
 */
public interface AdministratorQueryService extends BaseService<Administrator, AdministratorRepository> {

    Optional<Administrator> findByLoginName(String loginName);

    AdministratorAccessTokenVO login(AdministratorRequestVO.Login requestVO);
}
