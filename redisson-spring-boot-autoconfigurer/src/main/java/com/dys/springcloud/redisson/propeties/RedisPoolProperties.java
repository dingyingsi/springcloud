package com.dys.springcloud.redisson.propeties;

/**
 * @author dingyingsi
 * @date 2020-03-03 10:42
 */


public class RedisPoolProperties {

    private int connectionMinimumIdleSize;

    /**
     * 客户端连接超时间
     */
    private int connectTimeout;


    private int idleConnectionTimeout;

    /**
     * 连接池大小
     */
    private int connectionPoolSize;

    public int getConnectionMinimumIdleSize() {
        return connectionMinimumIdleSize;
    }

    public void setConnectionMinimumIdleSize(int connectionMinimumIdleSize) {
        this.connectionMinimumIdleSize = connectionMinimumIdleSize;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getIdleConnectionTimeout() {
        return idleConnectionTimeout;
    }

    public void setIdleConnectionTimeout(int idleConnectionTimeout) {
        this.idleConnectionTimeout = idleConnectionTimeout;
    }

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }
}