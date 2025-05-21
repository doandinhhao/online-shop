package com.yashmerino.online.shop.services.stubs;
  
import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.settings.IndexSettings;
import com.yashmerino.online.shop.conditions.AlgoliaServiceCondition;
import com.yashmerino.online.shop.conditions.AlgoliaServiceStubCondition;
import com.yashmerino.online.shop.model.Product;
import com.yashmerino.online.shop.model.dto.ProductDTO;
import com.yashmerino.online.shop.services.interfaces.AlgoliaService;
import com.yashmerino.online.shop.utils.ApplicationProperties;
import com.yashmerino.online.shop.utils.RequestBodyToEntityConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Algolia service stub if no Algolia search is used.
 */
@Service
@Conditional(AlgoliaServiceStubCondition.class)
@Slf4j
public class AlgoliaServiceStub implements AlgoliaService {
    /**
     * Constructor.
     *
     * @param applicationProperties is the Application's properties.
     */
    public AlgoliaServiceStub(ApplicationProperties applicationProperties) {
        // Stub
    }

    /**
     * Populates index with passed products.
     *
     * @param products is the list of products to add to the index.
     */
    public void populateIndex(List<Product> products) {
        // Stub
    }

    /**
     * Adds a product to the index.
     *
     * @param productDTO is the product to add.
     */
    public void addProductToIndex(ProductDTO productDTO) {
        // Stub
    }

    /**
     * Removes a product from the index.
     *
     * @param productId is the product's id to delete.
     */
    public void deleteProductFromIndex(Long productId) {
        // Stub
    }

    /**
     * Updates a product.
     *
     * @param productDTO is the product's DTO.
     */
    public void updateProduct(ProductDTO productDTO) {
        // Stub
    }
}
