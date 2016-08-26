package cn.zjc.jedis.config.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author zhangjinci
 * @version 2016/8/10 17:35
 * @function 加载classpath的资源文件
 */
public class JedisClusterPropertyLoader {

    private JedisClusterPropertyLoader() {
    }

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static Logger logger = LoggerFactory.getLogger(JedisClusterPropertyLoader.class);

    private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();


    public static Properties loadClusterProperties(String... locations) {
        Properties props = new Properties();

        for (String location : locations) {
            logger.debug("Loading properties file from classpath:" + location);

            if (location.startsWith("classpath:")) {
                location = location.replace("classpath:", "");  //除去多的classpath
            }

            Resource resource = new ClassPathResource(location);
            try(
                 InputStream in =  resource.getInputStream();
                 InputStreamReader isr = new InputStreamReader(in, DEFAULT_CHARSET)) {
                propertiesPersister.load(props, isr);
            } catch (Exception e) {
                logger.error("Could not load properties from classpath:" + location + "! Error message : " + e.getMessage());
                return null;
            }
        }
        return props;
    }


    public static Set<String> loadSettings(String location, String prefix) {
        Properties pro = loadClusterProperties(location);
        Set<String> re = new HashSet<>();
        if (pro != null) {
            for (Map.Entry<Object, Object> entry : pro.entrySet()) {
                if (prefix != null && prefix.length() > 0) {
                    if (((String) entry.getKey()).startsWith(prefix)) {
                        re.add((String) entry.getValue());
                    }
                } else {
                    re.add((String) entry.getValue());
                }
            }
        }
        return re;
    }

    public static Set<String> loadSettings(String location) {
        return loadSettings(location, null);
    }
}
