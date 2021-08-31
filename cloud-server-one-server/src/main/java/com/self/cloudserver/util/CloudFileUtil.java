package com.self.cloudserver.util;

import com.self.cloudserver.exception.CustomizedException;
import net.anumbrella.seaweedfs.core.FileTemplate;
import net.anumbrella.seaweedfs.core.file.FileHandleStatus;
import net.anumbrella.seaweedfs.core.http.StreamResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * 云文件工具类
 */
@Component
public class CloudFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(CloudFileUtil.class);

    @Autowired
    @Lazy
    private FileTemplate fileTemplate;

    /**
     * 根据云文件id获取输入流
     * @param cloudFileId 云文件id
     * @return 输入流
     */
    public InputStream getInputStreamByCloudFileId(String cloudFileId){
        if(StringUtils.isBlank(cloudFileId)){
            throw new CustomizedException("云文件id为空");
        }

        StreamResponse streamResponse = null;
        try {
            streamResponse = fileTemplate.getFileStream(cloudFileId);
        }catch (Exception e){
            logger.error("获取云文件错误：", e);
            throw new CustomizedException("获取云文件错误：" + e.getMessage());
        }

        return streamResponse.getInputStream();
    }

    /**
     * 上传云文件
     * @param cloudFileName 云文件名
     * @param inputStream 输入流
     * @throws IOException IOException
     * @return 上传结果状态
     */
    public FileHandleStatus uploadCloudFile(String cloudFileName, InputStream inputStream) throws IOException {
        if(StringUtils.isBlank(cloudFileName) || inputStream == null){
            throw new CustomizedException("上传文件名或上传文件为空");
        }

        return fileTemplate.saveFileByStream(cloudFileName, inputStream);
    }

}
