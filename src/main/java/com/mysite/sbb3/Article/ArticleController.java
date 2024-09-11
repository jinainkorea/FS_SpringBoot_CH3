package com.mysite.sbb3.Article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("articleList", this.articleService.list());
        return "article_list";
    }

    @GetMapping("/create")
    private String create() {
        return "article_form";
    }

    @PostMapping("/create")
    public String create(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
        this.articleService.create(title, content);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("article", this.articleService.getArticleById(id));
        return "article_detail";
    }
}
