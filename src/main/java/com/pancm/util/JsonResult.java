package com.pancm.util;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2019/6/13
 * @Description: com.pancm.util
 * @version: 1.0
 */
public class JsonResult<T> {

    private boolean success;
    private String code;
    private String message;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
