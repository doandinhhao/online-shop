package com.yashmerino.online.shop.model.dto.auth;

  
import com.yashmerino.online.shop.utils.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Register DTO.
 */
@Data
public class RegisterDTO {

    /**
     * User's role.
     */
    @NotNull(message = "Roles are required.")
    private Role role;

    /**
     * User's email.
     */
    @Email(
            message = "email_is_invalid",
            regexp  = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    )
    @NotBlank(message = "email_is_required")
    @Size(min = 4,   message = "email_too_short")
    @Size(max = 255, message = "email_too_long")
    private String email;

    /**
     * User's username.
     */
    @NotBlank(message = "username_is_required")
    @Size(min = 4,  message = "username_too_short")
    @Size(max = 40, message = "username_too_long")
    private String username;

    /**
     * User's password.
     */
    @NotBlank(message = "password_is_required")
    @Size(min = 4,  message = "password_too_short")
    @Size(max = 40, message = "password_too_long")
    private String password;

    // getters/setters hoặc Lombok @Data ở trên class
}
