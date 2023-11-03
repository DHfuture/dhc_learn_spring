package com.dhc.common.filter;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Author donghongchen
 * @create 2023/9/12 11:42
 * @Description:
 */
@Data
public class BaseFilter<T> {

    @Transient
    private Integer length;

    @Transient
    private Integer page;

    @Transient
    private Integer offset;

    @Transient
    private List<Sort.Order> orders;

    @Transient
    private Consumer<Long> totalHolder;

    public BaseFilter<T> addOrder(String field, Sort.Direction direction) {
        this.orders.add(new Sort.Order(direction, field));
        return this;
    }

}
