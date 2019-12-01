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
public class SecondDruidConfig {
	
	@Bean(name="secondDataSource")
	@ConfigurationProperties(prefix="spring.datasource.second")
	public DataSource secondDataSource() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Bean(name="secondSqlSessionFactory")
	public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) 
			throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		
		//factoryBean.setTypeAliasesPackage("com.cj.mybatis.entity");
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mybatis/mapper/second/*Mapper.xml"));
        factoryBean.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource("classpath:mybatis-config.xml"));
        
		return factoryBean.getObject();
		
	}
	
	@Bean(name="secondTransactionManager")
	public DataSourceTransactionManager secondDataSourceTransactionManager(@Qualifier("secondDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(name="secondSqlSessionTemplate")
	public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) 
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
