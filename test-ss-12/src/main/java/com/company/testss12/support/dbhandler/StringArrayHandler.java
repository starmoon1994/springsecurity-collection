package com.company.testss12.support.dbhandler;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

public class StringArrayHandler  implements TypeHandler<String[]> {

	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		if (parameter != null && parameter.length > 0) {
			String json = JSON.toJSONString(parameter);
			ps.setString(i, json);
		} else {
			ps.setNull(i, Types.VARCHAR);
		}
	}

	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		String columnValue = rs.getString(columnName);
		if (columnValue != null) {
			return JSON.parseArray(columnValue, String.class).toArray(new String[0]);
		}
		return null;
	}

	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex);
		if (columnValue != null) {
			return JSON.parseArray(columnValue, String.class).toArray(new String[0]);
		}
		return null;
	}

	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String columnValue = cs.getString(columnIndex);
		if (columnValue != null) {
			return JSON.parseArray(columnValue, String.class).toArray(new String[0]);
		}
		return null;
	}

}
