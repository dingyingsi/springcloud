package com.dys.spirngcloud.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.third")
public class Third {
    private String url;
    private String username;
    private String password;
    private Integer initialSize;
    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;
    private Long maxWaitMillis;
    private Boolean removeAbandonedOnMaintenance;
    private Boolean removeAbandonedOnBorrow;
    private Boolean jdbcCompliantTruncation;


}
