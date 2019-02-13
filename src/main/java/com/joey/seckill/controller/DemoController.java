package com.joey.seckill.controller;

import com.joey.seckill.pojo.User;
import com.joey.seckill.redis.RedisService;
import com.joey.seckill.redis.UserKey;
import com.joey.seckill.result.CodeMsg;
import com.joey.seckill.result.Result;
import com.joey.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈demo〉
 *
 * @author Joey
 * @create 2019-01-31
 * @since 1.0.0
 */
@Controller
public class DemoController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
        return Result.success("Success");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloerror(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(){
        return "hello";
    }

    @RequestMapping("/getByid")
    @ResponseBody
    public Result<User> getUserById(){
        User user = userService.getById(Long.valueOf(1));
        return Result.success(user);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User  user  = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user  = new User();
        user.setId(Long.parseLong("17706441315"));
        user.setNickname("1111");
        redisService.set(UserKey.getById, ""+1, user);
        return Result.success(true);
    }
}
