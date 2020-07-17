package com.yz.git.sc.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource deleted failed exception.
 *
 * @author Keveon on 2017/12/31.
 * @version 1.0
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceDeletedFailedException extends RuntimeException {
    private static final String MESSAGE = "资源 [%s] 删除失败!";

    /**
     * Constructs a new runtime exceptions with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     *
     * @param resourceType the resource type
     * @param <T>          the resource type
     */
//    public <T> ResourceDeletedFailedException(Class<T> resourceType) {
//        super(String.format(MESSAGE, ResourceUtils.getResourceName(resourceType)));
//    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ResourceDeletedFailedException(String message) {
        super(message);
    }
}
