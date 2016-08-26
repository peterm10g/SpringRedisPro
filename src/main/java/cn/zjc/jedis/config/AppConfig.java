//package cn.zjc.jedis.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//
///**
// * @author zhangjinci
// * @version 2016/8/10 16:08
// * @function
// */
//@Configuration
//public class AppConfig {
//
//     @Value("${spring.redis.cluster.nodes[0]}")
//     private String node;
//
//     @Autowired
//      private ClusterConfigurationProperties clusterProperties;
//
//     public @Bean
//    RedisConnectionFactory connectionFactory(){
//         clusterProperties.addNode(node);
//         return  new JedisConnectionFactory(
//                 new RedisClusterConfiguration(clusterProperties.getNodes()));
//     }
//
//
//}
