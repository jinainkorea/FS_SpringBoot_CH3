package com.std.basic1.Article;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Getter
@Setter
public class ArticleController {
    @GetMapping("/article/list")
    public String list() {
        return "article_list";
    }
}
