package com.yashmerino.online.shop.services.interfaces;

  
import com.yashmerino.online.shop.model.Category;

import java.util.List;

/**
 * Interface for category service.
 */
public interface CategoryService {

    /**
     * Returns list of categories.
     *
     * @return a list of categories.
     */
    List<Category> getCategories();
}
