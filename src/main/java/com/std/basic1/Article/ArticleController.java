package com.std.basic1.Article;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping("article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("articleList", this.articleService.getAll());
        return "article_list";
    }

    @GetMapping(value="/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("article", this.articleService.getArticle(id));
        return "article_detail";
    }

    @GetMapping("/create")
    public String create(Model model, ArticleForm articleForm) {
        return "article_form";
    }

    @PostMapping("/create")
    public String create(@Valid ArticleForm articleForm) {
        this.articleService.createArticle(articleForm.getTitle(), articleForm.getContent());
        return "redirect:/article/list";
    }
}
