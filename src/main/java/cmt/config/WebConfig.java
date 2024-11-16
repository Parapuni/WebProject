package cmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Web配置类，定义DispatcherServlet所需各种组件
 * 启用springMVC
 * 启用组件扫描，使得控制器Bean无需显式声明
 * 定义视图解析器和静态资源处理方式
 */
@Configuration
@EnableWebMvc
@ComponentScan("cmt")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        /*
        TODO
         后缀考虑有没有html还是纯jsp
         */
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        /*
        TODO
         主要是图片等静态资源的默认处理方式
         */
    }
}