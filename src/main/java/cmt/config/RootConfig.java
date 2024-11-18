package cmt.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 定义根上下文中的Bean
 * 通常包含基础设施 Bean，例如数据存储库
 * 和需要在多个 Servlet 实例之间共享的业务服务。
 */
@Configuration
@ComponentScan(basePackages = {"cmt"}, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
})
public class RootConfig {
}
