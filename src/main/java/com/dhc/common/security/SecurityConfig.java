package com.dhc.common.security;

import com.dhc.common.security.encoder.CustomPasswordEncoder;
import com.dhc.common.security.jwt.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

/**
 * @Author donghongchen
 * @create 2023/10/27 15:27
 * @Description:
 */
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 修改默认加密方式
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     *
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 基于 token，不需要 csrf
                .csrf(csrfConfigurer -> {
                    csrfConfigurer.disable();
                })
                // 开启跨域以便前端调用接口
                .cors(corsConfigurer -> {
                    corsConfigurer.configurationSource(corsConfigurationSource());
                })
                // 指定某些接口不需要通过验证即可访问。登录接口肯定是不需要认证的
                .authorizeHttpRequests(registry -> {
                    registry
                            // 静态资源，可匿名访问
                            .requestMatchers(HttpMethod.GET, "/", "/*.html", "/*/*.html", "/*/*.css", "/*/*.js", "/profile/*").permitAll()
                            .requestMatchers("/swagger-ui.html", "/swagger-resources/*", "/webjars/*", "/*/api-docs", "/druid/*", "/doc.html").permitAll()
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs").permitAll()
//                            .requestMatchers("/admin/api/administrators/login").permitAll()
//                            .requestMatchers(HttpMethod.POST, "/admin/api/administrators").permitAll()
                            // 这里意思是其它所有接口需要认证才能访问
                            .anyRequest().authenticated();
                })
                // 基于 token，不需要 session
                .sessionManagement(managementConfigurer -> {
                    managementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    /**
     * 配置跨源访问(CORS)
     *
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许的跨域请求来源 eg: http://example.com
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        // 允许的请求头 eg: Content-Type Accept
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        // 允许的请求方法 eg: GET POST
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
