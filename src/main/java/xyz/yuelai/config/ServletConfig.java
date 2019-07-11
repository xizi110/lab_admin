package xyz.yuelai.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/11-10:24
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {ServletConfig.class,Swagger2Config.class})
public class ServletConfig implements WebMvcConfigurer {

    /**
     * 默认静态资源处理
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable("default");
    }

    /**
     * 服务器响应客户端请求编码、文件类型设置
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        List<MediaType> mediaTypes = new ArrayList<MediaType>() {{
            add(MediaType.TEXT_HTML);
            add(MediaType.TEXT_PLAIN);
            add(MediaType.APPLICATION_JSON);
        }};
        converter.setSupportedMediaTypes(mediaTypes);
        converter.setWriteAcceptCharset(false);
        converters.add(converter);
    }
}
