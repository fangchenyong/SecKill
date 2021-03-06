package com.joey.seckill.service;

import java.util.Date;

import com.joey.seckill.dao.OrderDao;
import com.joey.seckill.pojo.OrderInfo;
import com.joey.seckill.pojo.SecKillOrder;
import com.joey.seckill.pojo.User;
import com.joey.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	public SecKillOrder getSecKillOrderByUserIdGoodsId(long userId, long goodsId) {
		return orderDao.getSecKillOrderByUserIdGoodsId(userId, goodsId);
	}

	@Transactional
	public OrderInfo createOrder(User user, GoodsVo goods) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getSecKillPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId());
		long orderId = orderDao.insert(orderInfo);
		SecKillOrder secKillOrder = new SecKillOrder();
		secKillOrder.setGoodsId(goods.getId());
		secKillOrder.setOrderId(orderId);
		secKillOrder.setUserId(user.getId());
		orderDao.insertSecKillOrder(secKillOrder);
		return orderInfo;
	}
	
}
