package com.tsp.belle.constants;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

/**
 * @author likeWind
 * @date created in 19:02 2020/3/18
 * @description 错误码
 */
public enum  ResultCode implements IErrorCode {
    /**
     *  错误返回码 枚举集
     */
    success(200,"请求成功"),
    server_failed(500,"服务器错误,请稍后重试"),
    server_timeout(501,"服务器连接超时"),
    user_not_login(301,"用户未登录"),
    file_upload_error(777,"文件解析错误" )
    , file_download_error(778,"文件下载出现异常" );

    private long code;
    private String message;

    ResultCode(long code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.message;
    }
}
