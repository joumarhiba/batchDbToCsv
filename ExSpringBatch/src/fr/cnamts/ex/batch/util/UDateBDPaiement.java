package fr.cnamts.ex.batch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import fr.cnamts.jk.soclebatch.core.constante.ConstantesCnam;

/**
 * Classe utilitaire
 * 
 * @author NORSYS
 */
public final class UDateBDPaiement {

	/** format JJMMAAAA */
	public static final String YYYYMMDD = "yyyyMMdd";
	/** Locale France. */
	public static final Locale LOCALE_FRANCE = Locale.FRANCE;

	 /**
     * Formatter une date en format pFormat passé en paramétre.
     * 
     * @param pDate
     * @param pFormat
     * @return
     */
	public static String formatteDate(final Date pDate, final String pFormat) {
		String retour;
		if (pDate == null) {
			retour = "";
		} else {
			final SimpleDateFormat formatter = new SimpleDateFormat(pFormat,
					LOCALE_FRANCE);
			retour = formatter.format(pDate);
		}
		return retour;
	}

	/**
	 * Si la chaine contient « / » parser au format JJ/MM/AAAA Si la chaine ne
	 * contient pas « / » parser au format AAAAMMJJ
	 * 
	 * @param pChaineDate
	 * @return
	 */
	public static DateTime parseDate(final String pChaineDate) {
		try {
			final DateTimeFormatter sdf = getFormatter(pChaineDate);
			return sdf.parseDateTime(pChaineDate);
		} catch (final IllegalArgumentException exception) {
			return null;
		}

	}

	/**
	 * Transformer au format JJ/MM/AAAA
	 * 
	 * @param pDateTime
	 * @return
	 */
	public static String formatSlash(final DateTime pDateTime) {
		String retour;
		if (pDateTime == null) {
			retour = ConstantesCnam.EMPTY_STRING;
		} else {
			final DateTimeFormatter sdf = DateTimeFormat.forPattern(
					ConstantesCnam.AFF_DD_MM_YYYY).withLocale(LOCALE_FRANCE);
			retour = sdf.print(pDateTime);
		}
		return retour;

	}

	/**
	 * Transformer au format AAAAMMJJ.
	 * 
	 * @param pDateTime
	 * @return
	 */
	public static String formatAAAAMMJJ(final DateTime pDateTime) {
		String retour;
		if (pDateTime == null) {
			retour = ConstantesCnam.EMPTY_STRING;
		} else {
			final DateTimeFormatter sdf = DateTimeFormat.forPattern(
					ConstantesCnam.AFF_DDMMYYYY).withLocale(LOCALE_FRANCE);
			retour = sdf.print(pDateTime);
		}
		return retour;
	}

	/**
	 * Conversion trimistre en Date Exemple trimestreToDate(2015,3) « 01/07/2015
	 * » trimestreToDate(2015,4) « 01/10/2015 »
	 * 
	 * @param pAnnee
	 * @param pNumTrimestre
	 * @return
	 */
	public static DateTime trimestreToDate(final int pAnnee,
			final int pNumTrimestre) {
		DateTime dateTime = null;
		switch (pNumTrimestre) {
		case 1:
			dateTime = new DateTime(pAnnee, 1, 1, 0, 0, 0, 1, DateTimeZone.UTC);
			break;
		case 2:
			dateTime = new DateTime(pAnnee, 4, 1, 0, 0, 0, 2, DateTimeZone.UTC);
			break;
		case 3:
			dateTime = new DateTime(pAnnee, 7, 1, 0, 0, 0, 3, DateTimeZone.UTC);
			break;
		case 4:
			dateTime = new DateTime(pAnnee, 10, 10, 0, 0, 0, 4,
					DateTimeZone.UTC);
			break;
		default:
			break;
		}
		
		
		return dateTime;
	}

	/**
	 * Prendre une date et retourner un nombre de trimestre. Si la date passé ne
	 * correspond pas au début trimestre, retourner -1 Exemple dateToTrimestre(
	 * dateToTrimestre("01/07/2015") ) 3. dateToTrimestre(
	 * parseDate("01/08/2015")) -1
	 * 
	 * @param pDateTime
	 * @return
	 */
	public static int dateToTrimestre(final DateTime pDateTime) {
		final int trimestres[] = { 1, 2, 3, 4 };
		int trimestre = -1;
		if (null == pDateTime) {
			trimestre = -1;
		} else {
			trimestre = (pDateTime.getMonthOfYear() / 3) + 1;
		}

		return (ArrayUtils.contains(trimestres, trimestre) ? trimestre : -1);
	}

