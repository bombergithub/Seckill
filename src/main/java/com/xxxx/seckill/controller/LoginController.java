package com.xxxx.seckill.controller;


import com.xxxx.seckill.service.IUserService;

import com.xxxx.seckill.vo.Loginvo;
import com.xxxx.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
//@respondBody表示，返回对象，而不是做页面跳转
//@RestController默认下面所有方法都会有responseBody效果，所以应该用@Controller

    @Autowired
    private IUserService userService;


    //跳转登录页面
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    //登录功能
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid Loginvo loginvo, HttpServletRequest request, HttpServletResponse response){
        //仅接收前端传过来的加密密码
        log.info("{}",loginvo);  //因为@Slf4j，所以默认有log,作为校验
//        System.out.println("LoginController-----------------");
//
        return userService.doLogin(loginvo,request,response);
//        return userService.doLogin(loginvo);
//        return null;

    }
}
