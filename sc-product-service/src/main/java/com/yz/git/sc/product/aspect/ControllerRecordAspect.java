package com.yz.git.sc.product.aspect;

import com.yz.git.sc.product.annotation.ControllerRecord;
import com.yz.git.sc.product.annotation.MetaData;
import com.yz.git.sc.product.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * <p>@ClassName ControlerRecordAspect<p>
 * <p>@Description 拦截器<p>
 * <p>@Author xuYang<p>
 * <p>@Date 2020/6/12 16:49<p>
 */
@Slf4j
@Aspect
@Component
public class ControllerRecordAspect {

    private static ThreadLocal<MetaData> metaDataContainer = ThreadLocal.withInitial(MetaData::new);


    /**
     * 切入点, 只切入有 ControllerRecord 注解的接口
     */
    @Pointcut("@annotation(com.yz.git.sc.product.annotation.ControllerRecord)")
    public void controllerAspect() {
        // ignore
    }
    //@Before("controllerAspect()")
    public void doBefore(JoinPoint pjp){

        MetaData metaData = ControllerRecordAspect.metaDataContainer.get();
        try {
            // 获取被拦截的方法
            fillMetaData(metaData, pjp);
        } catch (Exception e) {
            log.error("元数据填充失败 {}#{}", metaData.getClazz(), metaData.getMethodName(), e);
        }
        if (metaData.isNeedRecord()) {
            try {
                doBefore(pjp, metaData);
            } catch (Exception e) {
                metaData.setNeedRecord(false);
                log.error("数据处理失败", e);
            }
        }


    }

    /**
     * 获取监控的方法
     * @param metaData metaData
     * @param pjp      切片点
     * @return 数据
     */
    private MetaData fillMetaData(MetaData metaData, JoinPoint pjp) {
        // 拦截的方法名称。当前正在执行的方法
        Signature sig = pjp.getSignature();
        if (!(sig instanceof MethodSignature)) {
            log.warn("添加操作记录失败。原因：@ControllerRecord 该注解只能用于方法");
            return metaData;
        }
        Method method = ((MethodSignature) sig).getMethod();
        metaData.setClazz(method.getDeclaringClass().getName());
        metaData.setMethodName(sig.getName());
        metaData.setControllerRecord(method.getAnnotation(ControllerRecord.class));
        metaData.setNeedRecord(true);
        metaData.setOperationUser(method.getAnnotation(ControllerRecord.class).operationUser());
        metaData.setOperationType(method.getAnnotation(ControllerRecord.class).operationType());
        log.info("operaUser:{},operaType:{}",metaData.getOperationUser(),metaData.getOperationType());

        return metaData;
    }

    private void doBefore(JoinPoint pjp, MetaData metaData) throws NotFoundException, ReflectiveOperationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取请求中的参数 这里面包含了controller不需要的参数，需单独处理
        // paramName 请求方法中的参数名称; pjp.getArgs() 拦截的controller方法参数值
        String[] paramName = getFieldsName(pjp.getTarget().getClass(), metaData.getMethodName());


    }
    /**
     * 使用javassist来获取方法参数名称
     *
     * @param clazz      类名
     * @param methodName 方法名
     * @return 名字集合
     * @throws NotFoundException 异常
     */
    private String[] getFieldsName(Class<?> clazz, String methodName) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        // 在tomcat之类的容器中是无法通过ClassPool.getDefault()获取到用户定义的类的
        pool.insertClassPath(new ClassClassPath(clazz));

        CtClass ctClass = pool.get(clazz.getName());
        CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            String message = "添加操作记录失败。原因：拦截请求：【" + metaDataContainer.get().getMethodName() + " 】的方法中的参数名称失败";
            log.warn(message);
            throw BadRequestException.ofMessage(message);
        }
        String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
        TreeMap<Integer, String> sortMap = new TreeMap<>();
        for (int i = 0; i < attr.tableLength(); i++) {
            sortMap.put(attr.index(i), attr.variableName(i));
        }
        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        paramsArgsName = Arrays.copyOfRange(sortMap.values().toArray(new String[0]), pos, paramsArgsName.length + pos);
        if (ObjectUtils.isEmpty(paramsArgsName)) {
            String message = "添加操作记录失败。原因：拦截请求：【" + metaDataContainer.get().getMethodName() + " 】的方法中的参数名称失败";
            log.warn(message);
            throw BadRequestException.ofMessage(message);
        }
        return paramsArgsName;
    }

    /**
     * 被切入的方法结束执行后的操作
     *
     * @param object response
     */
    @AfterReturning(returning = "object", pointcut = "controllerAspect()")
    public void doAfter(Object object) {
        MetaData metaData = ControllerRecordAspect.metaDataContainer.get();
        if (!metaData.isNeedRecord()) {
            return;
        }
        log.info("方法：【{}#{}】运行时间为：{}", metaData.getClazz(), metaData.getMethodName(),
                (System.currentTimeMillis() - metaData.getBeginTime()));

    }
}
