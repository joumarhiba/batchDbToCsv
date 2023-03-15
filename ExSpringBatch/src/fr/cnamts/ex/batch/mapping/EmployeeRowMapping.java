package fr.cnamts.ex.batch.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.cnamts.ex.batch.bo.Employee;
import fr.cnamts.ex.batch.bo.EmployeeDto;


public class EmployeeRowMapping implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int nbRow) throws SQLException {

		Employee employee = new Employee();
		employee.setName(rs.getString("name"));  
		employee.setPhone(rs.getString("phone"));
		employee.setEmail(rs.getString("email"));
		employee.setCountry(rs.getString("country"));


		return employee;
	}

}
