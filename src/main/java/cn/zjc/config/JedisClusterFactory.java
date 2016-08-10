package cn.zjc.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangjinci
 * @version 2016/8/10 17:00
 * @function Redis集群工厂
 */
public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {

    private GenericObjectPoolConfig genericObjectPoolConfig;
    private JedisCluster jedisCluster;
    private Integer connectionTime = 2000;
    private Integer soTimeout = 3000;
    private Integer maxRedirections = 5;
    private Set<String> jedisClusterNodes;
    private String location = "classpath:redis-cluster.properties";
    private String prefix = "spring.redis.cluster";

    private static final String SEPARATOR = ":";

    @Override
    public void afterPropertiesSet() throws Exception {

        //加载配置文件
        jedisClusterNodes = JedisClusterPropertyLoader.loadSettings(location,prefix);

        //获取jedisClusterPropertis
        if (jedisClusterNodes == null || jedisClusterNodes.size() == 0) {
            throw new RuntimeException("Jedis cluster nodes null");
        }

        Set<HostAndPort> haps = new HashSet<>();
        for (String node : jedisClusterNodes) {
            String[] arr = node.split(SEPARATOR);
            if (arr.length != 2) {
                throw new RuntimeException("Jedis cluster nodes address format error");
            }

            haps.add(new HostAndPort(arr[0], Integer.valueOf(arr[1])));
        }

        jedisCluster = new JedisCluster(haps,connectionTime,soTimeout,maxRedirections,genericObjectPoolConfig);
    }


    @Override
    public JedisCluster getObject() throws Exception {
        return jedisCluster;
    }

    @Override
    public Class<?> getObjectType() {
        return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
    }

    //单例的bean
    @Override
    public boolean isSingleton() {
        return true;
    }

    public GenericObjectPoolConfig getGenericObjectPoolConfig() {
        return genericObjectPoolConfig;
    }

    public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
        this.genericObjectPoolConfig = genericObjectPoolConfig;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public Integer getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(Integer connectionTime) {
        this.connectionTime = connectionTime;
    }

    public Integer getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(Integer soTimeout) {
        this.soTimeout = soTimeout;
    }

    public Integer getMaxRedirections() {
        return maxRedirections;
    }

    public void setMaxRedirections(Integer maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    public Set<String> getJedisClusterNodes() {
        return jedisClusterNodes;
    }

    public void setJedisClusterNodes(Set<String> jedisClusterNodes) {
        this.jedisClusterNodes = jedisClusterNodes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
