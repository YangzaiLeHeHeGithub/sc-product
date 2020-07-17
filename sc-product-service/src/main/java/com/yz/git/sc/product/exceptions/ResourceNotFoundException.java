package com.yz.git.sc.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 资源没找到异常.
 *
 * @author Keveon on 2017/12/22.
 * @version 1.0
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final String MESSAGE = "资源 [%s] 没找到!";

    /**
     * Instantiates a new Resource not found exceptions.
     *
     * @param resourceType the resource type
     * @param <T>          the resource type
     */
//    public <T> ResourceNotFoundException(Class<T> resourceType) {
//        super(String.format(MESSAGE, ResourceUtils.getResourceName(resourceType)));
//    }

    /**
     * Instantiates a new Resource not found exceptions.
     *
     * @param s   the detail message.
     */
    public ResourceNotFoundException(String s) {
        super(s);
    }
}
