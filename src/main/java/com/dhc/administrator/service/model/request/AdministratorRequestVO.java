package com.dhc.administrator.service.model.request;

import com.dhc.administrator.entity.constants.AdministratorLevel;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Convert;
import lombok.Data;

import java.util.List;

/**
 * @Author donghongchen
 * @create 2023/10/24 11:44
 * @Description:
 */
public interface AdministratorRequestVO {

    interface Common {

        String getLoginName();

        String getPassword();

        String getNickname();

        List<String> getPermits();

    }

    @Data
    @Schema(name = "AdministratorRequestVO_Create")
    class Create implements Common {

        @Schema(title = "登陆名")
        private String loginName;

        @Schema(title = "密码")
        private String password;

        @Schema(title = "昵称")
        private String nickname;

        @Schema(title = AdministratorLevel.DOC)
        @Convert(converter = AdministratorLevel.Converter.class)
        private AdministratorLevel level;

        @Schema(title = "权限")
        private List<String> permits;
    }

    @Data
    @Schema(name = "AdministratorRequestVO_Update")
    class Update implements Common {

        @Schema(title = "登陆名")
        private String loginName;

        @Schema(title = "密码")
        private String password;

        @Schema(title = "昵称")
        private String nickname;

        @Schema(title = "权限")
        private List<String> permits;

    }

    @Data
    @Schema(name = "AdministratorRequestVO_Login")
    class Login {

        @Schema(title = "登陆名")
        private String loginName;

        @Schema(title = "密码")
        private String password;

    }

}
