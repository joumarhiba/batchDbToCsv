package fr.cnamts.ex.batch.util;

import fr.cnamts.jk.soclebatch.core.configuration.CommonsConfiguration;

/**
 * Classe de constantes pour la gestion des messages des traitements.
 *
 * @author NORSYS
 */
public final class MessagesConstantes {
	/** R�f�rence sur la configuration g�n�rale. */
	public static final CommonsConfiguration CONFIG_APPLICATION = CommonsConfiguration
			.getInstance();

	/** CE_ENVIRONNEMENT_EXEC_01. */
	public static final String CE_ENVIRONNEMENT_EXEC_01 = "L'environnement d'ex�cution n'est pas op�rationnel"
			+ ", probl�me au niveau de : ";
	/** CE_ENVIRONNEMENT_EXEC_02. */
	public static final String CE_ENVIRONNEMENT_EXEC_02 = "L'environnement d'ex�cution n'est pas op�rationnel.";
	/** Base de donn�es. */
	public static final String DISPONIBILITE_BPRG_O = "Disponibilit� du r�f�rentiel CDPS_O.";
	/** Le r�pertoire quarantaine. */
	public static final String REPERTOIRE_TRAVAIL = "Disponibilit� du r�pertoire travail.";
	/** Le r�pertoire sortie. */
	public static final String REPERTOIRE_SORTIE = "Disponibilit� du r�pertoire sortie.";
	/** r�pertoire de entr�e. */
	public static final String REPERTOIRE_ENTREE = "Disponibilit� du r�pertoire entr�e.";
	/** r�pertoire de archive. */
	public static final String REPERTOIRE_ARCHIVE = "Disponibilit� du r�pertoire archive.";

	/** CE_PRESENCE_FICHIER. */
	public static final String CE_PRESENCE_FICHIER = "Les fichiers ne sont pas pr�sents "
			+ "dans le r�pertoire d'entr�e.";
	/** FORMAT_DATE_REFERENCE_YYYYMMDD */
	public static final String FORMAT_DATE_REFERENCE_YYYYMMDD = "La date de d�claration doit avoir "
			+ "le format suivant AAAAMMJJ.";
	/** CE_PRESENCE_FICHIER_INVALIDE */
	public static final String CE_PRESENCE_FICHIER_INVALIDE = "Les fichiers pr�sents "
			+ "dans le r�pertoire d'entr�e sont invalides";

	/** CE_ENTETE_FICHIER_CSV. */
	public static final String CE_ENTETE_FICHIER_CSV = "L'Ent�te du fichier n'est pas conforme";

	/** CE_NOMBRE_ELEMENT_LIGNE_FICHIER */
	public static final String CE_NOMBRE_ELEMENT_LIGNE_FICHIER = "Le nombre d'�lements non conforme";

	/** DATE_NAISSANCE_OBLIGATOIRE. */
	public static final String DATE_NAISSANCE_OBLIGATOIRE = "La date de naissance  doit �tre renseign�.";

	/** CABINET_REFERENCE_OBLIGATOIRE. */
	public static final String CABINET_REFERENCE_OBLIGATOIRE = "Le cabinet de r�f�rence doit �tre renseign�.";

	/** DATE_DECLARATION_MT_OBLIGATOIRE. */
	public static final String DATE_DECLARATION_MT_OBLIGATOIRE = "La date de declaration doit �tre renseign�.";

	/** TYPE_ACTES_OBLIGATOIRE. */
	public static final String TYPE_ACTES_OBLIGATOIRE = "Le type d'actes doit �tre renseign�.";

	/** NOMBRE_TOTAL_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_TOTAL_ACTES_OBLIGATOIRE = "Le nombre total d'actes doit �tre renseign�.";

	/** NOMBRE_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_ACTES_OBLIGATOIRE = "Le nombre d'actes doit �tre renseign�.";

	/** NOMBRE_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_TOTAL_PATIENTS_OBLIGATOIRE = "Le nombre total de patients " +
			"doit �tre renseign�.";

	/** NOM_PATIENT_OBLIGATOIRE. */
	public static final String NOM_PATIENT_OBLIGATOIRE = "Le nom de patient doit �tre renseign�.";
	/** PRENOM_PATIENT_OBLIGATOIRE. */
	public static final String PRENOM_PATIENT_OBLIGATOIRE = "Le prenom de patient doit �tre renseign�.";

	/** NUM_AM_OBLIGATOIRE. */
	public static final String NUM_AM_OBLIGATOIRE = "Le numero AM doit �tre renseign�.";

	/** CODE_REGIME_OBLIGATOIRE. */
	public static final String CODE_REGIME_OBLIGATOIRE = "Le code r�gime doit �tre renseign�.";

	/** NIR_PATIENT_OBLIGATOIRE */
	public static final String NIR_PATIENT_OBLIGATOIRE = "Le Nir de patient doit �tre renseign�.";
	/** DISPONIBILITE_CDPS_O */
	public static final String DISPONIBILITE_CDPS_O = "Disponibilit� du r�f�rentiel CDPS_O.";

	/** CE_VERIF_FINDEFICHIER*/
	public static final String CE_VERIF_FINDEFICHIER = " : Le fichier en entr�e est incomplet" +
			" (Absence de la derni�re ligne 'FinDeFichier')";

/********************/


	/** DATE_NAISSANCE_OBLIGATOIRE. */
	public static final String DATE_NAISSANCE_NON_VALIDE = "La date de naissance n'est pas valide.";

	/** CABINET_REFERENCE_OBLIGATOIRE. */
	public static final String CABINET_REFERENCE_NON_VALIDE = "Le cabinet de r�f�rence n'est pas valide.";

	/** DATE_DECLARATION_MT_OBLIGATOIRE. */
	public static final String DATE_DECLARATION_NON_VALIDE = "La date de declaration n'est pas valide.";

	/** NOMBRE_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_TOTAL_PATIENTS_NON_VALIDE= "Le nombre total de patients " +
			"n'est pas valide.";

	/** NOM_PATIENT_OBLIGATOIRE. */
	public static final String NOM_PATIENT_NON_VALIDE = "Le nom de patient n'est pas valide.";
	/** PRENOM_PATIENT_OBLIGATOIRE. */
	public static final String PRENOM_PATIENT_NON_VALIDE = "Le prenom de patient n'est pas valide.";

	/** NUM_AM_OBLIGATOIRE. */
	public static final String NUM_AM_NON_VALIDE = "Le numero AM n'est pas valide.";

	/** CODE_REGIME_OBLIGATOIRE. */
	public static final String CODE_REGIME_NON_VALIDE = "Le code r�gime n'est pas valide.";

	/** NIR_PATIENT_OBLIGATOIRE */
	public static final String NIR_PATIENT_NON_VALIDE = "Le Nir de patient n'est pas valide.";

	/** TYPE_ACTES_OBLIGATOIRE. */
	public static final String TYPE_ACTES_NON_VALIDE = "Le type d'actes n'est pas valide.";

	/** NOMBRE_TOTAL_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_TOTAL_ACTES_NON_VALIDE= "Le nombre total d'actes n'est pas valide.";

	/** NOMBRE_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_ACTES_NON_VALIDE = "Le nombre d'actes n'est pas valide.";

	/**
	 * Constructeur priv�.
	 */
	private MessagesConstantes() {
		// Classe utilitaire, ne pas instancier.
	}
}
