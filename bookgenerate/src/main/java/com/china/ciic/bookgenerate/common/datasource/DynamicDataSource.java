package com.china.ciic.bookgenerate.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * Created by kakasun on 2017/5/3.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 使用DatabaseContextHolder获取当前线程的DatabaseType
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDatabaseType();
    }
}
