package com.dhc.common.entity;

import jakarta.persistence.PrePersist;

import java.util.Date;

/**
 * 创建前执行
 */
class CreateListener {

    @PrePersist
    void preCreate(BaseEntity entity) {
//        Date now = new Date();
//        if (entity.getCreated() == null) {
//            entity.setCreated(now);
//        }
//        if (entity.getModified() == null) {
//            entity.setModified(now);
//        }
        if (entity.getEnable() == null) {
            entity.setEnable(true);
        }
        if (entity.getIsDeleted() == null) {
            entity.setIsDeleted(false);
        }
    }

}
