package com.joey.seckill.vo;

import java.util.Date;

import com.joey.seckill.pojo.Goods;

public class GoodsVo extends Goods {
	private Double seckillPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;

	public Double getSecKillPrice() {
		return seckillPrice;
	}

	public void setSecKillPrice(Double seckillPrice) {
		this.seckillPrice = seckillPrice;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "GoodsVo{" +
				"seckillPrice=" + seckillPrice +
				", stockCount=" + stockCount +
				", startDate=" + startDate +
				", endDate=" + endDate +
				'}';
	}
}
