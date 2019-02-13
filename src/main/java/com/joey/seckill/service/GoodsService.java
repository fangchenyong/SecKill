package com.joey.seckill.service;

import java.util.List;

import com.joey.seckill.dao.GoodsDao;
import com.joey.seckill.pojo.SecKillGoods;
import com.joey.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GoodsService {
	
	@Autowired
	GoodsDao goodsDao;
	
	public List<GoodsVo> listGoodsVo(){
		System.out.println("service："+goodsDao.listGoodsVo());
		return goodsDao.listGoodsVo();
	}

	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		System.out.println("service："+goodsDao.getGoodsVoByGoodsId(1));
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

	public void reduceStock(GoodsVo goods) {
		SecKillGoods g = new SecKillGoods();
		g.setGoodsId(goods.getId());
		goodsDao.reduceStock(g);
	}
	
	
	
}
