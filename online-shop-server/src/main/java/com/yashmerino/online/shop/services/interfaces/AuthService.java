package com.yashmerino.online.shop.services.interfaces;
  
import com.yashmerino.online.shop.model.dto.auth.LoginDTO;
import com.yashmerino.online.shop.model.dto.auth.RegisterDTO;

/**
 * Interface for authentication/authorization service.
 */
public interface AuthService {

    /**
     * Registers the user.
     *
     * @param registerDTO is the register DTO.
     */
    void register(final RegisterDTO registerDTO);

    /**
     * Logins the user.
     *
     * @param loginDTO is the login DTO.
     * @return JWT Token.
     */
    String login(final LoginDTO loginDTO);
}
