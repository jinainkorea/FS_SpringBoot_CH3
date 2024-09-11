package com.mysite.sbb3;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "Hello World";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/article/list";
    }
}
