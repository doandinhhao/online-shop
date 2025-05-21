package com.yashmerino.online.shop.services;

  
import com.yashmerino.online.shop.exceptions.CouldntUploadPhotoException;
import com.yashmerino.online.shop.model.Cart;
import com.yashmerino.online.shop.model.CartItem;
import com.yashmerino.online.shop.model.Product;
import com.yashmerino.online.shop.model.User;
import com.yashmerino.online.shop.model.dto.ProductDTO;
import com.yashmerino.online.shop.repositories.CartItemRepository;
import com.yashmerino.online.shop.repositories.ProductRepository;
import com.yashmerino.online.shop.services.interfaces.ProductService;
import com.yashmerino.online.shop.services.interfaces.UserService;
import com.yashmerino.online.shop.utils.RequestBodyToEntityConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Implementation for product service.
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * Product repository.
     */
    private final ProductRepository productRepository;

    /**
     * User service.
     */
    private final UserService userService;

    /**
     * Cart item repository.
     */
    private final CartItemRepository cartItemRepository;

    /**
     * Constructor to inject dependencies.
     *
     * @param productRepository  is the product repository.
     * @param userService        is the user service.
     * @param cartItemRepository is the cart item repository.
     */
    public ProductServiceImpl(ProductRepository productRepository, UserService userService, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
    }

    /**
     * Returns the product.
     *
     * @param id is the product's id.
     * @return <code>Optional of Product</code>.
     */
    @Override
    public Product getProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new EntityNotFoundException("Product couldn't be found!");
        }
    }

    /**
     * Returns all the products.
     *
     * @return <code>List of Products</code>
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Saves a product.
     *
     * @param product is the product's object.
     */
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    /**
     * Deletes a product.
     *
     * @param id is the product's id.
     */
    @Override
    public void delete(Long id) {
        Product product = this.getProduct(id);

        productRepository.deleteById(product.getId());
    }

    /**
     * Returns seller's products.
     *
     * @param username is the seller's username.
     * @return List of Products.
     */
    @Override
    public List<Product> getSellerProducts(String username) {
        User user = userService.getByUsername(username);

        Long userId = user.getId();
        return productRepository.getProductsBySellerId(userId);
    }

    /**
     * Add product to the cart.
     *
     * @param id       is the product's id.
     * @param quantity is the quantity.
     */
    @Override
    public void addProductToCart(final Long id, final Integer quantity) {
        Product product = this.getProduct(id);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getByUsername(userDetails.getUsername());

        Cart cart = user.getCart();

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setName(product.getName());
        cartItem.setPrice(product.getPrice());
        cartItemRepository.save(cartItem);

        product.linkCartItem(cartItem);
        productRepository.save(product);
    }

    /**
     * Adds a new product.
     *
     * @param productDTO is the product DTO.
     * @return the product's id.
     */
    @Override
    public Long addProduct(ProductDTO productDTO) {
        Product product = RequestBodyToEntityConverter.convertToProduct(productDTO);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getByUsername(userDetails.getUsername());
        product.setUser(user);
        productRepository.save(product);

        return product.getId();
    }

    /**
     * Updates product photo.
     *
     * @param id    is the product's id.
     * @param photo is the product's photo.
     */
    @Override
    public void updatePhoto(Long id, MultipartFile photo) {
        Product product = this.getProduct(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getByUsername(username);

        if (!product.getUser().getUsername().equals(user.getUsername())) {
            throw new AccessDeniedException("access_denied");
        }

        try {
            product.setPhoto(photo.getBytes());
        } catch (IOException e) {
            throw new CouldntUploadPhotoException("product_photo_not_uploaded");
        }

        productRepository.save(product);
    }

    /**
     * Updates product.
     *
     * @param id         is the product's id.
     * @param productDTO is the product DTO.
     */
    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        Product product = this.getProduct(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();
        String productSellerUsername = product.getUser().getUsername();

        if (!currentUserUsername.equals(productSellerUsername)) {
            throw new AccessDeniedException("access_denied");
        }

        product.setName(productDTO.getName());
        product.setCategories(productDTO.getCategories());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());

        productRepository.save(product);
    }
}
