package com.assignment.carmarket.service;

import com.assignment.carmarket.dto.SignUpAccountDTO;
import com.assignment.carmarket.entity.AccountEntity;
import com.assignment.carmarket.entity.UserAccountEntity;
import com.assignment.carmarket.repository.AccountRepository;
import com.assignment.carmarket.repository.UserAccountRepository;
import com.assignment.carmarket.utils.PasswordUtils;
import com.assignment.carmarket.utils.exceptions.BadRequestHandler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public AccountEntity signUpAccount(SignUpAccountDTO dto) throws BadRequestHandler {
        PasswordUtils.checkFormatPassword(dto.getPassword());
        String fullName = dto.getFirstName() + "|" + dto.getLastName();
        String salt = PasswordUtils.generateSalt();
        String encryptPassword = PasswordUtils.generatePassword(dto.getPassword(), salt);
        UserAccountEntity user = setUserEntity(dto, fullName);
        userAccountRepository.save(user);
        AccountEntity account = validateUserName(dto, encryptPassword, salt, user, fullName);
        accountRepository.save(account);
        return account;
    }

    private AccountEntity validateUserName(SignUpAccountDTO dto, String encyptPassword, String salt, UserAccountEntity user, String fullName) throws BadRequestHandler {
        AccountEntity account = this.accountRepository.findByUserName(dto.getUsername()).orElse(null);
        if (account != null) {
            throw new BadRequestHandler("Username นี้ ไม่สามารถใช้งานได้");
        } else {
            account = new AccountEntity();
            account.setUserName(dto.getUsername().toLowerCase());
            account.setPassword(encyptPassword);
            account.setSalt(salt);
            account.setIsEnabled(true);
            account.setUserAccount(user);
            account.setCreatedBy(fullName);
            account.setUpdatedBy(fullName);
        }
        return account;
    }

    private UserAccountEntity setUserEntity(SignUpAccountDTO dto, String fullName) throws BadRequestHandler {
        UserAccountEntity userAccount = this.userAccountRepository.findByEmail(dto.getEmail()).orElse(null);
        if (userAccount != null) {
            throw new BadRequestHandler("Email นี้ ไม่สามารถใช้งานได้");
        } else {
            userAccount = new UserAccountEntity();
            userAccount.setTaxId(dto.getTaxId());
            userAccount.setEmail(dto.getEmail());
            userAccount.setFirstName(dto.getFirstName());
            userAccount.setLastName(dto.getLastName());
            userAccount.setMobileNumber(dto.getMobileNumber());
            userAccount.setCreatedBy(fullName);
            userAccount.setUpdatedBy(fullName);
        }
        return userAccount;
    }
}
