package com.mysite.sbb3.Article;

import com.mysite.sbb3.User.SiteUser;
import com.mysite.sbb3.User.UserRepository;
import com.mysite.sbb3.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("articleList", this.articleService.list());
        return "article_list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    private String create() {
        return "article_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content, Principal principal) {
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.articleService.create(title, content, siteUser);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("article", this.articleService.getArticleById(id));
        return "article_detail";
    }
}
