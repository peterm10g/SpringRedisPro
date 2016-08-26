package cn.zjc.jedis.stats;

import com.google.common.base.Objects;

/**
 * @author zhangjinci
 * @version 2016/8/19 11:27
 * @function 统计jedis各项数据, 只提供jedis从开始进行服务到查询的时刻
 */
public final class RedisStats {

    private final long hitCount;  //命中数量
    private final long missCount;  //miss数量
    private final long loadSuccessCount; //成功加载数量
    private final long loadExceptionCount; //加载异常数量
    private final long totalLoadTime;  //总加载时间
    private final long evictionCount;  //总的移除数量(包含手动删除、覆盖、过期等)

    private void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public RedisStats(long hitCount, long missCount,
                      long loadSuccessCount, long loadExceptionCount,
                      long totalLoadTime, long evictionCount) {
        checkArgument(hitCount >= 0);
        checkArgument(missCount >= 0);
        checkArgument(loadSuccessCount >= 0);
        checkArgument(loadExceptionCount >= 0);
        checkArgument(totalLoadTime >= 0);
        checkArgument(evictionCount >= 0);
        this.hitCount = hitCount;
        this.missCount = missCount;
        this.loadSuccessCount = loadSuccessCount;
        this.loadExceptionCount = loadExceptionCount;
        this.totalLoadTime = totalLoadTime;
        this.evictionCount = evictionCount;
    }

    //统计总体命中率
    public double hitRate() {
        long requestCount = getRequestCount();
        return (requestCount == 0) ? 1.0 : (double) hitCount / requestCount;
    }

    //统计总体Miss率
    public double missRate() {
        long requestCount = getRequestCount();
        return (requestCount == 0) ? 0.0 : (double) missCount / requestCount;
    }

    //总加载数量
    public long getLoadCount() {
        return loadSuccessCount + loadExceptionCount;
    }

    //总共请求次数
    public long getRequestCount() {
        return hitCount + missCount;
    }

    //异常加载率
    public double getLoadExceptionRate() {
        long totalLoadCount = loadSuccessCount + loadExceptionCount;
        return (totalLoadCount == 0)
                ? 0.0
                : (double) loadExceptionCount / totalLoadCount;
    }

    //平均加载时间
    public double getAverageLoadPenalty() {
        long totalLoadCount = loadSuccessCount + loadExceptionCount;
        return (totalLoadCount == 0)
                ? 0.0
                : (double) totalLoadTime / totalLoadCount;
    }

    public long getHitCount() {
        return hitCount;
    }

    public long getMissCount() {
        return missCount;
    }

    public long getLoadSuccessCount() {
        return loadSuccessCount;
    }

    public long getLoadExceptionCount() {
        return loadExceptionCount;
    }

    public long getTotalLoadTime() {
        return totalLoadTime;
    }

    public long getEvictionCount() {
        return evictionCount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hitCount, missCount, loadSuccessCount, loadExceptionCount,
                totalLoadTime, evictionCount);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof RedisStats) {
            RedisStats other = (RedisStats) object;
            return hitCount == other.hitCount
                    && missCount == other.missCount
                    && loadSuccessCount == other.loadSuccessCount
                    && loadExceptionCount == other.loadExceptionCount
                    && totalLoadTime == other.totalLoadTime
                    && evictionCount == other.evictionCount;
        }
        return false;
    }

    @Override
    public String toString() {
        return "RedisStats{" +
                "hitCount=" + hitCount +
                ", missCount=" + missCount +
                ", loadSuccessCount=" + loadSuccessCount +
                ", loadExceptionCount=" + loadExceptionCount +
                ", totalLoadTime=" + totalLoadTime +
                ", evictionCount=" + evictionCount +
                '}';
    }
}
