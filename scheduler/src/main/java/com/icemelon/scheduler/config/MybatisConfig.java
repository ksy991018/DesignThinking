package com.icemelon.scheduler.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.icemelon.scheduler.mapper")
public class MybatisConfig {

    @Bean
    public DataSourceTransactionManager transactionManager() {

        DataSourceTransactionManager m = new DataSourceTransactionManager();

        m.setDataSource(dataSource());

        return m;
    }
    @Bean
    public SqlSessionTemplate template() {

        SqlSessionTemplate template = null;
        try {
            template = new SqlSessionTemplate(sqlSessionFactory());
        }catch (Exception e) {e.printStackTrace();}
        return template;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());

        ClassPathResource config = new ClassPathResource("/config/sqlMapConfig.xml");



        factoryBean.setConfigLocation(config);
        return factoryBean.getObject();
    }

    @Bean
    public DataSource dataSource() {

        SingleConnectionDataSource ds = new SingleConnectionDataSource();

        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");

        ds.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");

        ds.setUsername("root");

        ds.setPassword("wnstjr7");

        ds.setSuppressClose(true);
        return ds;

    }

}
