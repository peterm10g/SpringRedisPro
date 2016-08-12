package cn.zjc.single;

import cn.zjc.config.serializer.FastjsonSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zhangjinci
 * @version 2016/8/10 12:20
 * @function jedis处理值为String的对象
 */

@Service
public class JedisStringService implements InitializingBean{




	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 设置key-value，长期生存
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		set(key.getBytes(), value.getBytes());
	}

	/**
	 * 设置key-value,添加生存时间expire
	 *
	 * @param key
	 * @param value
	 * @param expire
	 */
	public void set(String key, String value, long expire) {
		set(key.getBytes(), value.getBytes(), expire);
	}

	/**
	 * 根据key获取value
	 *
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return get(key.getBytes());
	}

	/**
	 * 根据key删除value
	 *
	 * @param key
	 */
	public void del(String key) {
		del(key.getBytes());
	}

	/**
	 * 根据keys批量删除
	 * @param keys
	 */
	public void del(String... keys) {
		int length = keys.length;
		if (length > 0) {
			byte[][] bytes = new byte[length][];
			for (int i = 0; i < length; i++) {
				bytes[i] = keys[i].getBytes();
			}
			del(bytes);
		}
	}

	/**
	 * 根据keys批量删除
	 * @param keys
	 */
	public void del(List<String> keys){
		int length = keys.size();
		if (length > 0) {
			byte[][] bytes = new byte[length][];
			for (int i = 0; i < length; i++) {
				bytes[i] = keys.get(i).getBytes();
			}
			del(bytes);
		}
	}

	public void set(byte[] key, byte[] value) {
		stringRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
				redisConnection.set(key, value);
				return null;
			}
		});
	}


	public void set(byte[] key, byte[] value, long expire) {
		stringRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
				redisConnection.setEx(key, expire, value);
				return null;
			}
		});
	}


	public String get(byte[] key) {
		return stringRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
				byte[] value = redisConnection.get(key);
				if (value == null || value.length == 0){
					return null;
				}else {
					return new String(value);
				}
			}
		});
	}

	public void del(byte[] key) {
		stringRedisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
				return redisConnection.del(key);
			}
		});
	}

	public void del(byte[]... key) {
		stringRedisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
				return redisConnection.del(key);
			}
		});
	}


	//默认使用fastjson序列化器
	@Override
	public void afterPropertiesSet() throws Exception {
         stringRedisTemplate.setValueSerializer(new FastjsonSerializer<>(String.class));
	}
}
