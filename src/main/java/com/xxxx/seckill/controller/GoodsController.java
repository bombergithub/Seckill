package com.xxxx.seckill.controller;


import com.xxxx.seckill.pojo.User;
import com.xxxx.seckill.service.IGoodsService;
import com.xxxx.seckill.service.IUserService;
import com.xxxx.seckill.vo.DetailVo;
import com.xxxx.seckill.vo.GoodsVo;
import com.xxxx.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    /**
     * 跳转到商品列表页
     * windows优化前QPS(1000循环30次访问量，吞吐量)：550.1/sec
     * linux优化前QPS(1000循环30次访问量，吞吐量)：154.9/sec
     * 优化后缓存QPS：7032.3/sec
     * 虚拟机配置：内存4GB,处理器4
     *
     * @param model
//     * @param user
     * @return
     */
    @RequestMapping(value = "/toList",produces = "text/html;charset=utf-8")   //直接跳转页面，加@ResponseBody，则处理信息
//    public String toList(Model model, User user){
//        model.addAttribute("user",user);
//        model.addAttribute("goodsList", goodsService.findGoodsVo());
//        return "goodsList";
//    }
    @ResponseBody
    public String toList(Model model, User user,HttpServletRequest request, HttpServletResponse response) {
        //Redis中获取页面，如果不为空，直接返回页面
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsList");
        if (StringUtils.hasText(html)) {
            return html;
        }
        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        //如果为空，手动渲染并且存入redis并且返回；
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(),
                model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsList", webContext);
        //首先判断渲染的页面是否有问题
        if (StringUtils.hasText(html)) {
            valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
        }
        return html;
    }


    /**
     * 跳转商品详情页
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
//    @RequestMapping(value = "/toDetail/{goodsId}",produces = "text/html;charset=utf-8")   //直接跳转页面，加@ResponseBody，则处理信息

//    public String toDetail(Model model,User user,@PathVariable Long goodsId){
//        GoodsVo goodsVo = goodsService.findGoodsVoByGoodId(goodsId);
//        Date startDate = goodsVo.getStartDate();
//        Date endDate = goodsVo.getEndDate();
//        Date nowDate = new Date();
//        //秒杀状态
//        int secKillStatus = 0;
//        //秒杀倒计时
//        int remainSeconds = 0;
//        if(nowDate.before(startDate)){
//            //说明秒杀还未开始
//            remainSeconds = ((int)((startDate.getTime() - nowDate.getTime())/1000));
//        }else if(nowDate.after(endDate)){
//            //说明秒杀已结束
//            secKillStatus = 2;
//            remainSeconds = -1;
//        }else {
//            secKillStatus = 1;
//            remainSeconds = 0;
//        }
//        model.addAttribute("remainSeconds",remainSeconds);
//        model.addAttribute("secKillStatus",secKillStatus);
//        model.addAttribute("user",user);
//        model.addAttribute("goods",goodsService.findGoodsVoByGoodId(goodsId));
//        return "goodsDetail";
//    }
//    @ResponseBody
//    public String toDetail(Model model,User user,@PathVariable Long goodsId,
//                           HttpServletRequest request,HttpServletResponse response){
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        //Redis中获取页面，如果不为空，直接返回页面
//        String html = (String)valueOperations.get("goodsDetail" + goodsId);
//        if(StringUtils.hasText(html)){
//            return html;
//        }
//        model.addAttribute("user",user);
//        GoodsVo goodsVo = goodsService.findGoodsVoByGoodId(goodsId);
//        Date startDate = goodsVo.getStartDate();
//        Date endDate = goodsVo.getEndDate();
//        Date nowDate = new Date();
//        //秒杀状态
//        int secKillStatus = 0;
//        //秒杀倒计时
//        int remainSeconds = 0;
//
//        if(nowDate.before(startDate)){
//            //说明秒杀还未开始
//            remainSeconds = ((int)((startDate.getTime() - nowDate.getTime())/1000));
//        }else if(nowDate.after(endDate)){
//            //说明秒杀已结束
//            secKillStatus = 2;
//            remainSeconds = -1;
//        }else {
//            secKillStatus = 1;
//            remainSeconds = 0;
//        }
//        model.addAttribute("remainSeconds",remainSeconds);
//        model.addAttribute("secKillStatus",secKillStatus);
//        model.addAttribute("goods",goodsVo);
//        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(),
//                model.asMap());
//        html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail",webContext);
//        if(StringUtils.hasText(html)){
//            valueOperations.set("goodsDetail:"+goodsId,html,60, TimeUnit.SECONDS);
//        }
//        //return "goodsDetail";
//        return html;
//    }

//    /**
//     * 跳转商品详情页
//     * @param model
//     * @param user
//     * @param goodsId
//     * @return
//     */
    @RequestMapping("/toDetail/{goodsId}")   //直接跳转页面，加@ResponseBody，则处理信息
    @ResponseBody
    public RespBean toDetail(User user, @PathVariable Long goodsId){
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        //秒杀状态
        int secKillStatus = 0;
        //秒杀倒计时
        int remainSeconds = 0;

        if(nowDate.before(startDate)){
            //说明秒杀还未开始
            remainSeconds = ((int)((startDate.getTime() - nowDate.getTime())/1000));
        }else if(nowDate.after(endDate)){
            //说明秒杀已结束
            secKillStatus = 2;
            remainSeconds = -1;
        }else {
            secKillStatus = 1;
            remainSeconds = 0;
        }
        DetailVo detailVo = new DetailVo();
        detailVo.setUser(user);
        detailVo.setGoodsVo(goodsVo);
        detailVo.setSecKillStatus(secKillStatus);
        detailVo.setRemainSeconds(remainSeconds);
        return RespBean.success(detailVo);
    }


}
