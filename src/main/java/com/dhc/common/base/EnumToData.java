package com.dhc.common.base;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Author donghongchen
 * @create 2023/10/26 14:00
 * @Description:
 */
public interface EnumToData<T> {

    T getValue();

}
