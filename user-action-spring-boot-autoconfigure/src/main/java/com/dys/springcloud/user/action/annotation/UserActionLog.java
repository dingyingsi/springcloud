package com.dys.springcloud.user.action.annotation;

import java.lang.annotation.*;

/**
 * @author dingyingsi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserActionLog {

    /**
     * 这个注解中可以使用内置登录用户变量loginUser.getUserName(); loginUser的类型为 {@link com.liubike.framework.user.api.dto.SysUserDto}
     * 例如: "【#{#loginUser.getUserName()}】用户操作了数据
     * @return
     */

    String value() default "";

    /**
     * 接口所属模块名称
     * @return
     */
    String module() default "";

    /**
     * 是否忽略参数，不记录操作日志
     * @return
     */
    boolean ignoreParam() default false;

    /**
     * 是否忽略返回值，不记录操作日志
     * @return
     */
    boolean ignoreReturn() default false;
}