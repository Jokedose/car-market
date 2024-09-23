package com.assignment.carmarket.repository;


import com.assignment.carmarket.entity.AuthTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthTokenEntity, Integer> {
    Optional<AuthTokenEntity> findByAccessToken(String accessToken);
    Optional<AuthTokenEntity> findByAccountId(Integer accountId);
}
