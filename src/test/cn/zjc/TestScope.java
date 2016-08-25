package cn.zjc;

import cn.zjc.cluster.JedisClusterFactory;
import cn.zjc.entity.Person;
import cn.zjc.single.JedisBasicService;
import cn.zjc.single.JedisObjectService;
import cn.zjc.single.JedisStringService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisCluster;

/**
 * @author zhangjinci
 * @version 2016/8/10 14:40
 * @function
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class TestScope {

//    @Autowired
//    private Example example;
//
//
//    @Test
//    public void Test1(){
//        example.set("zjc","hello spring-redis");
//
//        System.out.println("取出的值为--> " + example.get("zjc"));
//    }
//
//    @Test
//    public void Test2(){
//        RedisClusterConnection clusterConnection = connectionFactory.getClusterConnection();
//        clusterConnection.set("zjc".getBytes(),"hello world".getBytes());
//
//        String value = new String(clusterConnection.get("zjc".getBytes()));
//
//        System.out.println("取出的值为--> " + value);
//    }


//    @Autowired
//    private JedisClusterFactory jedisClusterFactory;
//
//    @Test
//    public void Test3() {
//        JedisCluster jedisCluster = jedisClusterFactory.getJedisCluster();
//        jedisCluster.set("zjc".getBytes(), "hello zjc".getBytes());
//
//        System.out.println("获取到的值--> " + new String(jedisCluster.get("zjc".getBytes())));
//    }


    @Autowired
    private JedisStringService jedisStringService;
    @Autowired
    private JedisBasicService jedisBasicService;
    @Autowired
    private JedisObjectService jedisObjectService;

    @Autowired
    private PersonService personService;

    @Test
    public void Test3() {
        personService.set("name", new Person(1, "zjcscut"), 5L);


        System.out.println(personService.get("name"));


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(personService.get("name"));


    }


}
