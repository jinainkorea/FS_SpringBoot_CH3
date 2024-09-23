package com.mysite.sbb.Article;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.User.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleRepository articleRepository;

  private Specification<Article> search(String kw) {
    return new Specification<Article>() {
      private static final long serialVersionUID = 1L;
      @Override
      public Predicate toPredicate(Root<Article> a, CriteriaQuery<?> query, CriteriaBuilder cb) {
        query.distinct(true);
        Join<Article, SiteUser> u1 = a.join("author", JoinType.LEFT);
        return cb.or(cb.like(a.get("title"), "%" + kw + "%"),
                cb.like(a.get("content"), "%" + kw + "%"),
                cb.like(u1.get("username"), "%" + kw + "%"))
            ;
      }
    };
  }

  public List<Article> list(String kw) {
    Specification<Article> spec = search(kw);
    return this.articleRepository.findAll(spec);
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
