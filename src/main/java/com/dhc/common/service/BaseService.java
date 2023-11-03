package com.dhc.common.service;

import com.dhc.common.entity.BaseEntity;
import com.dhc.common.filter.BaseFilter;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author donghongchen
 * @create 2023/9/5 15:23
 * @Description:
 */
public interface BaseService<T extends BaseEntity, R extends JpaRepository<T, Long>> {

    R getRepository();

    default T findById(Long id) {
        return getRepository().findById(id).orElse(null);
    }

    default List<T> findAll() {
        return getRepository().findAll();
    }


//    default void save(T entity) {
//        getRepository().save(entity);
//    }


    default void update(Long id, T entity) {
        entity.setId(id);
        getRepository().save(entity);
    }


    default void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    default Page<T> findPage(BaseFilter<T> filter, T condition) {

        Pageable pageable = PageRequest.of(filter.getPage(), filter.getLength(), Sort.by(filter.getOrders()));
        Example<T> example = Example.of(condition);
        return getRepository().findAll(example, pageable);
    }

//    default List<T> findList(BaseFilter<T> filter, T condition) {
//        getRepository().findAll()
//
//    }

}