package com.square.mall.member.center.biz.config;

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
 * @date 2019/8/21
 */
@Configuration
@MapperScan(basePackages = "com.square.mall.member.center.biz.dao", sqlSessionFactoryRef = "memberSessionFactory")
public class MysqlDataConfig {

    @Value("${mysql.member.dataSource.userName}")
    private String userName;
    @Value("${mysql.member.dataSource.password}")
    private String password;
    @Value("${mysql.member.dataSource.url}")
    private String url;
    @Value("${mysql.member.dataSource.driverClassName}")
    private String driverClassName;
    @Value("${mysql.member.dataSource.validationQuery}")
    private String validationQuery;
    @Value("${mysql.member.dataSource.maxPoolSize}")
    private int maxPoolSize;

    @Bean(name = "memberDataSource")
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

    @Bean(name = "memberSessionFactory")
    @Primary
    public SqlSessionFactory sessionFactory() throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/**/*Mapper.xml"));
        sessionFactory.setTypeAliasesPackage("com.square.mall.member.center.biz.eo");
        sessionFactory.setVfs(SpringBootVFS.class);

        return sessionFactory.getObject();

    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager() {

        return new DataSourceTransactionManager(dataSource());

    }


}
