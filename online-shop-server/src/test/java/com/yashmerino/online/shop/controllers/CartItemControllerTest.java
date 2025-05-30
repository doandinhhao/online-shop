package com.yashmerino.online.shop.controllers;
  
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link CartItemController}
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
class CartItemControllerTest {

    /**
     * Mock mvc to perform requests.
     */
    @Autowired
    private MockMvc mvc;

    /**
     * Test get cart item.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    void getCartItemTest() throws Exception {
        MvcResult result = mvc.perform(get("/api/cartItem/1")).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("{\"id\":1,\"productId\":1,\"name\":\"Phone\",\"price\":5.0,\"cartId\":1,\"quantity\":1}"));
    }

    /**
     * Test get cart item with wrong user.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    @WithMockUser(username = "anotherUser", authorities = {"USER"})
    void getCartItemWrongUserTest() throws Exception {
        MvcResult result = mvc.perform(get("/api/cartItem/1")).andExpect(status().isForbidden()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(",\"status\":403,\"error\":\"access_denied\"}"));
    }

    /**
     * Test delete cart item.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    void deleteCartItemTest() throws Exception {
        MvcResult result = mvc.perform(delete("/api/cartItem/1")).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("{\"status\":200,\"message\":\"cartitem_deleted_successfully\"}"));
    }

    /**
     * Test delete cart item as wrong user.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    @WithMockUser(username = "anotherUser", authorities = {"USER"})
    void deleteCartItemWrongUserTest() throws Exception {
        MvcResult result = mvc.perform(delete("/api/cartItem/1")).andExpect(status().isForbidden()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(",\"status\":403,\"error\":\"access_denied\"}"));
    }

    /**
     * Test change item's quantity.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    void changeQuantityTest() throws Exception {
        MvcResult result = mvc.perform(post("/api/cartItem/1/quantity?quantity=5")).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("{\"status\":200,\"message\":\"quantity_changed_successfully\"}"));

        result = mvc.perform(get("/api/cartItem/1")).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("{\"id\":1,\"productId\":1,\"name\":\"Phone\",\"price\":5.0,\"cartId\":1,\"quantity\":5}"));
    }

    /**
     * Test change item's quantity as wrong user.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    @WithMockUser(username = "anotherUser", authorities = {"USER"})
    void changeQuantityWrongUserTest() throws Exception {
        MvcResult result = mvc.perform(post("/api/cartItem/1/quantity?quantity=5")).andExpect(status().isForbidden()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(",\"status\":403,\"error\":\"access_denied\"}"));
    }

    /**
     * Test get cart items.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    void getCartItemsTest() throws Exception {
        MvcResult result = mvc.perform(get("/api/cartItem?username=user")).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("[{\"id\":1,\"productId\":1,\"name\":\"Phone\",\"price\":5.0,\"cartId\":1,\"quantity\":1}]"));
    }

    /**
     * Test get cart items with wrong user.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    @WithMockUser(username = "anotherUser", authorities = {"USER"})
    void getCartItemsWrongUserTest() throws Exception {
        MvcResult result = mvc.perform(get("/api/cartItem?username=user")).andExpect(status().isForbidden()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(",\"status\":403,\"error\":\"access_denied\"}"));
    }

    /**
     * Test change item's quantity to negative value.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    void changeQuantityToNegativeTest() throws Exception {
        MvcResult result = mvc.perform(post("/api/cartItem/1/quantity?quantity=-4")).andExpect(status().isBadRequest()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("{\"fieldErrors\":[{\"field\":\"changeQuantity.quantity\",\"message\":\"Quantity should be greater or equal to 1.\"}]}"));
    }
}
