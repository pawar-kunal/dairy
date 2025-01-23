package com.diary.Masters;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private Date dateOfBirth;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Date lastLogin;

    private String password;

    private String otp;

    private String status;

}
