package com.fpt.fitme.entity.tag;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "tag_name")
    private String name;

    @Column(name = "tag_type")
    private String type; //2 type "meal" and "exercise"

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;
}
