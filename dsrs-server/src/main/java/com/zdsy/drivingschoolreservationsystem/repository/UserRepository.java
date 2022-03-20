package com.zdsy.drivingschoolreservationsystem.repository;

import com.zdsy.drivingschoolreservationsystem.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByOpenid(String openid);
}