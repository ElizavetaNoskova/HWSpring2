package ru.skypro.lessons.springboot.hwspring2.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRole> userRoles;
}
