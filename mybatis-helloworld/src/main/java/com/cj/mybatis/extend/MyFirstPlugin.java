package com.cj.mybatis.extend;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

@Intercepts({
	@Signature(type=StatementHandler.class,method="parameterize",args=Statement.class)
})
public class MyFirstPlugin implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("MyFirstPlugin#intercept:"+invocation.getMethod());
		
		// test: change sql parameter value
		/*
		Object target = invocation.getTarget();
		// 拿到target的元数据
		MetaObject metaObject = SystemMetaObject.forObject(target);
		// StatementHandler -> ParameterHandler -> parameterObject
		Object value = metaObject.getValue("parameterHandler.parameterObject");
		System.out.println("Get Sql Parameter Value:" + value);
		metaObject.setValue("parameterHandler.parameterObject", 11);
		*/
		
		Object proceed = invocation.proceed();
		return proceed;
	}
	
	@Override
	public Object plugin(Object target) {
		// 使用当前Interceptor封装目标对象，返回一个新的代理对象
		Object wrap = Plugin.wrap(target, this);
		System.out.println("MyFirstPlugin#plugin wrap:"+target);
		return wrap;
	}

	@Override
	public void setProperties(Properties properties) {
		// 获取Plugin注册时（mybatis-config.xml <plugins> <plugin>）设置的property
		System.out.println("MyFirstPlugin#setProperties:"+properties);
	}
}
