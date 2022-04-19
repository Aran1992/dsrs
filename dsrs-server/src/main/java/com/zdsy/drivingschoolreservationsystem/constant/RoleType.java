package com.zdsy.drivingschoolreservationsystem.constant;

public enum RoleType {
    Student(0, "学生"),
    SchoolOwner(1, "驾校拥有者"),
    Platformer(2, "平台管理员"),
    StudentNotConfirmed(3, "驾校学生（未确认）"),
    SchoolOwnerNotConfirmed(4, "驾校拥有者（未确认）"),
    PlatformerNotConfirm(5, "平台管理员（未确认）"),
    ;

    private int code;

    private String name;

    private RoleType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
