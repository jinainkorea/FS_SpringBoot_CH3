package com.mysite.sbb.Article;

import com.mysite.sbb.Category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice
@RequiredArgsConstructor
public class ArticleGlobalController {
  private final CategoryService categoryService;

  @ModelAttribute
  public void addCategpryList(Model model, @RequestParam(value="cid", defaultValue = "0")Integer cid) {
    model.addAttribute("categoryList", this.categoryService.list());
    model.addAttribute("selectedCategory", this.categoryService.getCategory(cid));
  }
}
