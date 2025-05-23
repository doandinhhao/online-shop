package com.yashmerino.online.shop.controllers;

import com.yashmerino.online.shop.model.CartItem;
import com.yashmerino.online.shop.model.dto.CartItemDTO;
import com.yashmerino.online.shop.model.dto.SuccessDTO;
import com.yashmerino.online.shop.services.interfaces.CartItemService;
import com.yashmerino.online.shop.swagger.SwaggerConfig;
import com.yashmerino.online.shop.swagger.SwaggerHttpStatus;
import com.yashmerino.online.shop.swagger.SwaggerMessages;
import com.yashmerino.online.shop.utils.RequestBodyToEntityConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Cart item's controller.
 */
@Tag(name = "3. Cart Items Controller", description = "These endpoints are used to perform actions on cart items.")
@SecurityRequirement(name = SwaggerConfig.SECURITY_SCHEME_NAME)
@RestController
@RequestMapping("/api/cartItem")
@Validated
public class CartItemController {

    /**
     * Cart items' service.
     */
    private final CartItemService cartItemService;

    /**
     * Constructor.
     *
     * @param cartItemService is the cart items' service.
     */
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    /**
     * Adds a cart item.
     *
     * @param id is the cart item's id.
     * @return <code>ResponseEntity</code>
     */
    @Operation(summary = "Deletes a cart item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerHttpStatus.OK, description = SwaggerMessages.ITEM_SUCCESSFULLY_DELETED,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.BAD_REQUEST, description = SwaggerMessages.BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.FORBIDDEN, description = SwaggerMessages.FORBIDDEN,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.UNAUTHORIZED, description = SwaggerMessages.UNAUTHORIZED,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.INTERNAL_SERVER_ERROR, description = SwaggerMessages.INTERNAL_SERVER_ERROR,
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDTO> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);

        SuccessDTO successDTO = new SuccessDTO();
        successDTO.setStatus(200);
        successDTO.setMessage("cartitem_deleted_successfully");

        return new ResponseEntity<>(successDTO, HttpStatus.OK);
    }

    /**
     * Change the quantity of a cart's item.
     *
     * @param id is the cart item's id.
     * @return <code>ResponseEntity</code>
     */
    @Operation(summary = "Changes the quantity of an item in the cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerHttpStatus.OK, description = SwaggerMessages.QUANTITY_SUCCESSFULLY_CHANGED,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.BAD_REQUEST, description = SwaggerMessages.BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.FORBIDDEN, description = SwaggerMessages.FORBIDDEN,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.UNAUTHORIZED, description = SwaggerMessages.UNAUTHORIZED,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.INTERNAL_SERVER_ERROR, description = SwaggerMessages.INTERNAL_SERVER_ERROR,
                    content = @Content)})
    @PostMapping("/{id}/quantity")
    public ResponseEntity<SuccessDTO> changeQuantity(@PathVariable Long id, @Min(value = 1, message = "Quantity should be greater or equal to 1.") @RequestParam Integer quantity) {
        cartItemService.changeQuantity(id, quantity);

        SuccessDTO successDTO = new SuccessDTO();
        successDTO.setStatus(200);
        successDTO.setMessage("quantity_changed_successfully");

        return new ResponseEntity<>(successDTO, HttpStatus.OK);
    }

    /**
     * Returns a cart item.
     *
     * @param id is the cart item's id.
     * @return <code>ResponseEntity</code>
     * @throws EntityNotFoundException if cart item couldn't be found.
     */
    @Operation(summary = "Returns an item from the cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerHttpStatus.OK, description = SwaggerMessages.RETURNED_CART_ITEM,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CartItemDTO.class))}),
            @ApiResponse(responseCode = SwaggerHttpStatus.BAD_REQUEST, description = SwaggerMessages.BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.FORBIDDEN, description = SwaggerMessages.FORBIDDEN,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.UNAUTHORIZED, description = SwaggerMessages.UNAUTHORIZED,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.INTERNAL_SERVER_ERROR, description = SwaggerMessages.INTERNAL_SERVER_ERROR,
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<CartItemDTO> getCartItem(@PathVariable Long id) {
        CartItem cartItem = cartItemService.getCartItem(id);
        CartItemDTO cartItemDTO = RequestBodyToEntityConverter.convertToCartItemDTO(cartItem);

        return new ResponseEntity<>(cartItemDTO, HttpStatus.OK);
    }

    /**
     * Returns all the cart items.
     *
     * @param username is the user's username.
     * @return <code>List of CartItems</code>
     */
    @Operation(summary = "Returns all the items from the cart.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerHttpStatus.OK, description = SwaggerMessages.RETURNED_CART_ITEM,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.BAD_REQUEST, description = SwaggerMessages.BAD_REQUEST,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.FORBIDDEN, description = SwaggerMessages.FORBIDDEN,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.UNAUTHORIZED, description = SwaggerMessages.UNAUTHORIZED,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.INTERNAL_SERVER_ERROR, description = SwaggerMessages.INTERNAL_SERVER_ERROR,
                    content = @Content)})
    @GetMapping
    public List<CartItemDTO> getCartItems(@RequestParam String username) {
        Set<CartItem> cartItemsSet = cartItemService.getCartItems(username);
        List<CartItemDTO> cartItems = new ArrayList<>();

        for (CartItem cartItem : cartItemsSet) {
            cartItems.add(RequestBodyToEntityConverter.convertToCartItemDTO(cartItem));
        }

        return cartItems;
    }
}
