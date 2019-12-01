package com.cj.mybatis.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class FirstDruidConfig {
	
//	@Primary
	@Bean(name="firstDataSource")
	@ConfigurationProperties(prefix="spring.datasource.first")
	public DataSource firstDataSource() {
		return DruidDataSourceBuilder.create().build();
	}
	
//	@Primary
	@Bean(name="firstSqlSessionFactory")
	public SqlSessionFactory firstSqlSessionFactory(@Qualifier("firstDataSource") DataSource dataSource) 
			throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		
		//factoryBean.setTypeAliasesPackage("com.cj.mybatis.entity");
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mapper/first/*Mapper.xml"));
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource("classpath:mybatis-config.xml"));
    
		return factoryBean.getObject();	
	}
	
//	@Primary
	@Bean(name="firstTransactionManager")
	public DataSourceTransactionManager firstDataSourceTransactionManager(@Qualifier("firstDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
//	@Primary
	@Bean(name="firstSqlSessionTemplate")
	public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory) 
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
