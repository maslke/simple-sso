package com.maslke.demo.client;

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

import org.springframework.util.StringUtils;

public class LoginFilter implements Filter {

    private String ssoUrl;
    private FilterConfig filterConfig;

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.ssoUrl = filterConfig.getInitParameter("ssoUrl");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(true);
        if (session.getAttribute("isLogin") != null) {
            filterChain.doFilter(request, response);
            return;
        }
        String requestUrl = request.getRequestURL().toString();
        String token = request.getParameter("ticket");
        if (StringUtils.isEmpty(token)) {
            response.sendRedirect(this.ssoUrl + "/login.html?service=" + requestUrl);
        }
        else {
            if (isValidToken(this.ssoUrl, token)) {
                session.setAttribute("isLogin", true);
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                response.sendRedirect(ssoUrl + "?service=" + requestUrl);
            }
        }

    }

    private boolean isValidToken(String validUrl, String token) {
        return true;
    }
}
