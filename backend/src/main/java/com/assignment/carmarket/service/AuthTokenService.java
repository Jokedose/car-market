package com.assignment.carmarket.service;

import com.assignment.carmarket.entity.AuthTokenEntity;
import com.assignment.carmarket.repository.AuthTokenRepository;
import com.assignment.carmarket.utils.exceptions.UnAuthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthTokenService {
    @Autowired
    private AuthTokenRepository authTokenRepository;

    public AuthTokenEntity getAccount(String accessToken) throws UnAuthorizedHandler {
        Optional<AuthTokenEntity> authTokenEntity = this.authTokenRepository.findByAccessToken(accessToken);
        Date currentDate = new Date();

        if (authTokenEntity.isEmpty())
            throw new UnAuthorizedHandler("ACCESS_TOKEN_UNAUTHORIZED");

        if (currentDate.after(authTokenEntity.get().getExpiredDate()))
            throw new UnAuthorizedHandler("ACCESS_TOKEN_EXPIRED");

        authTokenEntity.get().getAccount().setLoginLastedDate(new Date());
        authTokenRepository.save(authTokenEntity.get());
        return authTokenEntity.get();
    }
}
