package com.pancm.util;

/**
 * @Auther: http://www.bjsxt.com
 * @Date: 2019/6/13
 * @Description: com.pancm.util
 * @version: 1.0
 */
public class JsonResult {

    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
