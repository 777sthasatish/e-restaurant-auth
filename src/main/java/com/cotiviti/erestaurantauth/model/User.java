package com.cotiviti.erestaurantauth.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String username;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String password;

    @Column(nullable = false)
    private boolean enabled;


    public User() {
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User(User user) {
        this.username = user.username;
        this.password = user.password;
        this.enabled = user.enabled;
    }

    @PrePersist
    public void setEnabled() {
        this.enabled = true;
    }
}
