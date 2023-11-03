package com.dhc.administrator.service.model.response;

import com.dhc.administrator.entity.Administrator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author donghongchen
 * @create 2023/5/18 15:20
 * @Description:
 */
@Schema(name = "AdministratorAccessTokenVO")
@Getter
@Setter
public class AdministratorAccessTokenVO {

    @Schema(title = "授权令牌")
    private String accessToken;

    @Schema(title = "账户信息")
    private Administrator administrator;

}
