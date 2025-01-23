package com.diary.Payloads.Requests;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateProfileRequest {
    private Long id;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private Date dateOfBirth;

}
