package com.square.mall.item.center.biz.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mysql数据源配置
 *
 * @author Gencent
 * @date 2020/7/22
 */
@Configuration
@MapperScan(basePackages = "com.square.mall.item.center.biz.dao", sqlSessionFactoryRef = "itemSessionFactory")
public class MysqlDataConfig {

    @Value("${mysql.item.dataSource.userName}")
    private String userName;
    @Value("${mysql.item.dataSource.password}")
    private String password;
    @Value("${mysql.item.dataSource.url}")
    private String url;
    @Value("${mysql.item.dataSource.driverClassName}")
    private String driverClassName;
    @Value("${mysql.item.dataSource.validationQuery}")
    private String validationQuery;
    @Value("${mysql.item.dataSource.maxPoolSize}")
    private int maxPoolSize;

    @Bean(name = "itemDataSource")
    @Primary
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(userName);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        config.setMaximumPoolSize(maxPoolSize);
        config.setConnectionTestQuery(validationQuery);

        return new HikariDataSource(config);

    }

    @Bean(name = "itemSessionFactory")
    @Primary
    public SqlSessionFactory sessionFactory() throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/**/*Mapper.xml"));
        sessionFactory.setTypeAliasesPackage("com.square.mall.item.center.biz.eo");
        sessionFactory.setVfs(SpringBootVFS.class);

        return sessionFactory.getObject();

    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager() {

        return new DataSourceTransactionManager(dataSource());

    }


}

