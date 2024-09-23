package com.mysite.sbb.Article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("article")
public class ArticleController {
  private final ArticleService articleService;

  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("articleList", this.articleService.list());
    return "article_list";
  }

  @GetMapping("/create")
  public String create(ArticleForm articleForm) {
    return "article_form";
  }

  @PostMapping("/create")
  public String create(ArticleForm articleForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "article_form";
    }
    this.articleService.createArticle(articleForm.getTitle(), articleForm.getContent());
    return "redirect:/";
  }

  @GetMapping("/detail/{id}")
  public String detail(Model model, @PathVariable("id")Integer id) {
    model.addAttribute(this.articleService.getArticleById(id));
    return "article_detail";
  }
}
