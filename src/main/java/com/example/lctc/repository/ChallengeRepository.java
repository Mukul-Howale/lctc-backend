package com.example.lctc.repository;

import com.example.lctc.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

}
