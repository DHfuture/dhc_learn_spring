package com.dhc.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author donghongchen
 * @create 2023/10/26 16:09
 * @Description:
 */
@Configuration
public class DhcAppWebMvcConfig implements WebMvcConfigurer {

    /* 序列化转化器 */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Long转String
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);

        //没有这个swagger的json解析会变成base64字符串
        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();

        //这里的顺序很重要，也许吧，我反过来添加也没出问题
        converters.addAll(0, List.of(byteArrayHttpMessageConverter, jackson2HttpMessageConverter));
    }

}
