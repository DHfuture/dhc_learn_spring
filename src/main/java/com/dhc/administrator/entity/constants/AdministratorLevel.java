package com.dhc.administrator.entity.constants;

import com.dhc.common.converter.EnumConverter;
import com.dhc.common.base.EnumToData;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Author donghongchen
 * @create 2023/9/4 17:48
 * @Description:
 */
public enum AdministratorLevel implements EnumToData<Integer> {

    /**
     * 超级管理员
     */
    SUPER_ADMINISTRATOR(0),

    /**
     * 管理员
     */
    ADMINISTRATOR(1),

    /**
     * 操作者
     */
    OPERATOR(2),

    ;

    public static final String DOC = "账号级别：0:超级管理员；1管理员；2操作者";

    private final Integer value;


    AdministratorLevel(Integer value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public Integer getValue() {
        return value;
    }


    public static class Converter extends EnumConverter<AdministratorLevel, Integer> {
        public Converter() {
            super(AdministratorLevel.class);
        }
    }


}
