package com.yz.git.sc.product.filter;

import com.yz.git.sc.product.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器统一错误处理
 *
 * @author Keveon on 2017/12/21.
 * @version 4.0
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalControllerAdvice {

    public static final String ERROR = "error";

    /**
     * 请求参数有问题.
     * <p>
     * 返回状态码为: {@link HttpStatus#BAD_REQUEST} 400.
     *
     * @param request   request
     * @param exception the exception
     * @return the vnd errors
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            IllegalArgumentException.class,
            MissingServletRequestParameterException.class})
    public VndErrors badRequestException(HttpServletRequest request, Exception exception) {
        log("badRequestException", request, exception);
        return new VndErrors(ERROR, getExceptionMessage(exception));
    }

    /**
     * 用户授权通过, 但是操作不允许.
     * <p>
     * 返回状态码为: {@link HttpStatus#FORBIDDEN} 403.
     *
     * @param request   request
     * @param exception the exception
     * @return the vnd errors
     */
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(OperationNotAllowedException.class)
    public VndErrors operationNotAllowedException(HttpServletRequest request, RuntimeException exception) {
        log("operationNotAllowedException", request, exception);
        return new VndErrors(ERROR, getExceptionMessage(exception));
    }

    /**
     * 资源没有找到.
     * <p>
     * 返回状态码为: {@link HttpStatus#NOT_FOUND} 404.
     *
     * @param request   request
     * @param exception the exception
     * @return the vnd errors
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public VndErrors resourceNotFoundException(HttpServletRequest request, ResourceNotFoundException exception) {
        log("resourceNotFoundException", request, exception);
        return new VndErrors(ERROR, getExceptionMessage(exception));
    }

    /**
     * 请求方法不允许.
     * 同时错误信息会携带支持的方法.
     * <p>
     * 返回状态码为: {@link HttpStatus#METHOD_NOT_ALLOWED} 405.
     *
     * @param request   request
     * @param exception the exception
     * @return vnd errors
     */
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public VndErrors httpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        log("httpRequestMethodNotSupportedException", request, exception);
        return new VndErrors(ERROR, String.format("请求方法 [%s] 不支持, 请使用: %s",
                exception.getMethod(), exception.getSupportedHttpMethods()));
    }

    /**
     * Http 媒体类型不支持.
     * <p>
     * 返回状态码为: {@link HttpStatus#UNSUPPORTED_MEDIA_TYPE} 415.
     *
     * @param request   request
     * @param exception the ex
     * @return the vnd errors
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public VndErrors httpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException exception) {
        log("httpMediaTypeNotSupportedException", request, exception);
        return new VndErrors(ERROR, String.format("不支持的媒体类型: %s", exception.getContentType()));
    }

    /**
     * 服务出现错误, 统一异常处理.
     * <p>
     * 返回状态码为: {@link HttpStatus#INTERNAL_SERVER_ERROR} 500.
     *
     * @param request   request
     * @param exception the exception
     * @return the vnd errors
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            ResourceReadFailedException.class,
            ResourceDeletedFailedException.class,
            ResourceCreatedFailedException.class,
            ResourceDownloadFailedException.class,
            RuntimeException.class})
    public VndErrors resourceReadFailedException(HttpServletRequest request, RuntimeException exception) {
        log("resourceReadFailedException", request, exception);
        return new VndErrors(ERROR, getExceptionMessage(exception));
    }

    /**
     * 其他未被捕获的异常.
     * <p>
     * 返回状态码为: {@link HttpStatus#INTERNAL_SERVER_ERROR} 500.
     *
     * @param request   request
     * @param throwable 所有的未被捕获或定义的异常.
     * @return vnd errors
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public VndErrors exception(HttpServletRequest request, Throwable throwable) {
        String message;
        log("INTERNAL_SERVER_ERROR", request, throwable);
        message = "服务器内部出现问题, 请稍后重试或联系系统管理员!";
        return new VndErrors(ERROR, message);
    }

    /**
     * 记录错误请求信息
     *
     * @param request   请求
     * @param exception 异常
     */
    private void log(String errorType, HttpServletRequest request, Throwable exception) {
        log.error("errorType: {}, method: {}, url: {}", errorType, request.getMethod(), request.getRequestURI(), exception);
    }

    /**
     * 有些 Exception 未设置有效的 message 信息，
     * VndErrors 会调用 Assert.hasText(message, "Message must not be null or empty!"); 导致抛出了新的异常信息
     *
     * @param exception 异常信息
     * @return 异常的 message，如果 message is blank 则返回 “异常类名, message is null”
     */
    private String getExceptionMessage(Exception exception) {
        String message = exception.getMessage();
        if (StringUtils.isBlank(message)) {
            return exception.getClass().getName() + ", message is null";
        }
        return message;
    }
}
