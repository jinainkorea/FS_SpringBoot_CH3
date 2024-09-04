package com.mysite.sbb.Comment;

import com.mysite.sbb.Article.Article;
import com.mysite.sbb.Article.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("comment")
@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;

    @PostMapping("/create/{id}")
    public String create(Model model, @PathVariable("id") Integer id, @Valid CommentForm commentForm, BindingResult bindingResult) {
        Article article = this.articleService.getArticleById(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("article", article);
            return "article_detail";
        }
        this.commentService.createComment(article, commentForm.getContent());
        return String.format("redirect:/article/detail/%s", id);
    }


}
