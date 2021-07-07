package com.fpt.fitme.dto.appUser;

import lombok.Data;

import java.util.Date;

@Data
public class AppUserStatusDTO {

    private Long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date lastModifiedDate;
    private Boolean isActive;

}
