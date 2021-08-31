package com.self.cloudserver.config;

import net.xdevelop.snowflake.SnowflakeUidGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UIDConfig {

    @Bean
    public SnowflakeUidGenerator customizedUidGenerator(){
        return new SnowflakeUidGenerator(SnowflakeUidGenerator.getWorkerIdByIP(24));
    }

}
