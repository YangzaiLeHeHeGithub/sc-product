package com.yz.git.sc.product.common.message;

/**
 * @author xuyang
 * @date 2019/08/20
 */
public class Messages {
    public static <D> Message<D> success(D data) {
        return new Message(Result.SUCCESS, 200, data);
    }

    public static <D> Message<D> success() {
        return success((D)null);
    }

    public static <D> Message<D> failed(int code) {
        return new Message(Result.FAILED, code);
    }

    public static <D> Message<D> failed(int code, String msg) {
        return new Message(Result.FAILED, code, msg);
    }

    public static <D> Message<D> error() {
        return new Message(Result.ERROR, (Object)null);
    }

    public static <D> Message<D> error(int code) {
        return new Message(Result.ERROR, code);
    }

    public static boolean isSuccess(Message<?> message) {
        return Result.SUCCESS.equals(message.getResult());
    }

    private Messages() {
    }
}
