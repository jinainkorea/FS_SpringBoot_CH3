package com.mysite.sbb3.Article;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
  List<Article> findByKw(Specification<Article> spec);
}
