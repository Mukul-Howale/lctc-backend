package com.example.lctc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User extends AuditModel{

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "name")
    private String userName;

    @Column(name = "email")
    private String userEmail;

    @Column(name = "pass")
    private String userPass;

    @Column(name = "is_logged_in")
    private boolean isLoggedIn;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "user_challenge",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "challenge_id")
            }
    )
    private Set<Challenge> challenges = new HashSet<>();
}
