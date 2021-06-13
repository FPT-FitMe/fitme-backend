package com.fpt.fitme.entity.target;

import com.fpt.fitme.entity.appuser.AppUser;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "target")
@Data
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser trainee;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "complete_date")
    private Date completeDate;

    @Column(name = "starting_bmi")
    private float startingBMI;

    @Column(name = "target_bmi")
    private float targetBMI;
}
