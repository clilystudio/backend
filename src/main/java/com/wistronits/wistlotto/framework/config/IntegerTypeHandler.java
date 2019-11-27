package com.wistronits.wistlotto.framework.config;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeException;

/**
 * Mybatis生成代码时，INTEGER类型转换为BigDecimal
 * 
 * @author 盛广立 2019年1月16日
 */
@MappedJdbcTypes(JdbcType.INTEGER)
public class IntegerTypeHandler extends BaseTypeHandler<BigDecimal> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BigDecimal parameter, JdbcType jdbcType)
			throws SQLException {
		try {
			ps.setInt(i, parameter.intValueExact());
		} catch (ArithmeticException e) {
			throw new TypeException("Integer requires that BigDecimal must be integer value.", e);
		}
	}

	@Override
	public BigDecimal getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getBigDecimal(columnName);
	}

	@Override
	public BigDecimal getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getBigDecimal(columnIndex);
	}

	@Override
	public BigDecimal getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getBigDecimal(columnIndex);
	}
}
