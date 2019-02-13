package com.joey.seckill.dao;

import java.util.List;

import com.joey.seckill.pojo.Goods;
import com.joey.seckill.pojo.SecKillGoods;
import com.joey.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface GoodsDao {
	
	@Select("select g.*,mg.stockCount, mg.startDate, mg.endDate,mg.seckillPrice from seckill_goods mg left join goods g on mg.goodsId = g.id")
	public List<GoodsVo> listGoodsVo();

	@Select("select g.*,mg.stockCount, mg.startDate, mg.endDate,mg.seckillPrice from seckill_goods mg left join goods g on mg.goodsId = g.id where g.id = #{goodsId}")
	public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

	@Update("update seckill_goods set stockCount = stockCount - 1 where goodsId = #{goodsId}")
	public int reduceStock(SecKillGoods g);
	
}
