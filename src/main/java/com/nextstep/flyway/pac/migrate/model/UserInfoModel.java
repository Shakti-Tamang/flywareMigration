package com.nextstep.flyway.pac.migrate.model;

import lombok.Data;
import lombok.NonNull;
import org.checkerframework.checker.interning.qual.InternedDistinct;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class UserInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Email(message = "must be valid email")
    @NotNull(message = "must not be null")
    @NotBlank(message="must not be blank")
    private String email;


    @NotBlank(message = "password must not balnk")
    @NotNull(message = " password must not be null")
    private String password;

    @NotBlank(message = "name must not balnk")
    @NotNull(message = " name must not be null")
    private String username;

    @NotBlank(message = "role must not balnk")
    @NotNull(message = " role must not be null")
    private String role;


}
