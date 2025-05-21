package com.yashmerino.online.shop.utils;
  
import com.yashmerino.online.shop.model.CartItem;
import com.yashmerino.online.shop.model.Category;
import com.yashmerino.online.shop.model.Product;
import com.yashmerino.online.shop.model.dto.CartItemDTO;
import com.yashmerino.online.shop.model.dto.CategoryDTO;
import com.yashmerino.online.shop.model.dto.ProductDTO;

/**
 * Utils class that converts request body to an entity;
 */
public class RequestBodyToEntityConverter {

    /**
     * Private constructor to now allow instantiation.
     */
    private RequestBodyToEntityConverter() {

    }

    /**
     * Converts productDTO to product entity.
     *
     * @param productDTO is the product DTO.
     * @return <code>Product</code>
     */
    public static Product convertToProduct(final ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategories(productDTO.getCategories());

        return product;
    }

    /**
     * Converts a product to ProductDTO.
     *
     * @param product is the product entity.
     * @return <code>ProductDTO</code>
     */
    public static ProductDTO convertToProductDTO(final Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId().toString());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategories(product.getCategories());

        return productDTO;
    }

    /**
     * Converts a cart item entity to cart item DTO.
     *
     * @param cartItem is the cart item entity.
     * @return <code>CartItemDTO</code>
     */
    public static CartItemDTO convertToCartItemDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setCartId(cartItem.getCart().getId());
        cartItemDTO.setName(cartItem.getName());
        cartItemDTO.setPrice(cartItem.getPrice());
        cartItemDTO.setProductId(cartItem.getProduct().getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());

        return cartItemDTO;
    }

    /**
     * Converts a category entity to category DTO.
     *
     * @param category is the category entity.
     * @return <code>CategoryDTO</code>
     */
    public static CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }
}
