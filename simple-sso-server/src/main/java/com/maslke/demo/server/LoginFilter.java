package com.maslke.demo.server;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String service = uri.substring(contextPath.length());
        if (service.equals("/login.html") || service.equals("/login/doLogin")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (session.getAttribute("isLogin") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("login.html");
        }
    }

    @Override
    public void destroy() {

    }
}
