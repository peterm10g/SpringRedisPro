package cn.zjc;

import cn.zjc.api.annotation.Trans;
import cn.zjc.jedis.single.AbstractJedisService;
import org.springframework.stereotype.Service;

/**
 * @author zjc
 * @version 2016/8/12 0:57
 * @description
 */
@Service
@Trans
public class PersonService extends AbstractJedisService<String,Person> {


	@Override
	protected Class getKeyType() {
		return String.class;
	}

	@Override
	protected Class getValueType() {
		return Person.class;
	}

	public void hello(String name){
		System.out.println("hello " + name);
	}
}
