package com.fpt.fitme.dto.target;

import com.fpt.fitme.dto.appUser.AppUserDTOLazy;
import lombok.Data;

import java.util.Date;

@Data
public class TargetDTO {
    private Long id;
    private AppUserDTOLazy trainee;
    private Date startDate;
    private Date completeDate;
    private float startingBMI;
    private float targetBMI;
    private Boolean hasFinished;
}
