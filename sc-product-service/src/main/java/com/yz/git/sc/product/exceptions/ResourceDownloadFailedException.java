package com.yz.git.sc.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource download failed exception.
 *
 * @author zhaiyanming on 2018/1/22.
 * @version 4.0
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceDownloadFailedException extends RuntimeException {
    private static final String MESSAGE = "资源 [%s] 下载失败!";

    /**
     * Instantiates a new Resource download failed exception.
     *
     * @param resourceType the resource type
     * @param <T>          the resource type
     */
//    public <T> ResourceDownloadFailedException(Class<T> resourceType) {
//        super(String.format(MESSAGE, ResourceUtils.getResourceName(resourceType)));
//    }

    /**
     * Constructs a new runtime exceptions with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     *
     * @param message the message
     */
    public ResourceDownloadFailedException(String message) {
        super(message);
    }
}
