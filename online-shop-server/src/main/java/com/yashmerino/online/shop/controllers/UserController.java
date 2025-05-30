package com.yashmerino.online.shop.controllers;


import com.yashmerino.online.shop.model.dto.SuccessDTO;
import com.yashmerino.online.shop.model.dto.auth.UserDTO;
import com.yashmerino.online.shop.model.dto.auth.UserInfoDTO;
import com.yashmerino.online.shop.services.interfaces.AuthService;
import com.yashmerino.online.shop.services.interfaces.UserService;
import com.yashmerino.online.shop.swagger.SwaggerConfig;
import com.yashmerino.online.shop.swagger.SwaggerHttpStatus;
import com.yashmerino.online.shop.swagger.SwaggerMessages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for users.
 */
@Tag(name = "1. User", description = "These endpoints are used to manipulate users.")
@SecurityRequirement(name = SwaggerConfig.SECURITY_SCHEME_NAME)
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    /**
     * Users' service.
     */
    private final UserService userService;

    /**
     * Controller.
     *
     * @param userService is the user's service.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Get user's information by username.
     *
     * @param username is the user's username.
     * @return <code>ResponseEntity</code>
     */
    @Operation(summary = "Returns user info.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerHttpStatus.OK, description = SwaggerMessages.USER_INFO_IS_RETURNED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserInfoDTO.class))}),
            @ApiResponse(responseCode = SwaggerHttpStatus.NOT_FOUND, description = SwaggerMessages.USER_DOES_NOT_EXIST,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.INTERNAL_SERVER_ERROR, description = SwaggerMessages.INTERNAL_SERVER_ERROR,
                    content = @Content)})
    @GetMapping("/{username}")
    public ResponseEntity<UserInfoDTO> getUserInfo(@Parameter(description = "User's username.") @PathVariable String username) {
        UserInfoDTO userInfoDTO = userService.getUserInfo(username);

        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }

    /**
     * Sets user's photo.
     *
     * @param username is the user's username.
     * @param photo    is the user's photo.
     * @return <code>ResponseEntity</code>
     */
    @Operation(summary = "Updates user photo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerHttpStatus.OK, description = SwaggerMessages.USER_PHOTO_IS_UPDATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SuccessDTO.class))}),
            @ApiResponse(responseCode = SwaggerHttpStatus.NOT_FOUND, description = SwaggerMessages.USER_DOES_NOT_EXIST,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.INTERNAL_SERVER_ERROR, description = SwaggerMessages.INTERNAL_SERVER_ERROR,
                    content = @Content)})
    @PostMapping(path = "/{username}/photo", consumes = "multipart/form-data")
    public ResponseEntity<SuccessDTO> setUserPhoto(@PathVariable String username, @Parameter(description = "User's photo.", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestPart("photo") MultipartFile photo) {
        userService.updatePhoto(username, photo);

        SuccessDTO successDTO = new SuccessDTO();
        successDTO.setStatus(200);
        successDTO.setMessage("user_photo_updated_successfully");

        return new ResponseEntity<>(successDTO, HttpStatus.OK);
    }

    /**
     * Returns user's photo as array of bytes.
     *
     * @param username is the user's username.
     * @return <code>ResponseEntity</code>
     */
    @Operation(summary = "Returns user photo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerHttpStatus.OK, description = SwaggerMessages.USER_PHOTO_RETURNED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = byte[].class))}),
            @ApiResponse(responseCode = SwaggerHttpStatus.NOT_FOUND, description = SwaggerMessages.USER_DOES_NOT_EXIST,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.INTERNAL_SERVER_ERROR, description = SwaggerMessages.INTERNAL_SERVER_ERROR,
                    content = @Content)})
    @GetMapping(path = "/{username}/photo")
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable String username) {
        byte[] photo = userService.getByUsername(username).getPhoto();

        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    /**
     * Updates user information.
     *
     * @param username is the user's username.
     * @param userDTO  is the user's updated information.
     * @return <code>ResponseEntity</code>
     */
    @Operation(summary = "Updates user information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SwaggerHttpStatus.OK, description = SwaggerMessages.USER_INFO_UPDATED,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SuccessDTO.class))}),
            @ApiResponse(responseCode = SwaggerHttpStatus.NOT_FOUND, description = SwaggerMessages.USER_DOES_NOT_EXIST,
                    content = @Content),
            @ApiResponse(responseCode = SwaggerHttpStatus.INTERNAL_SERVER_ERROR, description = SwaggerMessages.INTERNAL_SERVER_ERROR,
                    content = @Content)})
    @PutMapping(path = "/{username}")
    public ResponseEntity<SuccessDTO> updateUser(@PathVariable String username, @Parameter(description = "User's updated information.") @Validated @RequestBody UserDTO userDTO) {
        userService.updateUser(username, userDTO);

        SuccessDTO successDTO = new SuccessDTO();
        successDTO.setStatus(200);
        successDTO.setMessage("user_information_updated_successfully");

        return new ResponseEntity<>(successDTO, HttpStatus.OK);
    }
}
