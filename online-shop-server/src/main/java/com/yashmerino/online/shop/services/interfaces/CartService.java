package com.yashmerino.online.shop.services.interfaces;

  
import com.yashmerino.online.shop.model.Cart;

import java.util.Optional;

/**
 * Interface for cart service.
 */
public interface CartService {

    /**
     * Returns the cart.
     *
     * @param id is the cart's id.
     */
    Optional<Cart> getCart(final Long id);

    /**
     * Saves a cart.
     *
     * @param cart is the cart to save.
     */
    void save(final Cart cart);

}
