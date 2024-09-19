package com.mysite.sbb.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category getCategory(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            return null;
        }
        return category.get();
    }
}
