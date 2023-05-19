package com.xxxx.seckill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SeckillDemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisScript<Boolean> script;

    @Test
    void testLock01() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //当key存在时，不成功，只有当key不存在时才可以set成功
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1");
        //如果占位成功，进行正常操作
        if(isLock){
            valueOperations.set("name","secKill");
            String name = (String) valueOperations.get("name");
            System.out.println("name = "+name);
            Integer.parseInt("XXXXX");
            //操作结束，删除锁
            redisTemplate.delete("k1");
        }else{
            System.out.println("有线程在使用，请稍后再试");
        }
    }

    @Test
    void testLock02() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //给锁添加一个过期时间，防止应用在运行过程中导致锁无法释放；
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1",5, TimeUnit.SECONDS);
        //如果占位成功，进行正常操作
        if(isLock){
            valueOperations.set("name","secKill");
            String name = (String) valueOperations.get("name");
            System.out.println("name = "+name);
            Integer.parseInt("XXXXX");
            //操作结束，删除锁
            redisTemplate.delete("k1");
        }else{
            System.out.println("有线程在使用，请稍后再试");
        }
    }

    @Test
    void testLock03() {
        //避免锁异常的耗时操作（testLock02）
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //防治所因为时间因素而导致的数据紊乱问题
        String value = UUID.randomUUID().toString();
        Boolean isLock = valueOperations.setIfAbsent("k1", value,120, TimeUnit.SECONDS);
        //如果占位成功，进行正常操作
        if(isLock){
            valueOperations.set("name","secKill");
            String name = (String) valueOperations.get("name");
            System.out.println("name = "+name);
            //打印一下锁
            System.out.println(valueOperations.get("k1"));
            Boolean result = (Boolean)redisTemplate.execute(script, Collections.singletonList("k1"), value);
            System.out.println(result);
        }else{
            System.out.println("有线程在使用，请稍后再试");
        }
    }
    @Test
    void contextLoads() {
    }

}
