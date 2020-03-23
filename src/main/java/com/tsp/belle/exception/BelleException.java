package com.tsp.belle.exception;

import com.tsp.belle.constants.ResultCode;
import lombok.Data;

/**
 * @author likeWind
 * @date created in 15:46 2020/3/19
 * @description
 */
@Data
public class BelleException extends RuntimeException{

    private Long code;

    private String message;

    private BelleException(){

    }

    public static BelleException error(ResultCode resultCode){
        BelleException belleException = new BelleException();
        belleException.code = resultCode.getCode();
        belleException.message = resultCode.getMsg();
        return  belleException;
    }

    public ResultCode resultCode(String message){
        return forCode(message);
    }


    private ResultCode forCode(String message) {
        for (ResultCode value : ResultCode.values()) {
            if (value.getMsg().equals(message)) {
                return value;
            }
        }
        return ResultCode.server_failed;
    }

}
