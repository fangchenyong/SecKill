package com.joey.seckill.controller;

import com.joey.seckill.pojo.User;
import com.joey.seckill.redis.RedisService;
import com.joey.seckill.service.GoodsService;
import com.joey.seckill.service.UserService;
import com.joey.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;

	@Autowired
	GoodsService goodsService;

    @RequestMapping("/to_list")
    public String list(Model model,User user) {
    	model.addAttribute("user", user);
		List<GoodsVo> goodsList = goodsService.listGoodsVo();
		System.out.println(goodsList);
		model.addAttribute("goodsList",goodsList);
        return "goodsList";
    }


	@RequestMapping("/to_detail/{goodsId}")
	public String detail(Model model,User user,
						 @PathVariable("goodsId")long goodsId) {
		model.addAttribute("user", user);
		System.out.println("当前登录："+user.getId());
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		model.addAttribute("goods", goods);
		//秒杀开始时间
		long startAt = goods.getStartDate().getTime();
		//秒杀结束时间
		long endAt = goods.getEndDate().getTime();
		//当前时间
		long now = System.currentTimeMillis();


		System.out.println(goods.getStartDate());
		System.out.println(goods.getEndDate());
		System.out.println(now);


		int seckillStatus = 0;
		int remainSeconds = 0;
		//秒杀还没开始，倒计时
		if(now < startAt ) {
			seckillStatus = 0;
			remainSeconds = (int)((startAt - now )/1000);
		}
		//秒杀已经结束
		else  if(now > endAt){
			seckillStatus = 2;
			remainSeconds = -1;
		}
		//秒杀进行中
		else {
			seckillStatus = 1;
			remainSeconds = 0;
		}
		model.addAttribute("seckillStatus", seckillStatus);
		model.addAttribute("remainSeconds", remainSeconds);
		return "goodsDetail";
	}
    
}
