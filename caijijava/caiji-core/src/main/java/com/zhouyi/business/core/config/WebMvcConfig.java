package com.zhouyi.business.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: WebMvcConfig
 * @Description: 消息转换器，用户在controller直接返回对象
 * @date 2019/6/21 12:18
 * @Version 1.0
 **/
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //定义消息转换器
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter=new FastJsonHttpMessageConverter();

        //定义配置
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        //防止乱码
        List<MediaType> mediaTypes=new ArrayList<MediaType>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);

        //将配置添加到转换器
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        //将转换器交给容器
        HttpMessageConverter<?> httpMessageConverter=fastJsonHttpMessageConverter;
        converters.add(httpMessageConverter);
    }



}
