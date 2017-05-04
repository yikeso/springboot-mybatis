package com.china.ciic.bookgenerate.common.datasource;

/**
 * 数据源对应的常量
 * Created by kakasun on 2017/5/3.
 */
public enum DatabaseType {

    STUDY_MANAGE,WEB_READER;

    public String getLable() {
        switch (this) {
            case STUDY_MANAGE:
                return "电子书资源中心";
            case WEB_READER:
                return "电子书txt制作，异常管理";
            default:
                return "电子书txt制作，异常管理";
        }
    }
}
