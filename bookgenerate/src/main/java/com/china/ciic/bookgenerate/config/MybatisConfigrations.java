package com.china.ciic.bookgenerate.config;

import com.china.ciic.bookgenerate.common.datasource.DatabaseType;
import com.china.ciic.bookgenerate.common.datasource.DynamicDataSource;
import com.github.pagehelper.PageHelper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Created by kakasun on 2017/5/3.
 */
@Configuration
@MapperScan(basePackages = "com.china.ciic.*.dao.mapper")
public class MybatisConfigrations {

    @Autowired
    private Environment env;//springboot 的环境变量

    /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    public DataSource studyManageDataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("datasource.max-active")));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("datasource.max-idle")));
        dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("datasource.initial-size")));
        return dataSource;
    }

    public DataSource webReaderDataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("jdbc2.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("jdbc2.url"));
        dataSource.setUser(env.getProperty("jdbc2.username"));
        dataSource.setPassword(env.getProperty("jdbc2.password"));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("datasource2.max-active")));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("datasource2.max-idle")));
        dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("datasource2.initial-size")));
        return dataSource;
    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource() throws Exception {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        DataSource dataSource = webReaderDataSource();
        targetDataSources.put(DatabaseType.WEB_READER, dataSource);
        dataSource = studyManageDataSource();
        targetDataSources.put(DatabaseType.STUDY_MANAGE,dataSource);
        DynamicDataSource dds = new DynamicDataSource();
        dds.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dds.setDefaultTargetDataSource(dataSource);// 默认的datasource设置为studyManageDataSource
        return dds;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
/*        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
        fb.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));*/
        PageHelper pageHelper = new PageHelper();
        Properties prop = new Properties();
        prop.setProperty("offsetAsPageNum","true");
        prop.setProperty("rowBoundsWithCount","true");
        prop.setProperty("reasonable","true");
        pageHelper.setProperties(prop);
        fb.setPlugins(new Interceptor[]{pageHelper});
        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
