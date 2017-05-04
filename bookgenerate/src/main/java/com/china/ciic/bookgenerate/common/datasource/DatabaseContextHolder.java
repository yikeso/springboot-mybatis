package com.china.ciic.bookgenerate.common.datasource;

/**
 * 保存一个线程安全的DatabaseType容器
 * Created by kakasun on 2017/5/3.
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType(){
        return contextHolder.get();
    }
}
