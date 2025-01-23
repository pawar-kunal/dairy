package com.diary.Payloads.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDetailsResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private Date dateOfBirth;

    private Date lastLogin;

}
