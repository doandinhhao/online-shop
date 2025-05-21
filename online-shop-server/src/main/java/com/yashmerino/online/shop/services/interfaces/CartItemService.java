package com.yashmerino.online.shop.services.interfaces;

  
import com.yashmerino.online.shop.model.CartItem;

import java.util.Set;

/**
 * Interface for cart item service.
 */
public interface CartItemService {

    /**
     * Deletes a cart item.
     *
     * @param id is the cart item's id.
     */
    void deleteCartItem(final Long id);

    /**
     * Changes the quantity of a cart item.
     *
     * @param id       is the cart item's id.
     * @param quantity is the cart item's quantity.
     */
    void changeQuantity(final Long id, final Integer quantity);

    /**
     * Returns a cart item.
     *
     * @param id is the cart item's id.
     * @return <code>CartItem</code>
     */
    CartItem getCartItem(final Long id);

    /**
     * Returns all the cart items.
     *
     * @param username is the user's username..
     * @return <code>List of CartItem</code>
     */
    Set<CartItem> getCartItems(final String username);

    /**
     * Saves cart item.
     *
     * @param cartItem is the cart item.
     */
    void save(final CartItem cartItem);
}
