package com.dhc.administrator.dao;

import com.dhc.administrator.entity.Administrator;
import com.dhc.common.dao.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author donghongchen
 * @create 2023/9/4 18:23
 * @Description:
 */
public interface AdministratorRepository extends BaseRepository<Administrator> {

    Administrator findByLoginName(String loginName);

}
