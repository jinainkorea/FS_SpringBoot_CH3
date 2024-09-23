package com.mysite.sbb.Article;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.User.SiteUser;
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

  public void createArticle(String title, String content, SiteUser author) {
    Article article = new Article();
    article.setTitle(title);
    article.setContent(content);
    article.setCreateDate(LocalDateTime.now());
    article.setAuthor(author);
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

  public void modify(Article article, String title, String content) {
    article.setTitle(title);
    article.setContent(content);
    article.setModifyDate(LocalDateTime.now());
    this.articleRepository.save(article);
  }

  public void delete(Article article) {
    this.articleRepository.delete(article);
  }
}
