package xyz.yuelai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author 李泽众
 * @date 2019/7/11-10:24
 */

@Configuration
@ComponentScan(basePackages = {"xyz.yuelai"})
@ImportResource(value = "classpath*:applicationContext*.xml")
public class RootConfig {

    /**
     * 文件上传解析器
     * @return
     */
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1024*1024);
        return resolver;
    }

}
