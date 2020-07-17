package com.yz.git.sc.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 资源读取失败
 *
 * @author Keveon on 2017/12/31.
 * @version 4.0
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceReadFailedException extends RuntimeException {

    private static final String MESSAGE = "资源 [%s] 读取失败!";

    /**
     * Instantiates a new Resource read failed exception.
     *
     * @param resourceType the resource type
     * @param <T>          the resource type
     */
//    public <T> ResourceReadFailedException(Class<T> resourceType) {
//        super(String.format(MESSAGE, ResourceUtils.getResourceName(resourceType)));
//    }

    /**
     * Constructs a new runtime exceptions with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     *
     * @param message the message
     */
    public ResourceReadFailedException(String message) {
        super(message);
    }
}
