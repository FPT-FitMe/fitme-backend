package com.fpt.fitme.dto.log;

import com.fpt.fitme.dto.appUser.AppUserDTOLazy;
import lombok.Data;

import java.util.Date;

@Data
public class WeightLogDTO {
    private Long weightLogID;
    private AppUserDTOLazy trainee;
    private Date createdAt;
    private Float value;
}
