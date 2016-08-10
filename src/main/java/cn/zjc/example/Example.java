//package cn.zjc.example;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//
//
///**
// * @author zhangjinci
// * @version 2016/8/10 12:20
// * @function 例子
// */
//
//@Service
//public class Example {
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    public void set(String key, String value) {
//        set(key.getBytes(), value.getBytes());
//    }
//
//    public void set(byte[] key, byte[] value) {
//        stringRedisTemplate.execute(new RedisCallback<String>() {
//            @Override
//            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                redisConnection.set(key, value);
//                return null;
//            }
//        });
//
//    }
//
//    public String get(String key){
//       return get(key.getBytes());
//    }
//
//    public String get(byte[] key) {
//        return stringRedisTemplate.execute(new RedisCallback<String>() {
//            @Override
//            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                return new String(redisConnection.get(key));
//            }
//        });
//    }
//}
