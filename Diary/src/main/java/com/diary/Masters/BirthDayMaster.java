package com.diary.Masters;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class BirthDayMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer birthDayId;

    private String birthDayPersonName;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private Date date;

}
