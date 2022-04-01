package com.zdsy.drivingschoolreservationsystem.controller;

import java.security.Principal;
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
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    @PostMapping("/apply_to_be_platformer")
    public Result applyToBePlatformer(Principal principal) {
        Integer userId = Integer.parseInt(principal.getName());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return Result.failed(ResultCode.noSuchUser);
        }
        User user = optionalUser.get();
        user.setRoleType(RoleType.PlatformerNotConfirm.getCode());
        userRepository.save(user);
        return Result.success();
    }

    @PostMapping(path = "/register_school")
    public Result registerSchool(Principal principal, @RequestParam String name, @RequestParam String address,
            @RequestParam String phone) {
        Integer userId = Integer.parseInt(principal.getName());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return Result.failed(ResultCode.noSuchUser);
        }

        School school = schoolRepository.findByOwner(userId);
        if (school != null) {
            return Result.failed();
        }

        school = schoolRepository.findByName(name);
        if (school != null) {
            return Result.failed(ResultCode.nameHasBeenUsed);
        }

        User user = optionalUser.get();
        user.setRoleType(RoleType.SchoolOwnerNotConfirmed.getCode());
        userRepository.save(user);

        school = School.waitingForConfirmation(name, address, phone, userId);
        schoolRepository.save(school);

        return Result.success();
    }

    @PostMapping("/register_student")
    public Result registerStudent(Principal principal, @RequestParam Integer schoolId) {
        Integer userId = Integer.parseInt(principal.getName());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return Result.failed(ResultCode.noSuchUser);
        }

        Optional<School> optionalSchool = schoolRepository.findById(schoolId);
        if (optionalSchool.isEmpty()) {
            return Result.failed(ResultCode.noSuchSchool);
        }

        School school = optionalSchool.get();
        if (!school.isConfirmed()) {
            return Result.failed("学校未确认");
        }

        User user = optionalUser.get();
        user.setRoleType(RoleType.StudentNotConfirmed.getCode());
        userRepository.save(user);

        return Result.success();
    }
}
