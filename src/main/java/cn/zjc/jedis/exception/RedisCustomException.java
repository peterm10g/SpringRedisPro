package cn.zjc.jedis.exception;

/**
 * @author zhangjinci
 * @version 2016/8/15 8:45
 * @function 自定义异常类
 */
public class RedisCustomException extends RuntimeException{

    private String msg;

    private String errorCode;

    public RedisCustomException() {
        super();
    }

    public RedisCustomException(String msg) {
        this.msg = msg;
    }

    public RedisCustomException(String msg, String errorCode) {
        super();
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
