package fr.cnamts.ex.batch.item.tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FieldSetFactory;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.util.StringUtils;


public class PersonneLineTokenizer implements LineTokenizer{    /** Factory d'un fieldset. */
	private final transient FieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();
	/** logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonneLineTokenizer.class);


	/** delimiter */
	private transient String delimiter;

	private transient String[] names = new String[0];


	public void setNames(final String[] pNames) {
		if (null == pNames) {
			names = new String[0];
		} else {
			names = Arrays.asList(pNames).toArray(new String[pNames.length]);
		}
	}

	/**
	 * Setter Names
	 *
	 * @return the names
	 */
	public String[] getNames() {
		return names.clone();
	}

	// Nous retournons null pour faciliter les tests futurs.
	@SuppressWarnings("PMD.NullAssignment")
	@Override
	public FieldSet tokenize(final String pLine) {
		FieldSet resultat;
		if (null == pLine) {
			LOGGER.info("line null");
			resultat = null;
		} else {
			if (pLine.isEmpty()) {
				LOGGER.info("line Vide");
				resultat = null;
			} else {
				// Compter le nombre des occurences de d�limiter et le mettre en
				// négatif pour d�couper la ligne de donn�es en de N valeur pour
				// compter aussi les valeur vide
				final List<String> tokens = new ArrayList<String>(Arrays.asList(pLine.split(delimiter, -StringUtils
						.countOccurrencesOf(pLine, delimiter))));

				final String[] values = tokens.toArray(new String[tokens.size()]);

				if (names.length == 0) {
					resultat = fieldSetFactory.create(values);
				} else {
					if (values.length == names.length) {
						resultat = fieldSetFactory.create(values, names);
					} else {
						return null;
					}
				}
			}
		}
		return resultat;
	}


	public void setDelimiter(final String pDelimiter) {
		delimiter = pDelimiter;
	}



	public String getDelimiter() {
		return delimiter;
	}}
