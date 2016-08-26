package cn.zjc.jedis.single;


import org.springframework.stereotype.Service;


/**
 * @author zjc
 * @version 2016/8/11 0:21
 * @description 处理jedis值为Object的服务类
 */
@Service
public class JedisObjectService extends AbstractJedisService<String,Object> {

    @Override
    protected Class getKeyType() {
        return String.class;
    }

    @Override
    protected Class getValueType() {
        return Object.class;
    }
}
