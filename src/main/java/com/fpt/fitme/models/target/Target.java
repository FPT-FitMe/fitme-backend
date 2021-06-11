package com.fpt.fitme.models.target;

import com.fpt.fitme.models.user.User;
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
    private User trainee;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "complete_date")
    private Date completeDate;

    @Column(name = "starting_bmi")
    private float startingBMI;

    @Column(name = "target_bmi")
    private float targetBMI;
}
