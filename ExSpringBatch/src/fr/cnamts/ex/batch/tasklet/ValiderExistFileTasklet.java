package fr.cnamts.ex.batch.tasklet;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


public class ValiderExistFileTasklet implements Tasklet, StepExecutionListener{

	private transient StepExecution stepExecution;


	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValiderExistFileTasklet.class);

	
	private String repertoireSortie;
	

	public void setRepertoireSortie(String repertoireSortie) {
		this.repertoireSortie = repertoireSortie;
	}


	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

		File sortieDirectory = new File(repertoireSortie);
		if (sortieDirectory.exists() && sortieDirectory.isDirectory()) {
			LOGGER.info("Le repertoire de sortie exist ...\n"); 
			 if(sortieDirectory.listFiles().length >= 1 && sortieDirectory.listFiles()[0].getName().endsWith("csv")) {
	            	this.stepExecution.getJobExecution().getExecutionContext().put("FICHIER_SORTIE",sortieDirectory.listFiles()[0].getName());
	            	  LOGGER.info("the result file exists and it is csv file : "+this.stepExecution.getJobExecution().getExecutionContext()
	            			  .get("FICHIER_SORTIE"));
	            	  
	            }else {
	            	LOGGER.info("the file is not a csv file ");
	            	throw new Exception("length does not equal 1 or the result file not csv");
	            }
        }
        else {
        	LOGGER.info("Le repertoire de sortie n'exist pas ..\n");
        }
		return RepeatStatus.FINISHED;
	}


	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		// TODO Auto-generated method stub
		return ExitStatus.COMPLETED;
	}


	@Override
	public void beforeStep(StepExecution pStepExecution) {
		this.stepExecution= pStepExecution;
				
	}

}
