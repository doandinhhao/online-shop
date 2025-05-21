package com.yashmerino.online.shop.controllers;


import com.yashmerino.online.shop.model.Category;
import com.yashmerino.online.shop.model.dto.CategoryDTO;
import com.yashmerino.online.shop.services.interfaces.CategoryService;
import com.yashmerino.online.shop.swagger.SwaggerConfig;
import com.yashmerino.online.shop.swagger.SwaggerHttpStatus;
import com.yashmerino.online.shop.swagger.SwaggerMessages;
import com.yashmerino.online.shop.utils.RequestBodyToEntityConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for categories.
 */
@Tag(name = "5. Categories", description = "These endpoints are used to control categories.")
@SecurityRequirement(name = SwaggerConfig.SECURITY_SCHEME_NAME)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    /**
     * Categories' service.
     */
    private CategoryService categoryService;

    /**
     * Constructor.
     *
     * @param categoryService is the categories' service.
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Retrieves all the categories.
     *
     * @return <code>Set of Categories</code>
     */
    @Operation(summary = "Retrieves all the categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerHttpStatus.OK, description = SwaggerMessages.CATEGORIES_SUCCESSFULLY_RETRIEVED,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.INTERNAL_SERVER_ERROR, description = SwaggerMessages.INTERNAL_SERVER_ERROR,
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<Category> categories = categoryService.getCategories();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();

        for (Category category : categories) {
            categoriesDTO.add(RequestBodyToEntityConverter.convertToCategoryDTO(category));
        }

        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }
}
