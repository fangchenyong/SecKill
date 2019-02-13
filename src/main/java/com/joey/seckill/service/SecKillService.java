package com.joey.seckill.service;

import com.joey.seckill.pojo.OrderInfo;
import com.joey.seckill.pojo.User;
import com.joey.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SecKillService {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;

	@Transactional
	public OrderInfo seckill(User user, GoodsVo goods) {
		//减库存 下订单 写入秒杀订单
		goodsService.reduceStock(goods);

		return orderService.createOrder(user, goods);
	}
	
}
