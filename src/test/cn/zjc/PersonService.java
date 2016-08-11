package cn.zjc;

import cn.zjc.entity.Person;
import cn.zjc.single.JedisTypeService;
import org.springframework.stereotype.Service;

/**
 * @author zjc
 * @version 2016/8/12 0:57
 * @description
 */
@Service
public class PersonService extends JedisTypeService<Person>{

	@Override
	protected Class<Person> getTypeClass() {
		return Person.class;
	}
}
