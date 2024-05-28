package com.example.lctc.repository;

import com.example.lctc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email-ID
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM lctc.`user` u WHERE u.email = :userEmail")
    Optional<User> findUserByEmail(@Param("userEmail") String userEmail);

}
