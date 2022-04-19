package com.zdsy.drivingschoolreservationsystem.controller;

import java.util.Optional;

import com.zdsy.drivingschoolreservationsystem.constant.ResultCode;
import com.zdsy.drivingschoolreservationsystem.constant.RoleType;
import com.zdsy.drivingschoolreservationsystem.model.User;
import com.zdsy.drivingschoolreservationsystem.repository.UserRepository;
import com.zdsy.drivingschoolreservationsystem.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("platform")
public class PlatformController {
    @Autowired
    private UserRepository userRepository;
    @Autowired

    @PostMapping("/confirmPlatformer")
    public Result confirmPlatformer(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return Result.failed(ResultCode.noSuchUser);
        }

        User user = optionalUser.get();

        if (user.getRoleType() != RoleType.PlatformerNotConfirm.getCode()) {
            return Result.failed();
        }

        user.setRoleType(RoleType.Platformer.getCode());
        userRepository.save(user);

        // 是不是得修改该用户的session
        // 是不是得通知该用户他已经成为平台管理者？ 怎么通知呢？

        return Result.success();
    }
}
