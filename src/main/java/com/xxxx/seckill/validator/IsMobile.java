package com.xxxx.seckill.validator;




import com.xxxx.seckill.vo.IsMobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//验证手机号
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//定义校验的规则类
@Constraint(validatedBy = {IsMobileValidator.class})
public @interface IsMobile {

    //必填
    boolean required() default true;
    //错误返回信息
    String message() default "手机号码格式错误";

    Class<?>[] groups() default {};
    //有效载荷
    Class<? extends Payload>[] payload() default {};

}
