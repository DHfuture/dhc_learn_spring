package com.dhc.common.converter;

import com.dhc.common.base.EnumToData;
import jakarta.persistence.AttributeConverter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Author donghongchen
 * @create 2023/10/25 14:36
 * @Description: 枚举-数据库 转化
 */
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public abstract class EnumConverter<X extends Enum<X> & EnumToData<Y>, Y> implements AttributeConverter<X, Y> {

    private final Class<X> clazz;

    @Override
    public Y convertToDatabaseColumn(X x) {
        return x != null ? x.getValue() : null;
    }

    @Override
    public X convertToEntityAttribute(Y y) {
        if (Objects.isNull(y) || Objects.isNull(clazz)) {
            return null;
        }
        return Arrays.stream(this.clazz.getEnumConstants())
                .filter(f -> f.getValue().equals(y))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("未知枚举类型:" + y));
    }
}
