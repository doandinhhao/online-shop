package com.yashmerino.online.shop.services;

  
import com.yashmerino.online.shop.model.Category;
import com.yashmerino.online.shop.repositories.CategoryRepository;
import com.yashmerino.online.shop.services.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation for category service.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    /**
     * Category repository.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Constructor to inject dependencies.
     *
     * @param categoryRepository is the category repository.
     */
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Returns list of categories.
     *
     * @return a list of categories.
     */
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
