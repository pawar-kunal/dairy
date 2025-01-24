package com.diary.Payloads.Requests;

import lombok.Data;

import java.util.Date;

@Data
public class MovieRequest {
    private Integer movieId;

    private String movieName;

    private String modeOfWatching;

    private String moral;

    private String description;

    private Date watchedDate;
}
