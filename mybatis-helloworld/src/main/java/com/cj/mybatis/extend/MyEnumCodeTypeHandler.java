package com.cj.mybatis.extend;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.cj.mybatis.entity.EmployeeStatusEnum;


// 实现TypeHandler接口，或者继承 BaseTypeHandler
//public class MyEnumCodeTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E>{
public class MyEnumCodeTypeHandler extends BaseTypeHandler<EmployeeStatusEnum>{

	private final Class<EmployeeStatusEnum> type;
	private final Map<Integer,EmployeeStatusEnum> enumMap;
	private final EmployeeStatusEnum[] enums;

	public MyEnumCodeTypeHandler(Class<EmployeeStatusEnum> type) {
	    if (type == null) {
		      throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
		this.enums = type.getEnumConstants();
		if (this.enums == null) {
		  throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
		}
		enumMap=new HashMap<Integer,EmployeeStatusEnum>();
		for(EmployeeStatusEnum e:enums) {
			enumMap.put(e.getCode(), e);
		}
	}
	  
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, EmployeeStatusEnum parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getCode());
	}

	@Override
	public EmployeeStatusEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int code = rs.getInt(columnName);
	    if (code == 0 && rs.wasNull()) {
	      return null;
	    }
	    return toCodeEnum(code);
	}
	
	@Override
	public EmployeeStatusEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int code = rs.getInt(columnIndex);
	    if (code == 0 && rs.wasNull()) {
	      return null;
	    }
	    return toCodeEnum(code);
	}

	@Override
	public EmployeeStatusEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int code = cs.getInt(columnIndex);
	    if (code == 0 && cs.wasNull()) {
	      return null;
	    }
	    return toCodeEnum(code);
	}
	
	private EmployeeStatusEnum toCodeEnum(int code) {
		try {
		  return this.enumMap.get(code);
		} catch (Exception ex) {
		  throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getSimpleName() + " by code value.", ex);
		}
	}
	
}

