package com.tsp.belle.file.impl;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import com.tsp.belle.constants.ResultCode;
import com.tsp.belle.exception.BelleException;
import com.tsp.belle.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author likeWind
 * @date created in 10:14 2020/3/21
 * @description
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public String upload(InputStream inputStream, String extName) {
        byte[] bytes;
        ArrayList<Object> objects = new ArrayList<>();
        try {
            bytes = FileCopyUtils.copyToByteArray(inputStream);
            StorePath storePath = storageClient.uploadFile("group1",bytes, extName);
            return storePath.getPath();
        }catch (IOException e){
            e.printStackTrace();
            throw BelleException.error(ResultCode.file_upload_error);
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void download(String fullPath) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
        ServletOutputStream out = null;
        try {
            assert response != null;
            out = response.getOutputStream();
            byte[] bytes = storageClient.downloadFile("group1", fullPath);
            response.setHeader("Content-Disposition","attachment; filename=M00/00/00/rB8Ht151buuAG0NJAAD6XskwaqA7267.md");
            out.write(bytes);
        }catch (IOException e){
            e.printStackTrace();
            throw BelleException.error(ResultCode.file_download_error);
        }finally {
            try {
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