	/**
	 * Envoyer la date de fin de trimestre ou null Exemple
	 * finTrimestre(parseDate ("01/07/2015")) 31/12/2015 finTrimestre(parseDate
	 * ("01/08/2015") ) null (paramètre invalide)
	 * 
	 * @param pDateDebutTrimestre
	 * @return
	 */
	public static DateTime finTrimestre(final DateTime pDateDebutTrimestre) {
		final int trim = dateToTrimestre(pDateDebutTrimestre);
		DateTime dateTime = new DateTime();
		if (trim == -1) {
			dateTime = null;
		} else {
			dateTime = new DateTime(pDateDebutTrimestre.getYear(),
					pDateDebutTrimestre.getMonthOfYear() + 2, 1, 0, 0, 0, trim);
			dateTime = new DateTime(pDateDebutTrimestre.getYear(),
					pDateDebutTrimestre.getMonthOfYear() + 2, dateTime
							.dayOfMonth().getMaximumValue(), 0, 0, 0, trim);
		}
		return dateTime;
	}

	
	/**
	 * Envoyer la date de debut de trimestre ou null Exemple
	 * @param pDateDebutTrimestre
	 * @return
	 */
	public static DateTime dateDebutTrimestre(final DateTime pDate) {
		DateTime debutTrimestre;
		if(null== pDate){
			debutTrimestre =null;
		}else{
			final float result= ((float)pDate.getMonthOfYear())/((float)3);
			if(result<=1){
				debutTrimestre=new DateTime(pDate.getYear(),
						1, 1, 0, 0, 0, 1);
			}else if(result<=2){
				debutTrimestre =new DateTime(pDate.getYear(),
						4, 1, 0, 0, 0, 2);
			}else if(result<=3){
				debutTrimestre=new DateTime(pDate.getYear(),
						7, 1, 0, 0, 0, 3);
			}else{
				debutTrimestre=new DateTime(pDate.getYear(),
						10, 1, 0, 0, 0, 4);
			}
		}
		return debutTrimestre;
		
	}
	
	/**
	 * Prendre une date et retourner un nombre de trimestre. 
	 * 
	 * @param pDateTime
	 * @return
	 */
	public static int dateToTrimestre(final String pDate) {
		return dateToTrimestre(dateDebutTrimestre(parseDate(pDate)));
	}

	
	
	/**
	 * Envoyer le trimestre précédent ou null Exemple finTrimestre(parseDate
	 * ("01/07/2015")) 31/12/2015 finTrimestre(parseDate ("01/08/2015") ) null
	 * (paramètre invalide)
	 * 
	 * @param pDateTrimestre
	 * @return
	 */
	public static DateTime trimestrePredecent(final DateTime pDateTrimestre) {
		final int trim = dateToTrimestre(pDateTrimestre);
		DateTime dateTime = null;
		if (trim == -1) {
			dateTime = null;
		} else if (trim == 1) {
			dateTime = new DateTime(pDateTrimestre.getYear() - 1, 10, 1, 0, 0,
					0, 4);
		} else {
			dateTime = new DateTime(pDateTrimestre.getYear(),
					pDateTrimestre.getMonthOfYear() - 3, 1, 0, 0, 0, trim - 1);
		}
		return dateTime;
	}

	/**
	 * Si la chaine contient « / » format= JJ/MM/AAAA Si la chaune ne contient
	 * pas « / » format= AAAAMMJJ
	 * 
	 * @param pChaineDate
	 * @return
	 */
	private static DateTimeFormatter getFormatter(final String pChaineDate) {
		DateTimeFormatter formatteur;
		if ((null != pChaineDate) && pChaineDate.contains("/")) {
			formatteur = DateTimeFormat.forPattern(
					ConstantesCnam.AFF_DD_MM_YYYY).withLocale(LOCALE_FRANCE);
		} else {
			formatteur = DateTimeFormat.forPattern(YYYYMMDD).withLocale(
					LOCALE_FRANCE);
		}
		return formatteur;
	}

	/**
	 * Valider une chaîne de caractères date.
	 * 
	 * @param pDate
	 *            , chaîne de caractères à valider.
	 * @param pFormatDate
	 *            , format de la date.
	 * @return le résultat de la validation.
	 */
	public static boolean isValidDate(final String pDate,
			final String pFormatDate) {
		try {
			final DateTimeFormatter sdf = DateTimeFormat
					.forPattern(pFormatDate).withLocale(LOCALE_FRANCE);
			getFormatter(pFormatDate);
			sdf.parseDateTime(pDate).toDate();
		} catch (final IllegalArgumentException exception) {
			return false;
		}
		return true;
	}
	/**
	 * Valider une chaîne de caractères date.
	 * 
	 * @param pDate
	 *            , chaîne de caractères à valider.
	 * @param pFormatDate
	 *            , format de la date.
	 * @return le résultat de la validation.
	 */
	public static String toFormatString(final String pDate,
			final String pFormatDate) {
		String formattedDate="";
	Date date = null;
	try {
		date = new SimpleDateFormat(pFormatDate,LOCALE_FRANCE).parse(pDate);
		 formattedDate = new SimpleDateFormat("dd/MM/yyyy",LOCALE_FRANCE).format(date);
		
	} catch (final ParseException pParseException) {
		 formattedDate="";
	}
	
	return formattedDate;
	}
	/**
	 * Private Constructor.
	 */
	private UDateBDPaiement() {
		// TODO Auto-generated constructor stub
	}

}
