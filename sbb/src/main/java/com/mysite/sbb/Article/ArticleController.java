package com.mysite.sbb.Article;

import com.mysite.sbb.Article.ArticleForm;
import java.util.List;
import java.util.Optional;

import com.mysite.sbb.Comment.CommentForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("article")
@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Article> paging = this.articleService.getList(page);
        model.addAttribute("paging", paging);
        return "article_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Integer id, CommentForm commentForm) {
        Article article = this.articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @GetMapping("/create")
    public String create(Model model, ArticleForm articleForm) {
        return "article_form";
    }

    @PostMapping("/create")
    public String create(@Valid ArticleForm articleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article_form";
        }
        this.articleService.createArticle(articleForm.getTitle(), articleForm.getContent());
        return "redirect:/article/list";
    }


}
