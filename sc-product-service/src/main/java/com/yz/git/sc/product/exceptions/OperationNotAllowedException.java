package com.yz.git.sc.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * operation not allowed
 *
 * @author zhaiyanming
 * @version 4.0
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class OperationNotAllowedException extends RuntimeException {
    private static final String MESSAGE = "不允许操作";

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public OperationNotAllowedException() {
        super(MESSAGE);
    }

    /**
     * Instantiates a new operation not allowed exceptions.
     *
     * @param s the detail message.
     */
    public OperationNotAllowedException(String s) {
        super(s);
    }
}
