package com.company.testss12.support.dbhandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

public class JsonObjectHandler implements TypeHandler<JSONObject> {

	@Override
	public void setParameter(PreparedStatement ps, int i, JSONObject parameter, JdbcType jdbcType) throws SQLException {
		if (parameter != null && parameter.size() > 0) {
			String json = parameter.toJSONString();
			ps.setString(i, json);
		} else {
			ps.setNull(i, Types.VARCHAR);
		}
	}

	@Override
	public JSONObject getResult(ResultSet rs, String columnName) throws SQLException {
		String columnValue = rs.getString(columnName);
		if (columnValue != null) {
			return JSON.parseObject(columnValue);
		} else {
			return new JSONObject();
		}
	}

	@Override
	public JSONObject getResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex);
		if (columnValue != null) {
			return JSON.parseObject(columnValue);
		} else {
			return  new JSONObject();
		}
	}

	@Override
	public JSONObject getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String columnValue = cs.getString(columnIndex);
		if (columnValue != null) {
			return JSON.parseObject(columnValue);
		} else {
			return  new JSONObject();
		}
	}

}
