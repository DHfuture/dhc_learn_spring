package com.dhc.administrator.controller;

import com.dhc.administrator.AdministratorPermissionId;
import com.dhc.administrator.service.AdministratorQueryService;
import com.dhc.administrator.service.AdministratorUpdateService;
import com.dhc.administrator.service.model.request.AdministratorRequestVO;
import com.dhc.administrator.service.model.response.AdministratorAccessTokenVO;
import com.dhc.common.context.ApiContext;
import com.dhc.common.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author donghongchen
 * @create 2023/10/18 14:18
 * @Description:
 */
@RestController
@RequestMapping(ApiContext.ADMIN_API + "/administrators")
@Tag(name = "账户管理")
@RequiredArgsConstructor
public class AdministratorController {

    private final AdministratorQueryService administratorQueryService;

    private final AdministratorUpdateService administratorUpdateService;

    @GetMapping()
    @Operation(summary = "查询")
    @PreAuthorize("hasAuthority('101')")
    public Object findAll() {
        return administratorQueryService.findAll();
    }

    @PostMapping("")
    @Operation(summary = "创建")
    @PreAuthorize("isAnonymous()")
    public Object create(@RequestBody AdministratorRequestVO.Create requestVO) {
        return administratorUpdateService.create(requestVO);
    }

    @PutMapping(value = "", params = {"id"})
    @Operation(summary = "更新")
    public Object update(@RequestParam Long id, @RequestBody AdministratorRequestVO.Update requestVO) {
        administratorUpdateService.update(id, requestVO);
        return Optional.empty();
    }

    @DeleteMapping(value = "", params = {"id"})
    @Operation(summary = "删除")
    public Object delete(@RequestParam Long id) {
        administratorUpdateService.delete(id);
        return Optional.empty();
    }

    @PostMapping("/login")
    @Operation(summary = "登陆")
    @PreAuthorize("isAnonymous()")
    public AdministratorAccessTokenVO login(@RequestBody AdministratorRequestVO.Login requestVO) {
        return administratorQueryService.login(requestVO);
    }

}
