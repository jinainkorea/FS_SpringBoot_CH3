package com.mysite.sbb3.Article;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb3.User.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> list(String kw) {
        Specification<Article> spec = search(kw);
        return this.articleRepository.findAll(spec);
    }

    public void create(String title, String content, SiteUser author) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setAuthor(author);
        this.articleRepository.save(article);
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

    private Specification<Article> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Article> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Article, SiteUser> u1 = q.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("title"), "%" + kw + "%"), // 제목
                    cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                    cb.like(u1.get("username"), "%" + kw + "%"));    // 질문 작성자
            }
        };
    }

    public Article getArticleById(Integer id) {
        return this.articleRepository.findById(id).orElse(null);
    }
}
