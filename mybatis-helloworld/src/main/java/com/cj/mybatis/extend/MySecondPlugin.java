package com.cj.mybatis.extend;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts({
	@Signature(type=StatementHandler.class,method="parameterize",args=Statement.class)
})
public class MySecondPlugin implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("MySecondPlugin#intercept:"+invocation.getMethod());
		Object proceed = invocation.proceed();
		return proceed;
	}
	
	@Override
	public Object plugin(Object target) {
		// 使用当前Interceptor封装目标对象，返回一个新的代理对象
		Object wrap = Plugin.wrap(target, this);
		System.out.println("MySecondPlugin#plugin wrap:"+target);
		return wrap;
	}

	@Override
	public void setProperties(Properties properties) {
		// 获取Plugin注册时（mybatis-config.xml <plugins> <plugin>）设置的property
		System.out.println("MySecondPlugin#setProperties:"+properties);
	}
}
