package com.zdsy.drivingschoolreservationsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.zdsy.drivingschoolreservationsystem.constant.SchoolStatus;

import lombok.Data;

@Entity
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private Integer owner;
    private SchoolStatus status;

    public static School waitingForConfirmation(String name, String address, String phone, Integer owner) {
        School school = new School();
        school.setName(name);
        school.setAddress(address);
        school.setPhone(phone);
        school.setOwner(owner);
        school.setStatus(SchoolStatus.notConfirmed);
        return school;
    }

    public boolean isConfirmed() {
        return this.status == SchoolStatus.confirmed;
    }
}
