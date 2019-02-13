package com.joey.seckill.redis;

/**
 * @author Joey
 * @create 2019-01-31
 * @since 1.0.0
 */
public class OrderKey extends BasePrefix {

	public OrderKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

}
