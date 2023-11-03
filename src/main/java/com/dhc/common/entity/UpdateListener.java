package com.dhc.common.entity;

import jakarta.persistence.PreUpdate;

import java.util.Date;

/**
 * 更新前执行
 */
class UpdateListener {

    /**
     * 更新前执行
     */
    @PreUpdate
    void preUpdate(BaseEntity entity) {
//        Date now = new Date();
//        entity.setCreated(null);
//        entity.setModified(now);
    }

}
