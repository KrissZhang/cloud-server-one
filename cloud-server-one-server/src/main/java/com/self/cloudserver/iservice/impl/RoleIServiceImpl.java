package com.self.cloudserver.iservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.self.cloudserver.entity.Role;
import com.self.cloudserver.iservice.RoleIService;
import com.self.cloudserver.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleIServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleIService {

}
