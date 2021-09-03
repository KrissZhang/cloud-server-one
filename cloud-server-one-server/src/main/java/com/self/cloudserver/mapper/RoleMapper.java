package com.self.cloudserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.self.cloudserver.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    Role queryRole(Role role);

}
