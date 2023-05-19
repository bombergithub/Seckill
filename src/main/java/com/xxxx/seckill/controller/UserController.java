package com.xxxx.seckill.controller;


import com.xxxx.seckill.pojo.User;
import com.xxxx.seckill.rabbitmq.MQSender;
import com.xxxx.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Bomber
 * @since 2023-04-03
 */
@Controller
@RequestMapping("/user")
public class UserController {
//
//
//    @Autowired
//    private MQSender mqSender;
//
//    //用户信息（用于测试）
//    @RequestMapping("/info")
//    @ResponseBody
//    public RespBean info(User user){
//        return RespBean.success(user);
//    }
//
//
//    /**
//     * 测试发送的RabbitMQ消息
//     */
//    @RequestMapping("/mq")
//    @ResponseBody
//    public void mq(){
//        mqSender.send("hello，eceiver");
//    }
//
//    /**
//     * fanout模式
//     * 测试利用交换机发送的RabbitMQ消息
//     */
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public void mq_fanout(){
//        mqSender.send("hello，MQReceiver");
//    }
//
//    /**
//     * direct模式
//     * 测试利用交换机发送的RabbitMQ消息
//     */
//    @RequestMapping("/mq/direct01")
//    @ResponseBody
//    public void mq_direct01(){
//        mqSender.send01("hello，Red");
//    }
//    @RequestMapping("/mq/direct02")
//    @ResponseBody
//    public void mq_direct02(){
//        mqSender.send02("hello，green");
//    }
//
//    /**
//     * topic模式
//     * 测试发送的RabbitMQ消息
//     */
//    @RequestMapping("/mq/topic01")
//    @ResponseBody
//    public void mq_topic01(){
//        mqSender.send03("hello，Red");
//    }
//    @RequestMapping("/mq/topic02")
//    @ResponseBody
//    public void mq_topic02(){
//        mqSender.send04("hello，green");
//    }

//    /**
//     * header模式
//     * 测试发送的RabbitMQ消息
//     */
//    @RequestMapping("/mq/header01")
//    @ResponseBody
//    public void mq_header01(){
//        mqSender.send05("hello，Header01");
//    }
//    @RequestMapping("/mq/header02")
//    @ResponseBody
//    public void mq_header02(){
//        mqSender.send06("hello，Header02");
//    }
}
