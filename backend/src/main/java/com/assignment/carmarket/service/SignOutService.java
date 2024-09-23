package com.assignment.carmarket.service;

import com.assignment.carmarket.dto.SignOutDTO;
import com.assignment.carmarket.entity.AuthTokenEntity;
import com.assignment.carmarket.repository.AuthTokenRepository;
import com.assignment.carmarket.utils.exceptions.NotFoundHandler;
import com.assignment.carmarket.utils.exceptions.UnAuthorizedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignOutService {
    @Autowired
    private AuthTokenRepository authTokenRepository;

    public void signOut(SignOutDTO dto) throws NotFoundHandler {
        AuthTokenEntity authToken = this.authTokenRepository.findByAccessToken(dto.getToken()).orElseThrow(() -> new NotFoundHandler("Not found token."));
        authTokenRepository.delete(authToken);
    }
}
