package com.diary.Payloads.Requests;

import lombok.Data;

@Data
public class SignInRequest {
    private String mobile;
    private String password;
}
