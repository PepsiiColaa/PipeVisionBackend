package com.DigitalTwin.project.filter;

import com.DigitalTwin.project.common.BaseContext;
import com.DigitalTwin.project.common.R;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ZhuangShuo
 * @date: 2023/4/12 17:35
 * @description: 检查用户是否已经完成登录
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")

public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转，才能用getRequestURI
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1 获取本次请求的URI
        String requestURI = request.getRequestURI();

        log.info("拦截到请求：{}", requestURI);

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/user/login",
                "/user/register",
                "/user/sendMail",
                "/static",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };

        //2 判断本次请求是否需要处理
        boolean check = check(requestURI, urls);

        //3 如果不需要处理，则直接放行
        if (check) {
            filterChain.doFilter(request, response);
            log.info("本次请求{}不需要处理", requestURI);
            return;
        }

        //4-1 判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已登录，用户id为{}", request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");

            BaseContext.setCurrentId(empId);


            filterChain.doFilter(request, response);
            return;
        }
        //4-2 判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("user") != null) {
            log.info("用户已登录，用户id为{}", request.getSession().getAttribute("user"));

            Long empId = (Long) request.getSession().getAttribute("user");

            BaseContext.setCurrentId(empId);


            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登录");
        //5 如果未登录则返回未登录结果，通过输出流方式向客户端界面相应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param requestURI 传入的URI
     * @param urls       传入的不需要过滤的地址
     * @return 是否正确
     */
    public boolean check(String requestURI, String[] urls) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}

