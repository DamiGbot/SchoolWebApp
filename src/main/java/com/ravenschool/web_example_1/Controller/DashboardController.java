package com.ravenschool.web_example_1.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model,Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", String.valueOf(authentication.getAuthorities()));
        throw new RuntimeException("This is something!!!!");
//        return "dashboard.html";
    }

}