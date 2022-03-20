package com.zdsy.drivingschoolreservationsystem.controller;

import java.util.Optional;

import com.zdsy.drivingschoolreservationsystem.constant.ResultCode;
import com.zdsy.drivingschoolreservationsystem.constant.RoleType;
import com.zdsy.drivingschoolreservationsystem.model.School;
import com.zdsy.drivingschoolreservationsystem.model.User;
import com.zdsy.drivingschoolreservationsystem.repository.SchoolRepository;
import com.zdsy.drivingschoolreservationsystem.repository.UserRepository;
import com.zdsy.drivingschoolreservationsystem.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("school")
public class SchoolController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    @PostMapping(path = "/certify")
    public Result certify(@RequestParam Long userId, @RequestParam String name, @RequestParam String address,
            @RequestParam String phone) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        user.setRoleType(RoleType.SchoolOwnerNotCertified.getCode());
        userRepository.save(user);

        School school = schoolRepository.findByOwner(userId);
        if (school == null) {
            school = School.applyForCertification(name, address, phone, userId);
        }
        schoolRepository.save(school);

        return new Result(ResultCode.success);
    }
}
