package com.zdsy.drivingschoolreservationsystem.model;

import java.security.Principal;

import lombok.Data;

@Data
public class UserPrincipal implements Principal {
    private Integer userId;

    public String getName() {
        return this.userId.toString();
    }
}
