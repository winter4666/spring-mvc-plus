package com.github.winter4666.springmvcplus.restapi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 返回值假如不是{@link APIResult}，自动将返回值包装成{@link APIResult}，同时以json字符串的形式输出到客户端
 * @author wutian
 * @see APIResultBodyHandler
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface APIResultBody {

}
