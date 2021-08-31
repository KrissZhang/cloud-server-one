package com.self.cloudserver.constants;

public interface ApiUri {

    String MODULE_URI_PREFIX = "/cloud/server/one";

    String TEST = MODULE_URI_PREFIX + "/test";

    String TEST_FEIGN = MODULE_URI_PREFIX + "/testFeign";

    String TEST_UPLOAD_FILE = MODULE_URI_PREFIX + "/testUploadFile";

    String TEST_DOWNLOAD_FILE_CONTENT = MODULE_URI_PREFIX + "/testDownloadFileContent";

}
