package ru.levelp.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private IndexBean bean;

    @GetMapping("/")
    public String index(ModelMap model) {
        model.addAttribute("indexBean", bean);
        return "index";
    }
}
