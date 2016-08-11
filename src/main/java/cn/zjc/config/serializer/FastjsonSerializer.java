package cn.zjc.config.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;


import java.nio.charset.Charset;

/**
 * @author zhangjinci
 * @version 2016/8/11 16:02
 * @function
 */
public class FastjsonSerializer<T> implements RedisSerializer<T> {

    private static final Logger log = LoggerFactory.getLogger(FastjsonSerializer.class);

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    public FastjsonSerializer() {
    }

    public FastjsonSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T deserialize(byte[] data) throws SerializationException {
        if (data == null || data.length == 0) {
            return null;
        } else {
            String src = new String(data, DEFAULT_CHARSET);
            return JSON.parseObject(src, clazz);
        }
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        } else {
            return JSON.toJSONBytes(t, SerializerFeature.WriteClassName);
        }
    }

}
