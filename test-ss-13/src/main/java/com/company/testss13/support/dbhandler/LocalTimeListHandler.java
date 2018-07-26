package com.company.testss13.support.dbhandler;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LocalTimeListHandler implements TypeHandler<List<LocalTime>> {

	@Override
	public void setParameter(PreparedStatement ps, int i, List<LocalTime> parameter, JdbcType jdbcType) throws SQLException {
		if (parameter != null && parameter.size() > 0) {
			String json = JSON.toJSONString(parameter);
			ps.setString(i, json);
		} else {
			ps.setNull(i, Types.VARCHAR);
		}
	}

	@Override
	public List<LocalTime> getResult(ResultSet rs, String columnName) throws SQLException {
		String columnValue = rs.getString(columnName);
		if (columnValue != null) {
			return JSON.parseArray(columnValue, LocalTime.class);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<LocalTime> getResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex);
		if (columnValue != null) {
			return JSON.parseArray(columnValue, LocalTime.class);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<LocalTime> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String columnValue = cs.getString(columnIndex);
		if (columnValue != null) {
			return JSON.parseArray(columnValue, LocalTime.class);
		} else {
			return new ArrayList<>();
		}
	}

}
