package com.yashmerino.online.shop.model;

  
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yashmerino.online.shop.model.base.NamedEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * JPA Entity for a user.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
@Table(name = "users")
public class User extends NamedEntity {

    /**
     * User's cart.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    /**
     * User's products.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<Product> products;

    /**
     * User's email.
     */
    private String email;

    /**
     * User's username.
     */
    private String username;

    /**
     * User's password.
     */
    private String password;

    /**
     * User's roles.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    /**
     * User's photo.
     */
    @Lob
    @Column(name = "photo", length = 100000)
    @Nullable
    private byte[] photo;
}
