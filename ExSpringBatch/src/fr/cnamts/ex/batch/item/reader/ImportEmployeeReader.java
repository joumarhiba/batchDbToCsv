package fr.cnamts.ex.batch.item.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import fr.cnamts.ex.batch.bo.Employee;
import fr.cnamts.ex.batch.bo.EmployeeDto;

public class ImportEmployeeReader  implements ItemStreamReader<Employee> {

	private transient ItemReader<Employee> readerJdbc;
	

	public void setReaderJdbc(ItemReader<Employee> readerJdbc) {
		this.readerJdbc = readerJdbc;
	}

	@Override
	public void close() throws ItemStreamException {
		((ItemStream) this.readerJdbc).close();
	}

	@Override
	public void open(final ExecutionContext pExecutionContext)
			throws ItemStreamException {
		((ItemStream) this.readerJdbc).open(pExecutionContext);
	}

	@Override
	public void update(final ExecutionContext pExecutionContext) throws ItemStreamException {
		((ItemStream) this.readerJdbc).update(pExecutionContext);

	}

	@Override
	public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		final Employee resultat = this.readerJdbc.read();

		return resultat;
	}

}
