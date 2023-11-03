package com.dhc.administrator;

import com.dhc.common.base.EnumToData;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author donghongchen
 * @create 2023/10/30 14:27
 * @Description:
 */
@Getter
public enum AdministratorPermissionId implements EnumToData<String> {

    ADMINISTRATOR_QUERY("101", "管理员查询", "管理员"),


    ;

    private final String id;

    private final String name;

    private final String group;

    AdministratorPermissionId(String id, String name, String group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    @Override
    @JsonValue
    public String getValue() {
        return id;
    }

}
