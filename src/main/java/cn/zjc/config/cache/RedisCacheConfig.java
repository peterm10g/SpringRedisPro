package cn.zjc.config.cache;

import cn.zjc.config.serializer.FastjsonSerializer;
import cn.zjc.single.JedisObjectService;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

/**
 * @author zhangjinci
 * @version 2016/8/12 18:07
 * @function 使用redis实现SpringCache
 */
public class RedisCacheConfig extends JedisObjectService implements Cache {

    private String name;
    private Long defaule_expire = 30 * 1000L; //默认生存周期

    public Long getDefaule_expire() {
        return defaule_expire;
    }

    public void setDefaule_expire(Long defaule_expire) {
        this.defaule_expire = defaule_expire;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return super.redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object obj = super.get(key);
        return obj != null ? new SimpleValueWrapper(obj) : null;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        final String key = (String) o;
        super.redisTemplate.setValueSerializer(new FastjsonSerializer<>(aClass));
        return redisTemplate.execute(new RedisCallback<T>() {

            @Override
            public T doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return (T) redisConnection.get(key.getBytes());
            }
        });
    }

    @Override
    public void put(Object o, Object o1) {
        super.set((String) o, o1, defaule_expire);
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        put(o, o1);
        return new SimpleValueWrapper(o1);
    }

    @Override
    public void evict(Object o) {
        final String key = (String) o;
        super.del(key);
    }

    @Override
    public void clear() {
        super.redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();
                return true;
            }
        });
    }

}
