//package cn.zjc.config;
//
//
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author zhangjinci
// * @version 2016/8/10 16:09
// * @function
// */
//@Repository
//public class ClusterConfigurationProperties {
//
//    /*
//     * spring.redis.cluster.nodes[0] = 127.0.0.1:7379
//     * spring.redis.cluster.nodes[1] = 127.0.0.1:7380
//     * ...
//     */
//    List<String> nodes = new ArrayList<>();
//
//
//    /**
//     * Get initial collection of known cluster nodes in format {@code host:port}.
//     *
//     * @return
//     */
//    public List<String> getNodes() {
//        return nodes;
//    }
//
//    public void setNodes(List<String> nodes) {
//        this.nodes = nodes;
//    }
//
//    public void addNode(String node){
//        nodes.add(node);
//    }
//
//
//}
