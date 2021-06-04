package com.square.mall.trade.center.service.config;


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
@MapperScan(basePackages = "com.square.mall.trade.center.biz.dao", sqlSessionFactoryRef = "tradeSessionFactory")
public class MysqlDataConfig {

    @Value("${mysql.trade.dataSource.userName}")
    private String userName;
    @Value("${mysql.trade.dataSource.password}")
    private String password;
    @Value("${mysql.trade.dataSource.url}")
    private String url;
    @Value("${mysql.trade.dataSource.driverClassName}")
    private String driverClassName;
    @Value("${mysql.trade.dataSource.validationQuery}")
    private String validationQuery;
    @Value("${mysql.trade.dataSource.maxPoolSize}")
    private int maxPoolSize;

    @Bean(name = "tradeDataSource")
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

    @Bean(name = "tradeSessionFactory")
    @Primary
    public SqlSessionFactory sessionFactory() throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/**/*Mapper.xml"));
        sessionFactory.setTypeAliasesPackage("com.square.mall.trade.center.bize.eo");
        sessionFactory.setVfs(SpringBootVFS.class);

        return sessionFactory.getObject();

    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager() {

        return new DataSourceTransactionManager(dataSource());

    }


}

