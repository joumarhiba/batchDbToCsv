package fr.cnamts.ex.batch.util;

import fr.cnamts.jk.soclebatch.core.configuration.CommonsConfiguration;

/**
 * Classe de constantes pour la gestion des messages des traitements.
 *
 * @author NORSYS
 */
public final class MessagesConstantes {
	/** Référence sur la configuration générale. */
	public static final CommonsConfiguration CONFIG_APPLICATION = CommonsConfiguration
			.getInstance();

	/** CE_ENVIRONNEMENT_EXEC_01. */
	public static final String CE_ENVIRONNEMENT_EXEC_01 = "L'environnement d'exécution n'est pas opérationnel"
			+ ", problème au niveau de : ";
	/** CE_ENVIRONNEMENT_EXEC_02. */
	public static final String CE_ENVIRONNEMENT_EXEC_02 = "L'environnement d'exécution n'est pas opérationnel.";
	/** Base de données. */
	public static final String DISPONIBILITE_BPRG_O = "Disponibilité du référentiel CDPS_O.";
	/** Le répertoire quarantaine. */
	public static final String REPERTOIRE_TRAVAIL = "Disponibilité du répertoire travail.";
	/** Le répertoire sortie. */
	public static final String REPERTOIRE_SORTIE = "Disponibilité du répertoire sortie.";
	/** répertoire de entrée. */
	public static final String REPERTOIRE_ENTREE = "Disponibilité du répertoire entrée.";
	/** répertoire de archive. */
	public static final String REPERTOIRE_ARCHIVE = "Disponibilité du répertoire archive.";

	/** CE_PRESENCE_FICHIER. */
	public static final String CE_PRESENCE_FICHIER = "Les fichiers ne sont pas présents "
			+ "dans le répertoire d'entrée.";
	/** FORMAT_DATE_REFERENCE_YYYYMMDD */
	public static final String FORMAT_DATE_REFERENCE_YYYYMMDD = "La date de déclaration doit avoir "
			+ "le format suivant AAAAMMJJ.";
	/** CE_PRESENCE_FICHIER_INVALIDE */
	public static final String CE_PRESENCE_FICHIER_INVALIDE = "Les fichiers présents "
			+ "dans le répertoire d'entrée sont invalides";

	/** CE_ENTETE_FICHIER_CSV. */
	public static final String CE_ENTETE_FICHIER_CSV = "L'Entête du fichier n'est pas conforme";

	/** CE_NOMBRE_ELEMENT_LIGNE_FICHIER */
	public static final String CE_NOMBRE_ELEMENT_LIGNE_FICHIER = "Le nombre d'élements non conforme";

	/** DATE_NAISSANCE_OBLIGATOIRE. */
	public static final String DATE_NAISSANCE_OBLIGATOIRE = "La date de naissance  doit être renseigné.";

	/** CABINET_REFERENCE_OBLIGATOIRE. */
	public static final String CABINET_REFERENCE_OBLIGATOIRE = "Le cabinet de référence doit être renseigné.";

	/** DATE_DECLARATION_MT_OBLIGATOIRE. */
	public static final String DATE_DECLARATION_MT_OBLIGATOIRE = "La date de declaration doit être renseigné.";

	/** TYPE_ACTES_OBLIGATOIRE. */
	public static final String TYPE_ACTES_OBLIGATOIRE = "Le type d'actes doit être renseigné.";

	/** NOMBRE_TOTAL_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_TOTAL_ACTES_OBLIGATOIRE = "Le nombre total d'actes doit être renseigné.";

	/** NOMBRE_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_ACTES_OBLIGATOIRE = "Le nombre d'actes doit être renseigné.";

	/** NOMBRE_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_TOTAL_PATIENTS_OBLIGATOIRE = "Le nombre total de patients " +
			"doit être renseigné.";

	/** NOM_PATIENT_OBLIGATOIRE. */
	public static final String NOM_PATIENT_OBLIGATOIRE = "Le nom de patient doit être renseigné.";
	/** PRENOM_PATIENT_OBLIGATOIRE. */
	public static final String PRENOM_PATIENT_OBLIGATOIRE = "Le prenom de patient doit être renseigné.";

	/** NUM_AM_OBLIGATOIRE. */
	public static final String NUM_AM_OBLIGATOIRE = "Le numero AM doit être renseigné.";

	/** CODE_REGIME_OBLIGATOIRE. */
	public static final String CODE_REGIME_OBLIGATOIRE = "Le code régime doit être renseigné.";

	/** NIR_PATIENT_OBLIGATOIRE */
	public static final String NIR_PATIENT_OBLIGATOIRE = "Le Nir de patient doit être renseigné.";
	/** DISPONIBILITE_CDPS_O */
	public static final String DISPONIBILITE_CDPS_O = "Disponibilité du référentiel CDPS_O.";

	/** CE_VERIF_FINDEFICHIER*/
	public static final String CE_VERIF_FINDEFICHIER = " : Le fichier en entrée est incomplet" +
			" (Absence de la dernière ligne 'FinDeFichier')";

/********************/


	/** DATE_NAISSANCE_OBLIGATOIRE. */
	public static final String DATE_NAISSANCE_NON_VALIDE = "La date de naissance n'est pas valide.";

	/** CABINET_REFERENCE_OBLIGATOIRE. */
	public static final String CABINET_REFERENCE_NON_VALIDE = "Le cabinet de référence n'est pas valide.";

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
	public static final String CODE_REGIME_NON_VALIDE = "Le code régime n'est pas valide.";

	/** NIR_PATIENT_OBLIGATOIRE */
	public static final String NIR_PATIENT_NON_VALIDE = "Le Nir de patient n'est pas valide.";

	/** TYPE_ACTES_OBLIGATOIRE. */
	public static final String TYPE_ACTES_NON_VALIDE = "Le type d'actes n'est pas valide.";

	/** NOMBRE_TOTAL_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_TOTAL_ACTES_NON_VALIDE= "Le nombre total d'actes n'est pas valide.";

	/** NOMBRE_ACTES_OBLIGATOIRE. */
	public static final String NOMBRE_ACTES_NON_VALIDE = "Le nombre d'actes n'est pas valide.";

	/**
	 * Constructeur privé.
	 */
	private MessagesConstantes() {
		// Classe utilitaire, ne pas instancier.
	}
}
