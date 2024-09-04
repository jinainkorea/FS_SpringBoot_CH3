package com.mysite.sbb;

import com.mysite.sbb.Article.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Test
    void testJpa() {
        for (int i = 0; i <= 50; i++) {
            String title = String.format("테스트 글 : %d", i);
            String content = "내용없음";
            this.articleService.createArticle(title, content);
        }
    }

}
