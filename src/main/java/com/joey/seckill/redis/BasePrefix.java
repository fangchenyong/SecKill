package com.joey.seckill.redis;

/**
 * @author Joey
 * @create 2019-01-31
 * @since 1.0.0
 */
public abstract class BasePrefix implements KeyPrefix{
	
	private int expireSeconds;
	
	private String prefix;

	/**
	 * 0表是永不过期
	 * @param prefix
	 */
	public BasePrefix(String prefix) {
		this(0, prefix);
	}
	
	public BasePrefix( int expireSeconds, String prefix) {
		this.expireSeconds = expireSeconds;
		this.prefix = prefix;
	}
	@Override
	public int expireSeconds() {//默认0代表永不过期
		return expireSeconds;
	}

	@Override
	public String getPrefix() {
		String className = getClass().getSimpleName();
		return className+":" + prefix;
	}

}
