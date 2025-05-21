package com.yashmerino.online.shop.services;

  
import com.yashmerino.online.shop.exceptions.CouldntUploadPhotoException;
import com.yashmerino.online.shop.model.User;
import com.yashmerino.online.shop.model.dto.auth.UserDTO;
import com.yashmerino.online.shop.model.dto.auth.UserInfoDTO;
import com.yashmerino.online.shop.repositories.UserRepository;
import com.yashmerino.online.shop.services.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Implementation for user service.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * User repository.
     */
    private final UserRepository userRepository;

    /**
     * Constructor to inject dependencies.
     *
     * @param userRepository is the user repository.
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Returns user by id.
     *
     * @param id is the user's id.
     * @return <code>Optional of User</code>.
     */
    @Override
    public User getById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new EntityNotFoundException("User not found.");
        }
    }

    /**
     * Returns user by username.
     *
     * @param username is the user's username.
     * @return <code>User</code>
     */
    @Override
    public User getByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new EntityNotFoundException("username_not_found");
        }
    }

    /**
     * Get information about a user by username.
     *
     * @param username is the user's username.
     * @return <code>UserInfoDTO</code>
     */
    @Override
    public UserInfoDTO getUserInfo(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.orElseThrow(() -> new EntityNotFoundException("username_not_found"));
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setRoles(user.getRoles());
        userInfoDTO.setEmail(user.getEmail());

        return userInfoDTO;
    }

    /**
     * Update photo for user.
     *
     * @param photo    is the user's photo.
     * @param username is the user's username.
     */
    @Override
    public void updatePhoto(String username, MultipartFile photo) {
        User user = this.getByUsername(username);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();

        if (!user.getUsername().equals(currentUserUsername)) {
            throw new AccessDeniedException("access_denied");
        }

        try {
            user.setPhoto(photo.getBytes());
        } catch (IOException e) {
            throw new CouldntUploadPhotoException("user_photo_not_uploaded");
        }

        userRepository.save(user);
    }

    /**
     * Updates user information.
     *
     * @param username is the user's username.
     * @param userDTO  is the user's updated information.
     */
    @Override
    public void updateUser(String username, UserDTO userDTO) {
        User user = this.getByUsername(username);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUsername = auth.getName();

        if (!user.getUsername().equals(currentUserUsername)) {
            throw new AccessDeniedException("access_denied");
        }

        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
    }
}
