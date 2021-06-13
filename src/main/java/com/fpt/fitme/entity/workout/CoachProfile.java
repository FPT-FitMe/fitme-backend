package com.fpt.fitme.models.workout;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "coach_profile")
@Data
public class CoachProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coach_id")
    private Long coachID;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "contact")
    private String contact;
}
