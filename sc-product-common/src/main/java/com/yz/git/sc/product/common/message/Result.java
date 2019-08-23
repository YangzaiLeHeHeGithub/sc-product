package com.yz.git.sc.product.common.message;

/**
 * @author xuyang
 * @date 2019/08/19
 */
public enum Result {
    //成功
    SUCCESS(1),
    //失败
    FAILED(0),
    //错误
    ERROR(-1),
    //
    UPDATEPWD(2),
    //
    TOKENEXCEPTION(1014);

    private int value;

    private Result(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static boolean isSuccess(Result result) {
        return result == SUCCESS;
    }
}

