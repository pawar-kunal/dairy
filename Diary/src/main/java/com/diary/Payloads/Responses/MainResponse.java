package com.diary.Payloads.Responses;

import lombok.Data;

@Data
public class MainResponse {
    private String message;

    private Integer responseCode;

    private Boolean flag;
}
