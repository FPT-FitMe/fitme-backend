package com.fpt.fitme.entity.log;

import com.fpt.fitme.entity.appuser.AppUser;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "weight_log")
@Data
public class WeightLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weight_log_id")
    private Long weightLogID;

    @ManyToOne
    private AppUser trainee;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "value")
    private float value;
}
