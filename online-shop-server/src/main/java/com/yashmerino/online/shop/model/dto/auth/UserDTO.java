package com.yashmerino.online.shop.model.dto.auth;
  
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * User DTO.
 */
@Data
public class UserDTO {

    /**
     * User's email.
     */
    @Email(message = "email_is_invalid", regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-z A-Z]{2,7}$")
    @NotBlank(message = "email_is_required")
    // NOSONAR: The wrapper is required. Different error messages.
    @Size(min = 4, message = "email_too_short")
    @Size(max = 255, message = "email_too_long")

    private String email;
}
