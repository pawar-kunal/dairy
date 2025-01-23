package com.diary.Payloads.Requests;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequest {
    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private Date dateOfBirth;

    private String password;
}
