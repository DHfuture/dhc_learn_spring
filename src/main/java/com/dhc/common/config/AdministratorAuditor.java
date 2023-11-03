package com.dhc.common.config;

import com.dhc.administrator.entity.Administrator;
import com.dhc.common.security.CustomAdministrator;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author donghongchen
 * @create 2023/10/19 16:34
 * @Description: administratorId自动填充
 */
@Component
public class AdministratorAuditor implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomAdministrator customUser = (CustomAdministrator) authentication.getPrincipal();
        Administrator administrator = customUser.getAdministrator();
        return Optional.of(administrator.getId());
    }

}
