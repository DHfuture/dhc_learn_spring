package com.dhc.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * @Author donghongchen
 * @create 2023/9/4 17:29
 * @Description:
 */
@Data
@MappedSuperclass
@EntityListeners({CreateListener.class, UpdateListener.class, AuditingEntityListener.class})
public class BaseEntity {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created;

    /**
     * 创建人
     */
    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date modified;

    /**
     * 修改人
     */
    @LastModifiedBy
    private Long modifiedBy;

    /**
     * 启用/禁用
     */
    private Boolean enable;

    /**
     * ip地址
     */
    @Column(updatable = false)
    private String ip;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    /**
     * 版本号
     * update时必带版本号，否则save会忽略id，新增数据
     */
//    @Version
//    private Integer version;

}
