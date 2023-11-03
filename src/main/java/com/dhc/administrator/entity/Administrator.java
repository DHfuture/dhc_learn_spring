package com.dhc.administrator.entity;

import com.dhc.administrator.entity.constants.AdministratorLevel;
import com.dhc.common.converter.ListJsonConverter;
import com.dhc.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @Author donghongchen
 * @create 2023/9/4 17:27
 * @Description:
 */
@Entity(name = "t_administrator")
@EqualsAndHashCode(callSuper = true)
@Data
public class Administrator extends BaseEntity {

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 密码
     */
    @Column(length = 533, columnDefinition = "TEXT")
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 权限
     */
    @Convert(converter = AdministratorLevel.Converter.class)
    private AdministratorLevel level;

    @Convert(converter = ListJsonConverter.class)
    private List<String> permits;

}
