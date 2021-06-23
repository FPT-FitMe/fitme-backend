package com.fpt.fitme.entity.appuser;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "app_user_role")
@Data
public class AppUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleID;

    @Column(name = "role_name")
    private String roleName;

}
