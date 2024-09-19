package com.mysite.sbb.Article;

import com.mysite.sbb.Category.Category;
import com.mysite.sbb.Comment.Comment;
import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.User.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    private Specification<Article> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Article> a, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Article, SiteUser> u1 = a.join("author", JoinType.LEFT);
                Join<Article, Comment> c = a.join("commentList", JoinType.LEFT);
                Join<Comment, SiteUser> u2 = c.join("author", JoinType.LEFT);
                return cb.or(cb.like(a.get("title"), "%" + kw + "%"), // 제목
                    cb.like(a.get("content"), "%" + kw + "%"), // 내용
                    cb.like(u1.get("username"), "%" + kw + "%"), // 글 작성자
                    cb.like(c.get("content"), "%" + kw + "%"), // 답변 내용
                    cb.like(u2.get("username"), "%" + kw + "%")); // 답변 작성자
            }
        };
    }

    public List<Article> getArticleList() {
        return this.articleRepository.findAll();
    }

    public Article getArticleById(Integer id) {
        Optional<Article> article = this.articleRepository.findById(id);
        if (article.isPresent()) {
            return article.get();
        } else {
            throw new DataNotFoundException("Article Not Found");
        }
    }

    public void createArticle(String title, String content, SiteUser user, Category category) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setAuthor(user);
        article.setCategory(category);
        this.articleRepository.save(article);
    }

    public Page<Article> getList(int page, String kw, Integer cid) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
//        Specification<Article> spec = search(kw);
        return this.articleRepository.findAllByKeyword(kw, cid, pageable);
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

    public void vote(Article article, SiteUser siteUser) {
        article.getVoter().add(siteUser);
        this.articleRepository.save(article);
    }
}
