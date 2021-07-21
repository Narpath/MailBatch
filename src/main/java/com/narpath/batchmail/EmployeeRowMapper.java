package com.narpath.batchmail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setId(rs.getInt("id"));
		emp.setName(rs.getString("name"));
		emp.setDesignation(rs.getString("designation"));
		emp.setCity(rs.getString("city"));
		emp.setCompany(rs.getString("companyname"));
		emp.setEmail(rs.getString("email"));
		return emp;
	}
}