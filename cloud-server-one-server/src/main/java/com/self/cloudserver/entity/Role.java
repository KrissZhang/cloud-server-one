package com.self.cloudserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Role {

    @TableId(type = IdType.AUTO)
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
