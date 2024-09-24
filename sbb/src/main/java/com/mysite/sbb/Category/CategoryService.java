package com.mysite.sbb.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> list() {return this.categoryRepository.findAll();}

    public Category getCategory(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            Category defaultCategory = new Category();
            defaultCategory.setId(0);
            defaultCategory.setName("전체");
            return defaultCategory;
        }
        return category.get();
    }
}
