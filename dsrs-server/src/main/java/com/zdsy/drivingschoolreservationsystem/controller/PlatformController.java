package com.zdsy.drivingschoolreservationsystem.controller;

import java.util.Optional;

import com.zdsy.drivingschoolreservationsystem.constant.RoleType;
import com.zdsy.drivingschoolreservationsystem.model.User;
import com.zdsy.drivingschoolreservationsystem.repository.UserRepository;
import com.zdsy.drivingschoolreservationsystem.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("platform")
public class PlatformController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/certify")
    public Result certify(@RequestParam Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        User user = optional.get();
        user.setRoleType(RoleType.PlatformManagerNotCertified.getCode());
        return Result.success();
    }
}
