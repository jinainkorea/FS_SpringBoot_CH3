package com.mysite.sbb.Article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
