package com.hnguigu.demo.common.util;

import com.hnguigu.demo.common.constant.ResponseStatusConstant;

import java.util.List;

/**
 * @program: BOS
 * @description: 响应的结果
 * @author: 徐子楼
 * @create: 2018-10-01 15:01
 **/
public class ResponseResult {
    /**
     * 状态
     */
    private int status;

    /**
     * 消息
     */
    private String message;

    /**
     * 结果集
     */
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    public static ResponseResult success(Object data) {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS, "success", data);
    }

    public static ResponseResult success() {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS, "success", null);
    }

    public static ResponseResult success(String message) {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS, message, null);
    }

    public static ResponseResult fail(Object data) {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_FAIL, "fail", data);
    }

    public static ResponseResult fail(String message) {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_FAIL, message, null);
    }

    public static ResponseResult fail() {
        return new ResponseResult(ResponseStatusConstant.RESPONSE_STATUS_NO_PERMISSION, "fali", null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
