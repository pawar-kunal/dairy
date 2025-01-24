package com.diary.Payloads.Requests;

import lombok.Data;

import java.util.Date;

@Data
public class BirthDayRequest {
    private Integer birthDayId;

    private String birthDayPersonName;

    private Date birthDate;
}
