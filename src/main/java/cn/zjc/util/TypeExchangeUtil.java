package cn.zjc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author zhangjinci
 * @version 2016/8/12 18:14
 * @function
 */
public class TypeExchangeUtil {

    private static final Logger log = LoggerFactory.getLogger(TypeExchangeUtil.class);

    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos);
        ) {
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (Exception e) {
            log.error("parse object to byte array failed");
            return null;
        }
        return bytes;
    }


    public static Object toObject(byte[] bytes) {
        Object obj = null;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis);
        ) {
            obj = ois.readObject();
        } catch (Exception e) {
            log.error("parse byte array to object failed");
            return null;
        }
        return obj;
    }
}
