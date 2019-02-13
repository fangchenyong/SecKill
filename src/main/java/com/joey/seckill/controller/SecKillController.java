package com.joey.seckill.controller;

import com.joey.seckill.pojo.OrderInfo;
import com.joey.seckill.pojo.SecKillOrder;
import com.joey.seckill.pojo.User;
import com.joey.seckill.redis.RedisService;
import com.joey.seckill.result.CodeMsg;
import com.joey.seckill.service.GoodsService;
import com.joey.seckill.service.OrderService;
import com.joey.seckill.service.SecKillService;
import com.joey.seckill.service.UserService;
import com.joey.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/secKill")
public class SecKillController {

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	SecKillService secKillService;
	
    @RequestMapping("/do_secKill")
    public String list(Model model, User user,
					   @RequestParam("goodsId")long goodsId) {
    	model.addAttribute("user", user);
		System.out.println("用户："+user.getId());
		System.out.println("用户："+user.getNickname());
		System.out.println("用户："+user.getPassword());
		System.out.println("goodsId："+goodsId);
    	if(user.getId() == null) {
    		return "login";
    	}
    	//判断库存
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	int stock = goods.getStockCount();
		System.out.println("库存："+stock);
    	if(stock <= 0) {
    		model.addAttribute("errmsg", CodeMsg.SECKILL_OVER.getMsg());
    		return "seckillFail";
    	}
    	//判断是否已经秒杀到了
    	SecKillOrder order = orderService.getSecKillOrderByUserIdGoodsId(user.getId(), goodsId);
		System.out.println("订单："+order);
    	if(order != null) {
    		model.addAttribute("errmsg", CodeMsg.SECKILL_REPEATE.getMsg());
    		return "seckillFail";
    	}
    	//减库存 下订单 写入秒杀订单
    	OrderInfo orderInfo = secKillService.seckill(user, goods);
    	model.addAttribute("orderInfo", orderInfo);
    	model.addAttribute("goods", goods);
        return "orderDetail";
    }
}
