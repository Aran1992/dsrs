package com.zdsy.drivingschoolreservationsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String avatarUrl;
    private String openid;// 用户唯一标识
    private Integer roleType;

    public User(String openid) {
        this.openid = openid;
    }
}