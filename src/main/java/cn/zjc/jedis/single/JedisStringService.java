package cn.zjc.jedis.single;


import org.springframework.stereotype.Service;


/**
 * @author zhangjinci
 * @version 2016/8/10 12:20
 * @function jedis处理值为String的对象
 */

@Service
public class JedisStringService extends AbstractJedisService<String,String>{

	@Override
	protected Class getKeyType() {
		return String.class;
	}

	@Override
	protected Class getValueType() {
		return String.class;
	}
}
