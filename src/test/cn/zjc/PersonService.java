package cn.zjc;

import cn.zjc.entity.Person;
import cn.zjc.single.AbstractJedisService;
import org.springframework.stereotype.Service;

/**
 * @author zjc
 * @version 2016/8/12 0:57
 * @description
 */
@Service
public class PersonService extends AbstractJedisService<String,Person> {


	@Override
	protected Class getKeyType() {
		return String.class;
	}

	@Override
	protected Class getValueType() {
		return Person.class;
	}
}
