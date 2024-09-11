package com.mysite.sbb3.Article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("article")
public class ArticleController {
    @GetMapping("/list")
    @ResponseBody
    public String list() {
        return "article_list";
    }
}
