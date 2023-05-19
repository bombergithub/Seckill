package com.xxxx.seckill.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    //正则表达式，规定手机号的写法规范
    private static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");


    public static boolean isMobile(String mobile){
//        if(!StringUtils.xt(mobile)){
//            return false;
//        }
        //判断是否为空
        if (StringUtils.isEmpty(mobile)){
            return false;
        }
        //判断是否按照格式
        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();
    }
}
