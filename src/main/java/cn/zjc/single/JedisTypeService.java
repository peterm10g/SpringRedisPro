package cn.zjc.single;

import cn.zjc.config.serializer.FastjsonSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjinci
 * @version 2016/8/11 18:07
 * @function Jedis类型服务，请注意使用方式，XXEntityService必须继承此抽象类并且实现
 * getTypeClass()方法
 */
@Service
public abstract class JedisTypeService<T> implements InitializingBean {

    @Autowired
    private RedisTemplate<String, T> redisTemplate;

    protected ValueOperations<String, T> valueOperations;

    //T服务类必须实现此类来获取泛型的Class<T>
    //注意T这个实体必须包含一个空的public的构造
    abstract protected Class<T> getTypeClass();


    public void set(String key, T value) {
        valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public void set(String key, T value, long expire) {
        valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, expire);
    }


    public void set(String key, T value, long expire, TimeUnit timeUnit) {
        valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, expire, timeUnit);
    }

    public Object get(String key) {
        valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public Boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }


    public Boolean expire(String key, long expire, TimeUnit timeUnit) {
        return redisTemplate.expire(key, expire, timeUnit);
    }

    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public void del(Collection<String> c) {
        redisTemplate.delete(c);
    }


    //设置序列化对象clazz
    @Override
    public void afterPropertiesSet() throws Exception {
        redisTemplate.setValueSerializer(new FastjsonSerializer<>(getTypeClass()));
    }
}
