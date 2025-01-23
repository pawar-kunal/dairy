package com.diary.Payloads.Requests;

import lombok.Data;

@Data
public class NewPasswordRequest {
    private Long id;
    private String newPassword;
}
