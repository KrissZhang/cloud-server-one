package com.self.cloudserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.self.cloudserver.entity.Role;
import com.self.cloudserver.iservice.RoleIService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class CloudserverApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

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

    @Test
    void test1(){
        Map<String, String> map = new HashMap<>();
        map.put("req", "123456");

        String url = "http://127.0.0.1:8081/rpc/cloud/server/two/test?req={req}";
        ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(url, JSONObject.class, map);

        JSONObject result = responseEntity.getBody();
        String data = Optional.ofNullable(result.getString("data")).orElse("");

        System.out.println(data);
    }

}
