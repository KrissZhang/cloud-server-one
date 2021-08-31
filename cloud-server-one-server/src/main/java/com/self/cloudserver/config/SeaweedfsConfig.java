package com.self.cloudserver.config;

import net.anumbrella.seaweedfs.core.FileSource;
import net.anumbrella.seaweedfs.core.FileTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.yaml")
public class SeaweedfsConfig {

    @Value("${seaweedfs.host}")
    private String host;

    @Value("${seaweedfs.port}")
    private Integer port;

    @Value("${seaweedfs.use-public}")
    private boolean usePublic = false;

    @Bean
    @Lazy
    public FileTemplate fileTemplate() throws IOException {
        FileSource fileSource = new FileSource();
        fileSource.setHost(host);
        fileSource.setPort(port);
        fileSource.setConnectionTimeout(1000000);

        // 启动服务
        fileSource.startup();
        FileTemplate fileTemplate = new FileTemplate(fileSource.getConnection());
        fileTemplate.setUsingPublicUrl(usePublic);
        return fileTemplate;
    }

}
