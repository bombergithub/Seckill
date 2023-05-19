# seckill_system

#### 介绍
根据 B站优极限机构（java秒杀系统） 所发布教程所编写。



#### 项目实现过程

1.  项目框架搭建
    1. SpringBoot环境搭建
    2. 继承Thymeleaf、RespBean
    3. MyBatis

2.  分布式对话
    1. 用户登录
        - 设计数据库
        - 明文密码二次MD5加密（
          设置了个MD5的盐二次加密算法（前端到后端时候一次，后端到服务器时候一次，防止数据库被截获后反编译加密方法））
        - 参数校验+全局异常处理
    2. 共享Session
        - SpringSession
        - Redis

3.  功能开发
    1. 商品列表
    2. 商品详情
    3. 秒杀
    4. 订单详情

4. 系统压测
    1. JMeter
    2. 自定义变量模拟多用户
    3. JMeter命令行的使用
    4. 正式压测
        - 商品列表
        - 秒杀

5. 页面优化
    1. 页面缓存  (URL缓存 + 对象缓存)
    2. 页面静态化，前后端分离
    3. 静态资源优化
    4. 解决库存超卖的问题

6. 接口优化
    1. Redis预减库存减少数据库的访问
    2. 内存标记减少Redis的访问
    3. RabbitMQ异步下单
        - SpringBoot整合RabbitMQ

7. 安全优化
    1. 秒杀接口地址隐藏
    2. 算术验证码
    3. 接口防刷
    

#### 环境说明

1.  windows10
2.  jdk:8
3.  IDEA：2021.1 x64
4.  虚拟机：VMware，CentOs7