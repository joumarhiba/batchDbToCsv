package fr.cnamts.ex.batch.item.processor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import fr.cnamts.ex.batch.bo.Employee;
import fr.cnamts.ex.batch.bo.EmployeeDto;
import fr.cnamts.ex.batch.tasklet.ValiderExistDbTasklet;

public class ImportEmployeeProcessor implements ItemProcessor<Employee, EmployeeDto> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportEmployeeProcessor.class);

	@Autowired
	private DozerBeanMapper dozerBeanMapper;
	
	@Override
	public EmployeeDto process(Employee employee) throws Exception {
			
	EmployeeDto employeeDto = dozerBeanMapper.map(employee,EmployeeDto.class);
			

	// Format the current date as a string
	Date currentDate = new Date();
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	String dateString = formatter1.format(currentDate);

			employeeDto.setCreatedAt(dateString);


		
		return employeeDto;
	}

}
