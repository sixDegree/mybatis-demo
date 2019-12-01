package com.cj.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.cj.mybatis.mapper")
@MapperScan(basePackages="com.cj.mybatis.dao.first",sqlSessionTemplateRef="firstSqlSessionTemplate")
@MapperScan(basePackages="com.cj.mybatis.dao.second",sqlSessionTemplateRef="secondSqlSessionTemplate")
public class App {
    public static void main( String[] args ){
        SpringApplication.run(App.class, args);
    }
}
