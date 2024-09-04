package com.mysite.sbb.Comment;

import com.mysite.sbb.Article.Article;
import com.mysite.sbb.Comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public void createComment(Article article, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setArticle(article);
        this.commentRepository.save(comment);
    }
}
