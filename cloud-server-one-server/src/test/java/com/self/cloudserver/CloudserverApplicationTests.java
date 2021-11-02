package com.self.cloudserver;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.self.cloudserver.entity.Role;
import com.self.cloudserver.iservice.RoleIService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class CloudserverApplicationTests {

    @Autowired
    private RoleIService roleIService;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    void contextLoads() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id", 1);
        List<Role> roleList = roleIService.list(queryWrapper);
        for (Role role : roleList) {
            System.out.println(JSON.toJSONString(role));
        }
    }

}
