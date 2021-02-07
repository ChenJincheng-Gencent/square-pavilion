package com.square.mall.job.application.config;

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
 * @date 2021/2/7
 */
@Configuration
@MapperScan(basePackages = "com.square.mall.job.application.service.dao", sqlSessionFactoryRef = "jobSessionFactory")
public class MysqlDataConfig {

    @Value("${mysql.job.dataSource.userName}")
    private String userName;
    @Value("${mysql.job.dataSource.password}")
    private String password;
    @Value("${mysql.job.dataSource.url}")
    private String url;
    @Value("${mysql.job.dataSource.driverClassName}")
    private String driverClassName;
    @Value("${mysql.job.dataSource.validationQuery}")
    private String validationQuery;
    @Value("${mysql.job.dataSource.maxPoolSize}")
    private int maxPoolSize;

    @Bean(name = "jobDataSource")
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

    @Bean(name = "jobSessionFactory")
    @Primary
    public SqlSessionFactory sessionFactory() throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/**/*Mapper.xml"));
        sessionFactory.setTypeAliasesPackage("com.square.mall.member.center.service.eo");
        sessionFactory.setVfs(SpringBootVFS.class);

        return sessionFactory.getObject();

    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager() {

        return new DataSourceTransactionManager(dataSource());

    }


}
