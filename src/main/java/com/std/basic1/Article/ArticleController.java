package com.std.basic1.Article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Getter
@Setter
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article/list")
    public String list(Model model) {
        model.addAttribute("articleList", this.articleService.getAll());
        return "article_list";
    }
}
