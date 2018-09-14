package com.maslke.demo.server.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private Set<String> set = new HashSet<>();


    @RequestMapping("/doLogin")
    public Model login(String userName, String password, ServletRequest request, ServletResponse response, Model model) throws IOException {
        if (!checkLogin(userName, password)) {
            model.addAttribute("ret", "failure");
            model.addAttribute("msg", "invalid username or password");
            return model;
        }
        String token = UUID.randomUUID().toString();
        set.add(token);
        request.setAttribute("isLogin", true);
        model.addAttribute("ret", "success");
        model.addAttribute("token", token);
        return model;

    }

    private boolean checkLogin(String userName, String password) {
        return true;
    }

    @RequestMapping("/doValidate")
    public Model validateToken(String token, Model model) {
        model.addAttribute("valid", set.contains(token));
        return model;
    }


}
