package com.std.basic1.Article;

import com.std.basic1.SiteUser.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping("article")
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String create(Model model, ArticleForm articleForm) {
        return "article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@Valid ArticleForm articleForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "article_form";
        }
        this.articleService.createArticle(articleForm.getTitle(), articleForm.getContent(), this.userService.getUser(principal.getName()));
        return "redirect:/article/list";
    }
}
