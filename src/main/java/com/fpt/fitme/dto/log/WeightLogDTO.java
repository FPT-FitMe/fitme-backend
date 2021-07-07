package com.fpt.fitme.dto.log;

import com.fpt.fitme.dto.appUser.AppUserDTO;
import lombok.Data;

import java.util.Date;

@Data
public class WeightLogDTO {
    private Long weightLogID;
    private AppUserDTO trainee;
    private Date createdAt;
    private Float value;
}
