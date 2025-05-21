package com.yashmerino.online.shop.services.interfaces;
  
import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.settings.IndexSettings;
import com.yashmerino.online.shop.conditions.AlgoliaServiceCondition;
import com.yashmerino.online.shop.model.Product;
import com.yashmerino.online.shop.model.dto.ProductDTO;
import com.yashmerino.online.shop.utils.ApplicationProperties;
import com.yashmerino.online.shop.utils.RequestBodyToEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for Algolia service.
 */
public interface AlgoliaService {
    /**
     * Populates index with passed products.
     *
     * @param products is the list of products to add to the index.
     */
    void populateIndex(List<Product> products);

    /**
     * Adds a product to the index.
     *
     * @param productDTO is the product to add.
     */
    void addProductToIndex(ProductDTO productDTO);

    /**
     * Removes a product from the index.
     *
     * @param productId is the product's id to delete.
     */
    void deleteProductFromIndex(Long productId);

    /**
     * Updates a product.
     *
     * @param productDTO is the product's DTO.
     */
    void updateProduct(ProductDTO productDTO);
}
