package com.joey.seckill.redis;

/**
 * @author Joey
 * @create 2019-01-31
 * @since 1.0.0
 */
public class UserKey extends BasePrefix{

	private UserKey(String prefix) {
		super(prefix);
	}
	public static UserKey getById = new UserKey("id");
	public static UserKey getByName = new UserKey("name");

	public static final int TOKEN_EXPIRE = 3600*24 * 2;
	private UserKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static UserKey token = new UserKey(TOKEN_EXPIRE, "tk");
}
