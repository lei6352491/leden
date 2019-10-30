package com.zhouyi.business.core.config;

import com.thoughtworks.xstream.converters.basic.ByteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

/**
 * @author 李秸康
 * @ClassNmae: TestConfig
 * @Description: TODO
 * @date 2019/9/2 16:46
 * @Version 1.0
 **/
@Configuration
public class TestConfig {
    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter(){
        return new ByteArrayHttpMessageConverter();
    }
}
