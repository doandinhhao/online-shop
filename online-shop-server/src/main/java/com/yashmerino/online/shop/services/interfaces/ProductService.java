package com.yashmerino.online.shop.services.interfaces;

  
import com.yashmerino.online.shop.model.Product;
import com.yashmerino.online.shop.model.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Interface for product service.
 */
public interface ProductService {

    /**
     * Returns the product.
     *
     * @param id is the product's id.
     * @return <code>Product</code>
     */
    Product getProduct(final Long id);

    /**
     * Returns all the products.
     *
     * @return <code>List of Products</code>
     */
    List<Product> getAllProducts();

    /**
     * Saves a product.
     *
     * @param product is the product's object.
     */
    void save(final Product product);

    /**
     * Deletes a product.
     *
     * @param id is the product's id.
     */
    void delete(final Long id);

    /**
     * Returns seller's products.
     *
     * @param username is the seller's username.
     * @return List of Products.
     */
    List<Product> getSellerProducts(String username);

    /**
     * Add product to the cart.
     *
     * @param id       is the product's id.
     * @param quantity is the quantity.
     */
    void addProductToCart(final Long id, final Integer quantity);

    /**
     * Adds a new product.
     *
     * @param productDTO is the product DTO.
     * @return the product's id.
     */
    Long addProduct(final ProductDTO productDTO);

    /**
     * Updates product photo.
     *
     * @param id    is the product's id.
     * @param photo is the product's photo.
     */
    void updatePhoto(Long id, MultipartFile photo);

    /**
     * Updates product.
     *
     * @param id         is the product's id.
     * @param productDTO is the product DTO.
     */
    void updateProduct(Long id, ProductDTO productDTO);
}
