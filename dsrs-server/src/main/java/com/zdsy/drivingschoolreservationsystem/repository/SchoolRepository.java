package com.zdsy.drivingschoolreservationsystem.repository;

import com.zdsy.drivingschoolreservationsystem.model.School;

import org.springframework.data.repository.CrudRepository;

public interface SchoolRepository extends CrudRepository<School, Integer> {
    School findByOwner(Integer owner);

    School findByName(String name);
}