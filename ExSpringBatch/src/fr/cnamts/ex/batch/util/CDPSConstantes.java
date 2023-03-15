package fr.cnamts.ex.batch.util;

import org.springframework.batch.core.ExitStatus;

/**
 * Classe contenant toutes les constantes des batch CDPS_A
 *
 * @author NORSYS
 */
public final class CDPSConstantes {


	 /**
     * Clé de récupération de la clé de la liste des fichiers en entrée valides.
     */
    public static final String MAP_FICHIERS_ENTREE_VALIDE = "mapFichiersEntreeValide";

    /**
     * Clé de récupération de la clé de la liste des fichiers en entrée non valides.
     */
    public static final String MAP_FICHIERS_ENTREE_IGNORE = "mapFichiersEntreeIgnore";

    /** Code retour avec warning. */
    public static final ExitStatus EXIT_STATUT_ERREUR = new ExitStatus(
    "ERREUR_STATUT");
    /** Code retour avec warning. */
	public static final ExitStatus EXIT_STATUT_BLOQUANTE = new ExitStatus(
    "STATUT_BLOQUANTE");
    /** Nom logger rapport du traitement de cdps Global */
	public static final String LOGGER_RAPPORT_CDPS_GLOBAL = "cdpsRapportGlobal";
	/** Nom logger rapport du traitement de cdps Global */
	public static final String LOGGER_RAPPORT_CDPS_DETAILL = "cdpsRapport";
	 /** yyyyMMdd_HHmm */
    public static final String AFF_YYYYMMDD_HHMI = "yyyyMMdd_HHmm";
    /** TYPE_PAIEMENT.*/
	public static final String TYPE_PAIEMENT = "TYPE_PAIEMENT";
	/** CODE_REGIME.*/
	public static final String CODE_REGIME = "CODE_REGIME";
	/** CODE_CAISSE*/
	public static final String CODE_CAISSE = "CODE_CAISSE";
	/** CODE_CENTRE */
	public static final String CODE_CENTRE = "CODE_CENTRE";
	/** ANNEE.*/
	public static final String ANNEE = "ANNEE";
	/** TRIMESTRE.*/
	public static final String TRIMESTRE = "TRIMESTRE";
	/** DATE_RFERENCE.*/
	public static final String DATE_RFERENCE = "DATE_RFERENCE";
	/**DATE_DEBUT .*/
	public static final String DATE_DEBUT = "DATE_DEBUT";
	/**DATE_FIN .*/
	public static final String DATE_FIN = "DATE_FIN";
	/** REGIME_ORGANISMES*/
	public static final String REGIME_ORGANISMES = "REGIME_ORGANISMES";
	/**TRUE */
	public static final String TRUE = "true";

	/***************Nombres**************************/
	/** NUMBER_30.*/
	public static final int NUMBER_30 = 30;
	/** NUMBER_40.*/
	public static final int NUMBER_40 = 40;
	/** NUMBER_60.*/
	public static final int NUMBER_60 = 60;
	/** NUMBER_100.*/
	public static final int NUMBER_100 = 100;
	/** NUMBER_8.*/
	public static final int NUMBER_8 = 8;
	/** NUMBER_35.*/
	public static final int NUMBER_35 = 35;
	/** NUMBER_50.*/
	public static final int NUMBER_50 = 50;
	/** NUMBER_20.*/
	public static final int NUMBER_20 = 20;
	/** NUMBER_15.*/
	public static final int NUMBER_15 = 15;

	/*****************************************************/


	/**
     * Constructeur privé.
     */
    private CDPSConstantes() {
        // Classe utilitaire, ne pas instancier.
    }
}
