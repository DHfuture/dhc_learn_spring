package com.dhc.common.converter;

import com.alibaba.fastjson2.JSON;
import jakarta.persistence.AttributeConverter;

/**
 * @Author donghongchen
 * @create 2023/10/30 12:03
 * @Description:
 */
public class ListJsonConverter implements AttributeConverter<Object, String> {
    @Override
    public String convertToDatabaseColumn(Object o) {
        return JSON.toJSONString(o);
    }

    @Override
    public Object convertToEntityAttribute(String s) {
        return JSON.parseArray(s);
    }
}
