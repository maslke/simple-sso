package com.maslke.demo.server.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private Set<String> set = new HashSet<>();


    @RequestMapping("/login")
    public String login(String userName, String password, ServletRequest request) {
        request.setAttribute("isLogin", true);
        return "success";
    }

    @RequestMapping("/validate")
    public Model validateToken(String token, Model model) {
        model.addAttribute("valid", set.contains(token));
        return model;
    }

}
