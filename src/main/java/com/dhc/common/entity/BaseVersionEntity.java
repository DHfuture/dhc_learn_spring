package com.dhc.common.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author donghongchen
 * @create 2023/10/24 11:22
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class BaseVersionEntity extends BaseEntity{

    /**
     * 版本号
     */
    @Version
    private Integer version;

}
