package fr.cnamts.ex.batch.mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import fr.cnamts.ex.batch.bo.Employee;

public class PersonneFieldSetMapper implements FieldSetMapper<Employee> {
	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonneFieldSetMapper.class);



	@Override
	public Employee mapFieldSet(final FieldSet pFieldset) throws BindException {
		if (pFieldset == null) {


			return null;
			// Inutile de continuer si l'objet est null
		}
		//		TODO mapping from pFieldset to Objet Personne
		//		pFieldset.readString("TODO nom champ");
		final Employee personneBO = new Employee();

		return personneBO;
	}


}
