package fr.cnamts.ex.batch.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.dozer.DozerBeanMapper;

import fr.cnamts.ex.batch.bo.Employee;
import fr.cnamts.ex.batch.bo.EmployeeDto;

public class PersonneAdapter {
	
	private static final Locale LOCALE_FRANCE = Locale.FRANCE;
	private  DozerBeanMapper dozerMapper;
	
	public  EmployeeDto fromPersonneToPersonneDetailleBO(Employee employee){
		EmployeeDto employeeDto = new EmployeeDto();
		dozerMapper.map(employee, employeeDto);
		
		
		return employeeDto;
	}

	public void setDozerMapper(DozerBeanMapper dozerMapper) {
		this.dozerMapper = dozerMapper;
	}
	
	public  String formatteDate(final Date pDate, final String pFormat) {
		  String retour;
		  if (pDate == null) {
		   retour = "";
		  } else {
		   final SimpleDateFormat formatter = new SimpleDateFormat(pFormat,
		     LOCALE_FRANCE);
		   retour = formatter.format(pDate);
		  }
		  return retour;
		 }
	
	

}
