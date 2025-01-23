package com.diary.Payloads.Requests;

import lombok.Data;

@Data
public class VerifyOTPRequest {
    private Long id;
    private String otp;
}
