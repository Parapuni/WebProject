package cmt.config;

import org.h2.server.web.WebServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 同时创建DispatcherServlet和ContextLoaderListener
 * DispatcherServlet是应用的核心servlet，是一个前端控制器
 * 前者负责将请求分发给controller，会创建servlet上下文，包含controller，view等web相关bean
 * 后者创建Root上下文，通常包含基础设施 Bean，例如数据存储库和需要在多个 Servlet 实例之间共享的业务服务，
 * 被servlet用于的子上下继承，可以被覆盖重写
 */
public class CommentSystemInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 指定Root配置
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 指定DispatcherServlet的配置
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * 将DispatcherServlet映射到“/”
     * 使其成为默认servlet，处理所有进入应用的请求
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        //注册H2的servlet，启动H2数据库，默认使用数据库URL jdbc:h2:mem:csdb 用户名sa 密码 空
        ServletRegistration.Dynamic servlet = servletContext.addServlet("h2-console", new WebServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/console/*");
    }
}
