package com.self.cloudserver.entity;

import lombok.Data;

@Data
public class Role {

    private Long id;

    private String roleName;

    private String roleCode;

    private String roleDesc;

    private Integer deleteFlag;

    private Integer createUser;

    private Integer updateUser;

    private String createTime;

    private String updateTime;

}
