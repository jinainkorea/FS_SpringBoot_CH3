package com.mysite.sbb.Article;

import com.mysite.sbb.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("article")
public class ArticleController {
  private final ArticleService articleService;
  private final UserService userService;

  @GetMapping("/list")
  public String list(Model model, @RequestParam(value="kw", defaultValue = "")String kw) {
    model.addAttribute("articleList", this.articleService.list(kw));
    model.addAttribute("kw", kw);
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

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/modify/{id}")
  public String modify(ArticleForm articleForm, @PathVariable("id")Integer id, Principal principal) {
    Article article = this.articleService.getArticleById(id);
    if (!article.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
    }
    articleForm.setTitle(article.getTitle());
    articleForm.setContent(article.getContent());
    return "article_form";
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/modify/{id}")
  public String modify(@Valid ArticleForm articleForm, @PathVariable("id")Integer id, BindingResult bindingResult, Principal principal) {
    if (bindingResult.hasErrors()) {
      return "article_form";
    }
    Article article = this.articleService.getArticleById(id);
    if (!article.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
    }
    this.articleService.modify(article, articleForm.getTitle(), articleForm.getContent());
    return String.format("redirect:/article/detail/%s", id);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/delete/{id}")
  public String delete(@PathVariable("id")Integer id, Principal principal) {
    Article article = this.articleService.getArticleById(id);
    if (!article.getAuthor().getUsername().equals(principal.getName())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
    }
    this.articleService.delete(article);
    return "redirect:/";
  }
}
