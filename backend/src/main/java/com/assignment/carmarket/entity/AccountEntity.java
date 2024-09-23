package com.assignment.carmarket.entity;

import com.assignment.carmarket.repository.BaseColumn;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "account")
public class AccountEntity extends BaseColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "login_lasted_date")
    private Date loginLastedDate;

    @ManyToOne
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    private UserAccountEntity userAccount;
}
