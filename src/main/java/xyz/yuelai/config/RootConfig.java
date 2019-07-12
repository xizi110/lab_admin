package xyz.yuelai.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author 李泽众
 * @date 2019/7/11-10:24
 */

@Configuration
@ComponentScan(basePackages = {"xyz.yuelai"})
@ImportResource(value = "classpath:applicationContext*.xml")
public class RootConfig {


}
