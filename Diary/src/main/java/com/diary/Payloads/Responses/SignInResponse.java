package com.diary.Payloads.Responses;

import lombok.Data;

import java.util.Date;

@Data
public class SignInResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private Date dateOfBirth;

    private Date lastLogin;

    private String message;

    private Integer responseCode;

    private Boolean flag;
}
