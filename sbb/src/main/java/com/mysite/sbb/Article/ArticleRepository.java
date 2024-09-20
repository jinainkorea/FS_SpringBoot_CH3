package com.mysite.sbb.Article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Article findByTitle(String subject);
    Article findByTitleAndContent(String subject, String content);
    List<Article> findByTitleLike(String subject);
    Page<Article> findAll(Pageable pagement);
    Page<Article> findAll(Specification<Article> spec, Pageable pageable);
    @Query("select "
        + "distinct a "
        + "from Article a "
        + "left outer join SiteUser u1 on a.author=u1 "
        + "left outer join Comment c on c.article=a "
        + "left outer join SiteUser u2 on c.author=u2 "
        + "left outer join Category ca on ca=a.category "
        + "where "
        + "     (a.title like %:kw% "
        + "     or a.content like %:kw% "
        + "     or u1.username like %:kw% "
        + "     or c.content like %:kw% "
        + "     or u2.username like %:kw%) "
        + "     and (:cid = 0 or ca.id = :cid)"
    )
    Page<Article> findAllByKeyword(@Param("kw") String kw, Pageable pageable, @Param("cid") Integer cid);
}
