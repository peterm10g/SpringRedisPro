package cn.zjc.single;

import cn.zjc.config.serializer.FastjsonSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;

/**
 * @author zhangjinci
 * @version 2016/8/11 18:07
 * @function
 */
@Service
public class JedisTypeService<T> implements InitializingBean{

    @Autowired
    private RedisTemplate<String,T> redisTemplate;

    protected ValueOperations<String,T> valueOperations;

    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    private Class<T> getClazz(){
        if (clazz == null) {
            clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
        return clazz;
    }


    public void set(String key,T value){
      valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public void set(String key,T value,long expire){
        valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value,expire);
    }




    //设置序列化对象clazz
    @Override
    public void afterPropertiesSet() throws Exception {
         redisTemplate.setValueSerializer(new FastjsonSerializer<>(getClazz()));
    }
}
