package com.china.ciic.bookgenerate;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * 在这里我们用SpringBootApplication指定这是一个springboot的应用程序
 * Created by once on 2017/4/24.
 */
@EnableAutoConfiguration
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        /*
         *在main方法中启动我们的应用程序
         */
        SpringApplication.run(Application.class, args);

    }
    /**
     * 使用@Bean注入fastJsonHttpMessageConvert
     * 使用第三方的json转换框架
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //定义coverter消息转换对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //fastjson配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //添加配置
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> httpMessageConverter = fastJsonHttpMessageConverter;
        return new HttpMessageConverters(httpMessageConverter);
    }
}
