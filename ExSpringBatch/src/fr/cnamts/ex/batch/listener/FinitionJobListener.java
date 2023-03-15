package fr.cnamts.ex.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;


public class FinitionJobListener implements JobExecutionListener {
	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(FinitionJobListener.class);

	@Override
	public void afterJob(JobExecution pJobExecution) {
		/*	if (BatchStatus.FAILED.equals(pJobExecution.getStatus())
		|| BatchStatus.STOPPED.equals(pJobExecution.getStatus())) {

	final String msgErreurBloq = (String) pJobExecution.getExecutionContext().get("ERREUR_BLOQUANTE");
	// Opération effectuée par le script de lancement du batch, a
	// supprimé.

	if (null != msgErreurBloq) {

		LOGGER.error(msgErreurBloq);
	}
}*/

	}

	@Override
	public void beforeJob(JobExecution pJobExecution) {


	}

}
