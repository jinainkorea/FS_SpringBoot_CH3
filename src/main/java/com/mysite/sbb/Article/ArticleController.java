package com.mysite.sbb.Article;

import com.mysite.sbb.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("article")
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
  public String create(ArticleForm articleForm) {
    return "article_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/create")
  public String create(@Valid ArticleForm articleForm, BindingResult bindingResult, Principal principal) {
    if (bindingResult.hasErrors()) {
      return "article_form";
    }
    this.articleService.createArticle(articleForm.getTitle(), articleForm.getContent(), this.userService.getUser(principal.getName()));
    return "redirect:/";
  }

  @GetMapping("/detail/{id}")
  public String detail(Model model, @PathVariable("id")Integer id) {
    model.addAttribute(this.articleService.getArticleById(id));
    return "article_detail";
  }
}
