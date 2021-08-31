package com.self.cloudserver.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.self.cloudserver.exception.CustomizedException;
import com.self.cloudserver.util.CloudFileUtil;
import net.anumbrella.seaweedfs.core.file.FileHandleStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class TestBiz {

    private static final Logger logger = LoggerFactory.getLogger(TestBiz.class);

    @Autowired
    private CloudFileUtil cloudFileUtil;

    public JSONObject testUploadFile(String uploadFileName, String fileFullPath) {
        JSONObject result = new JSONObject();
        if(FileUtil.exist(fileFullPath)){
            try{
                InputStream inputStream = FileUtil.getInputStream(fileFullPath);
                FileHandleStatus fileHandleStatus = cloudFileUtil.uploadCloudFile(uploadFileName, inputStream);
                if(fileHandleStatus != null){
                    result = JSON.parseObject(JSON.toJSONString(fileHandleStatus));
                }
            }catch (Exception e){
                logger.error("上传文件失败：", e);
            }
        }

        return result;
    }

    public JSONObject testDownloadFileContent(String cloudFileId){
        JSONObject result = new JSONObject();

        if(StringUtils.isBlank(cloudFileId)){
            throw new CustomizedException("云文件id为空");
        }

        InputStream inputStream = cloudFileUtil.getInputStreamByCloudFileId(cloudFileId);
        List<String> contentList = IoUtil.readUtf8Lines(inputStream, CollUtil.newArrayList());
        String content = CollUtil.join(contentList, "");
        result.put("content", content);

        return result;
    }

}
