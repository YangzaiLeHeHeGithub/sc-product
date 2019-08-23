package com.yz.git.sc.product.common.message;

import java.io.Serializable;

/**
 * @author xuyang
 * @date 2019/08/19
 */
public class Message<D> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Result result;
    private int code;
    private String message;
    private D data;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message() {
    }

    public Message(Result result, int code) {
        this.result = result;
        this.code = code;
    }

    public Message(Result result, Integer code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public Message(Result result, int code, String message, D data) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Message(Result result, D data) {
        this.result = result;
        this.data = data;
    }

    public Message(Result result, int code, D data) {
        this.result = result;
        this.code = code;
        this.data = data;
    }

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public D getData() {
        return this.data;
    }

    public void setData(D data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "Message{result=" + this.result + ", code=" + this.code + ", message='" + this.message + '\'' + ", data=" + this.data + '}';
    }
}
