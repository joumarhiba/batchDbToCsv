package fr.cnamts.ex.batch.item.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import fr.cnamts.ex.batch.bo.EmployeeDto;
import fr.cnamts.ex.batch.tasklet.ValiderExistDbTasklet;

public class ImportEmployeeWriter  implements ItemWriter<EmployeeDto> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImportEmployeeWriter.class);


	@Override
	public void write(List<? extends EmployeeDto> employeeDtos) throws Exception {
		
		MapSqlParameterSource source = new MapSqlParameterSource();

//		for (EmployeeDto employeeDto : employeeDtos) {
//			
//			source.addValue("name", employeeDto.getName()); 
//			source.addValue("phone", employeeDto.getPhone());
//			source.addValue("email", employeeDto.getEmail());
//			source.addValue("country", employeeDto.getCountry());
//			source.addValue("createdAt", employeeDto.getCreatedAt());
//			
//			LOGGER.info(employeeDto.toString());
//
//		}
		
	}

}
