package com.mysite.sbb3.Article;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb3.User.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> list() {
        return articleRepository.findAll();
    }

    public void create(String title, String content, SiteUser author) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setAuthor(author);
        this.articleRepository.save(article);
    }

    public Article getArticleById(Integer id) {
        return this.articleRepository.findById(id).orElse(null);
    }
}
