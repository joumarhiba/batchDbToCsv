package fr.cnamts.ex.batch.item.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;

import fr.cnamts.ex.batch.bo.Employee;

public class ImportEmployeeDataReader extends FlatFileItemReader<Employee> {
	 
    private static final String NB_ENREG_LUS = "NB_ENREG_LUS";
	/** Nombre de lignes lues. */
    private transient int              nbLignesLues;
	
	
    @Override
    public void open(final ExecutionContext pExecutionContext) throws ItemStreamException {
        this.nbLignesLues = pExecutionContext.getInt(NB_ENREG_LUS, 0);
        super.open(pExecutionContext);
    }
    @Override
    public void update(final ExecutionContext pExecutionContext) throws ItemStreamException {
        pExecutionContext.putInt(NB_ENREG_LUS, nbLignesLues);
        super.update(pExecutionContext);
    }
}
