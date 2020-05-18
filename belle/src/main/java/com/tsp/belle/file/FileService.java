package com.tsp.belle.file;



import java.io.InputStream;

/**
 * @author likeWind
 * @date created in 10:08 2020/3/21
 * @description
 */
public interface FileService {

    /**
     * 文件上传
     * @param inputStream 文件输入流
     * @param extName 文件扩展名
     * @return 文件实际存储地址
     */
    String upload(InputStream inputStream,String extName);


    /**
     * 使用文件路径下载文件
     * @param path 文件路径
     */
    void download(String path);

}
