package com.zdsy.drivingschoolreservationsystem.constant;

public enum RoleType {
    Student(0, "学生"),
    SchoolOwner(1, "驾校拥有者"),
    PlatformManager(2, "平台管理员"),
    SchoolOwnerNotCertified(3, "驾校拥有者（未认证）"),
    PlatformManagerNotCertified(4, "平台管理员（未认证）"),
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
