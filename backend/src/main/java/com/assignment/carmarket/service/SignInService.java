package com.assignment.carmarket.service;

import com.assignment.carmarket.dto.SignInDTO;
import com.assignment.carmarket.entity.AccountEntity;
import com.assignment.carmarket.entity.AuthTokenEntity;
import com.assignment.carmarket.repository.AccountRepository;
import com.assignment.carmarket.repository.AuthTokenRepository;
import com.assignment.carmarket.utils.PasswordUtils;
import com.assignment.carmarket.utils.UUIDUtils;
import com.assignment.carmarket.utils.exceptions.BadRequestHandler;
import com.assignment.carmarket.utils.exceptions.InternalServerErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SignInService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthTokenRepository authTokenRepository;

    public AuthTokenEntity signIn(SignInDTO dto) throws InternalServerErrorHandler, BadRequestHandler {
        AccountEntity account = this.accountRepository.findByUserName(dto.getUsername().toLowerCase()).orElse(null);
        AuthTokenEntity authToken;
        if (account == null) {
            throw new BadRequestHandler("ไม่พบ user นี้ในระบบ");
        } else {
            boolean passwordMatches = PasswordUtils.checkMatchPassword(dto.getPassword(), account.getPassword());
            if (!passwordMatches) {
                throw new BadRequestHandler("username หรือ password ไม่ถูกต้อง กรุณากรอกใหม่อีกครั้ง");
            } else {
                authToken = this.authTokenRepository.findByAccountId(account.getId()).orElse(null);
                String accessToken = UUIDUtils.generateUUID();
                String refreshToken = UUIDUtils.generateUUID();
                Date expiredDate = UUIDUtils.generateExpireTime();
                if (authToken != null) {
                    authToken.setAccessToken(accessToken);
                    authToken.setRefreshToken(refreshToken);
                    authToken.setExpiredDate(expiredDate);
                    authToken.setAccount(account);
                    authToken.setUpdatedBy(account.getUserName());
                } else {
                    authToken = new AuthTokenEntity();
                    authToken.setAccessToken(accessToken);
                    authToken.setRefreshToken(refreshToken);
                    authToken.setExpiredDate(expiredDate);
                    authToken.setAccount(account);
                    authToken.setCreatedBy(account.getUserName());
                    authToken.setUpdatedBy(account.getUserName());
                }
                authToken.getAccount().setLoginLastedDate(new Date());
                authTokenRepository.save(authToken);
            }
        }
        return authToken;
    }
}
