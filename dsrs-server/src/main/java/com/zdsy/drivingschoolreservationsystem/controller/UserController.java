package com.zdsy.drivingschoolreservationsystem.controller;

import com.zdsy.drivingschoolreservationsystem.result.Result;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping("test")
    public Result test() {
        return Result.success();
    }
}
