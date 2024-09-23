package com.mysite.sbb.Article;

import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleRepository articleRepository;

  public List<Article> list() {
    return this.articleRepository.findAll();
  }

  public void createArticle(String title, String content) {
    Article article = new Article();
    article.setTitle(title);
    article.setContent(content);
    article.setCreateDate(LocalDateTime.now());
    this.articleRepository.save(article);
  }

  public Article getArticleById(Integer id) {
    Optional<Article> article = this.articleRepository.findById(id);
    if (article.isPresent()) {
      return article.get();
    } else {
      throw new DataNotFoundException("Article not found");
    }
  }
}
