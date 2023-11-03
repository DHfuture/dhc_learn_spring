package com.dhc.administrator.service;

import com.dhc.administrator.entity.Administrator;
import com.dhc.administrator.service.model.request.AdministratorRequestVO;

/**
 * @Author donghongchen
 * @create 2023/10/24 14:26
 * @Description:
 */
public interface AdministratorUpdateService {
    Administrator create(AdministratorRequestVO.Create requestVO);

    void update(Long id, AdministratorRequestVO.Update requestVO);

    void delete(Long id);
}
