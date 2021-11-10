package com.self.cloudserver.constants;

public interface ApiUri {

    String MODULE_URI_PREFIX = "/cloud/server/one";

    String TEST = MODULE_URI_PREFIX + "/test";

    String TEST_FEIGN = MODULE_URI_PREFIX + "/testFeign";

    String TEST_UPLOAD_FILE = MODULE_URI_PREFIX + "/testUploadFile";

    String TEST_DOWNLOAD_FILE_CONTENT = MODULE_URI_PREFIX + "/testDownloadFileContent";

    String TEST_APX = MODULE_URI_PREFIX + "/testApx";

    String TEST_WEBSERVICE = MODULE_URI_PREFIX + "/testWebService";

    String TEST_EVENT = MODULE_URI_PREFIX + "/testEvent";

    String TEST_EXPORTTOFILE = MODULE_URI_PREFIX + "/exportToFile";

    String TEST_EXPORTTOWEB = MODULE_URI_PREFIX + "/exportToWeb";

    String TEST_EXPORTTOWEBFILE = MODULE_URI_PREFIX + "/exportToWebFile";

    String TEST_READANDSAVEEXCELDATA = MODULE_URI_PREFIX + "/readAndSaveExcelData";

    String TEST_UPLOADANDSAVEEXCELDATA = MODULE_URI_PREFIX + "/uploadAndSaveExcelData";

}
