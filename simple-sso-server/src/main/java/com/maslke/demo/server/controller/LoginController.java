package com.maslke.demo.server.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private Set<String> set = new HashSet<>();


    @RequestMapping("/doLogin")
    public void login(String userName, String password, ServletRequest request, ServletResponse response) throws IOException {
        request.setAttribute("isLogin", true);
        String service = request.getParameter("service");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (!StringUtils.isEmpty(service)) {
            String token = UUID.randomUUID().toString();
            set.add(token);
            if (service.contains("?")) {
                httpServletResponse.sendRedirect(service + "&ticket=" + token);
            } else {
                httpServletResponse.sendRedirect(service + "?ticket=" + token);
            }
        }
    }

    @RequestMapping("/doValidate")
    public Model validateToken(String token, Model model) {
        model.addAttribute("valid", set.contains(token));
        return model;
    }



}
