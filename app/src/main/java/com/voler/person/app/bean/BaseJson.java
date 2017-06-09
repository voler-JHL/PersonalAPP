package com.voler.person.app.bean;

/**
 * 服务器返回的数据固定为这种方式(字段名可根据服务器更改)
 * 替换范型即可重用BaseJson
 */

public class BaseJson<T> {
    private T data;
    private int code;
    private String message;


    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (code == 1) {
            return true;
        } else {
            return false;
        }
    }
}
