package com.joey.seckill.dao;

import com.joey.seckill.pojo.OrderInfo;
import com.joey.seckill.pojo.SecKillOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface OrderDao {
	
	@Select("select * from seckill_order where userId=#{userId} and goodsId=#{goodsId}")
	public SecKillOrder getSecKillOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

	@Insert("insert into order_info(userId, goodsId, goodsName, goodsCount, goodsPrice, orderChannel, status, createDate)values("
			+ "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
	@SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
	public long insert(OrderInfo orderInfo);
	
	@Insert("insert into seckill_order (userId, goodsId, orderId)values(#{userId}, #{goodsId}, #{orderId})")
	public int insertSecKillOrder(SecKillOrder secKillOrder);

	
}
