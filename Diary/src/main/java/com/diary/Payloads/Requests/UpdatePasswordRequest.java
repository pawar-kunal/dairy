package com.diary.Payloads.Requests;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
