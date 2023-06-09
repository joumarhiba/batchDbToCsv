package fr.cnamts.ex.batch.tasklet;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ValiderExistDbTasklet  implements Tasklet, StepExecutionListener{
	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(ValiderExistDbTasklet.class);

	private String driver;
	private String url;
	private String username;
	private String password;
	private String dbName;
	private String tableName;
	//	TODO -verifier exsitences de repertoires
	//	UFichier.isNonexiste(this.repertoireEntree)
	//	-verifier existence et le format de fichier entree

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	/** Step execution du batch. */
	private transient StepExecution stepExecution;

	/** Job execution du batch. */
	private transient JobExecution jobExecution;


	/**
	 * permet d'arreter le batch
	 */
	private void arreterBatch() {
		this.stepExecution.getExecutionContext().put("ERREUR_BLOQUANTE", Boolean.TRUE);
		this.stepExecution.setTerminateOnly();
	}



	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		// TODO Auto-generated method stub
		this.stepExecution.getJobExecution().getExecutionContext().put("ERREUR_BLOQUANTE", Boolean.TRUE);
		LOGGER.info("this running ................");
		Class.forName(driver);
		
		Connection connection = DriverManager.getConnection(url, username, password);

		DatabaseMetaData dbmd = connection.getMetaData();
		ResultSet resultSet = dbmd.getCatalogs();
		boolean databaseExists = false;

		while (resultSet.next()) {
		    if (resultSet.getString(1).equalsIgnoreCase(dbName)) {
		        databaseExists = true;
		        break;
		    }
		}
		
		if (connection.isValid(5)) {
		    System.out.println("Connection is valid "+tableName);
		} else {
		    System.out.println("Connection is invalid");
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, phone, email, country FROM "+tableName);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
		  System.out.println("Table exists and has data");
		} else {
		  System.out.println("Table exists but has no data");
		}
		return RepeatStatus.FINISHED;
	}





	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public void beforeStep(StepExecution pStepExecution) {
		
		this.stepExecution= pStepExecution;
		
	}





}
