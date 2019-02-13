package com.joey.seckill.redis;

 /**
  *	@author Joey
  * @create 2019-01-31
  * @since 1.0.0
  */
public interface KeyPrefix {
		
	public int expireSeconds();
	
	public String getPrefix();
	
}
