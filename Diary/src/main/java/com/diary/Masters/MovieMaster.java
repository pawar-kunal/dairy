package com.diary.Masters;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class MovieMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    private String movieName;

    private String modeOfWatching;

    private String moral;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date watchedDate;

    private Date date;

}
