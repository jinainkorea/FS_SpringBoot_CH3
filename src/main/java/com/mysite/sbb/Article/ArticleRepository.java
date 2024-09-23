package com.mysite.sbb.Article;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
  List<Article> findAll(Specification<Article> spec);
}
