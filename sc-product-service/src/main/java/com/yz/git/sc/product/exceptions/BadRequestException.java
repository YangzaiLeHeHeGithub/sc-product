package com.yz.git.sc.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Bad request exception.
 *
 * @author Keveon on 2017/12/22.
 * @version 1.0
 */
@SuppressWarnings("serial")
//1111
//2222
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private static final String MESSAGE = "请至少输入一个参数或条件";
    private static final String MESSAGE_FORMAT = "请求参数 [%s] 不能为空";

    /**
     * Instantiates a new Bad request exception.
     */
    public BadRequestException() {
        super(MESSAGE);
    }

    /**
     * Instantiates a new Bad request exception.
     *
     * @param message the message
     */
    private BadRequestException(String message) {
        super(message);
    }

    /**
     * parameter name
     *
     * @param parameterName parameter name
     * @return bad req exception
     */
    public static com.yz.git.sc.product.exceptions.BadRequestException ofParameterName(String parameterName) {
        return new com.yz.git.sc.product.exceptions.BadRequestException(String.format(MESSAGE_FORMAT, parameterName));
    }

    /**
     * message
     *
     * @param message message
     * @return bad req exception
     */
    public static com.yz.git.sc.product.exceptions.BadRequestException ofMessage(String message) {
        return new com.yz.git.sc.product.exceptions.BadRequestException(message);
    }
}
