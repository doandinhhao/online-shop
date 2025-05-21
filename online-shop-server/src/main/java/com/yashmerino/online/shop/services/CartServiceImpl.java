package com.yashmerino.online.shop.services;

  
import com.yashmerino.online.shop.model.Cart;
import com.yashmerino.online.shop.repositories.CartRepository;
import com.yashmerino.online.shop.services.interfaces.CartService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation for cart service.
 */
@Service
public class CartServiceImpl implements CartService {

    /**
     * Cart repository.
     */
    private final CartRepository cartRepository;

    /**
     * Constructor to inject dependencies.
     *
     * @param cartRepository is the cart repository.
     */
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    /**
     * Returns the cart.
     *
     * @param id is the cart's id.
     * @return <code>Optional of Cart</code>.
     */
    @Override
    public Optional<Cart> getCart(Long id) {
        return cartRepository.findById(id);
    }

    /**
     * Saves a cart.
     *
     * @param cart is the cart to save.
     */
    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
