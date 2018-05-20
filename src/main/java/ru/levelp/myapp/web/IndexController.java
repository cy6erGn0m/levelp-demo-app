package ru.levelp.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @Autowired
    private IndexBean bean;

    @GetMapping("/")
    public String index(ModelMap model, HttpSession session) {
        model.addAttribute("indexBean", bean);
        model.addAttribute("userName", session.getAttribute("userName"));
        return "index";
    }
}
