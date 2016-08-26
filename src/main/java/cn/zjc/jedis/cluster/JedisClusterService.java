package cn.zjc.jedis.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * @author zjc
 * @version 2016/8/10 23:47
 * @description jedis集群服务类
 */
@Service
public class JedisClusterService {

    @Autowired(required = false)
    private JedisClusterFactory jedisClusterFactory;

    private JedisCluster jedisCluster;

    public JedisCluster getJedisCluster() {
        return jedisClusterFactory.getJedisCluster();
    }

}
