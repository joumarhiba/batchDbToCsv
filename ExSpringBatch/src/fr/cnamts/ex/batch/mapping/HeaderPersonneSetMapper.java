package fr.cnamts.ex.batch.mapping;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.file.LineCallbackHandler;

public class HeaderPersonneSetMapper implements StepExecutionListener, LineCallbackHandler {

	/** Ent�te mod�le d'un fichier CSV Adhesion. */
	private static final String[] ENTETE = new String[] {
			"NOM",
			"PRENOM",
			"AGE"
	};

	private transient String fileName;

	/** Step execution. */
	private transient StepExecution stepExecution;

	@Override
	public void handleLine(final String pLine) {
		if (!verifierEntete(pLine)) {
			declencherErreurBloquante(fileName);
		}

	}

	@Override
	public ExitStatus afterStep(final StepExecution pStepExecution) {
		return pStepExecution.getExitStatus();
	}

	@Override
	public void beforeStep(final StepExecution pStepExecution) {
		stepExecution = pStepExecution;

	}

	public void declencherErreurBloquante(final String pFileName) {
		stepExecution.getJobExecution().getExecutionContext().putString("ERREUR_BLOQUANTE",
				"Format fichier d'entete non conforme" + pFileName);
		stepExecution.getExecutionContext().put("ERREUR_BLOQUANTE", Boolean.TRUE);
		stepExecution.setTerminateOnly();
	}

	/**
	 * Cette methode permet de verifier l'entete d'un fichier CSV.
	 *
	 * @param pLine
	 *            , entete lus.
	 * @return {@code true} si l'entete est valide, {@code false} sinon.
	 */
	public boolean verifierEntete(final String pLine) {
		//		TODO On teste si le header obtenu et celui que l'on attend sont
		// exactement les memes (le deepEquals est necessaire pour comparer
		// un tableau d'objets).
		//		java.util.Arrays.deepEquals(ENTETE, pLine.split(";"))

		return true;
	}
}
