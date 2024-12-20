package cmt.config.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 控制浏览器缓存静态资源，设置寿命为2小时
 * 只缓存.js .css .jpg .png文件
 */
@WebFilter(urlPatterns = {"*.js","*.css","*.jpg","*.png"})
public class ResponseCacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     *执行控制操作
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.addHeader("Cache-Control","MAX-AGE="+2*3600);//最大寿命7200s = 2h
        response.addHeader("Expires", new Date(System.currentTimeMillis()+1000*7200L).toString());//失效时间设置为当前时间+2h
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
