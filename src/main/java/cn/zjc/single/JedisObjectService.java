package cn.zjc.single;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @author zjc
 * @version 2016/8/11 0:21
 * @description 处理jedis值为Object的服务类
 */
@Service
public class JedisObjectService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * set操作
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		operations.set(key, value);
	}

	/**
	 * set操作,设置生存周期(单位:second)
	 *
	 * @param key
	 * @param value
	 * @param expire
	 */
	public void set(String key, String value, long expire) {
		set(key, value, expire, TimeUnit.SECONDS);
	}

	/**
	 * set操作,生存模式自定义
	 *
	 * @param key
	 * @param value
	 * @param expire
	 * @param timeUnit
	 */
	public void set(String key, Object value, long expire, TimeUnit timeUnit) {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		operations.set(key, value, expire, timeUnit);
	}

	/**
	 * get操作
	 *
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		return operations.get(key);
	}

	/**
	 * 删除
	 * @param key
	 */
	public void del(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 匹配删除
	 * @param pattern
	 */
	public void delByPattern(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (keys != null && !keys.isEmpty()) {
			redisTemplate.delete(keys);
		}
	}


	


}
