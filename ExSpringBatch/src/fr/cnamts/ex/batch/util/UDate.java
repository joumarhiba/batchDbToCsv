package fr.cnamts.ex.batch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import fr.cnamts.jk.soclebatch.core.constante.ConstantesCnam;

/**
 * Classe utilitaire
 * 
 * @author NORSYS
 */
public final class UDate {

    /** format de date dd/mm/yyyy */
    public static final String DD_MM_YYYY = ConstantesCnam.AFF_DD_MM_YYYY;
    /** format de date DD_MM_YYYY_HH_MM_SS */
    public static final String DD_MM_YYYY_HH_MM_SS = DD_MM_YYYY + " "
            + ConstantesCnam.AFF_HH_MM_SS;

    /** Formattage */
    public static final String DD_MM_YYYYTHH_MM_SS = ConstantesCnam.AFF_YYYY_MM_DD
            + "'T'" + ConstantesCnam.AFF_HH_MM_SS;

    /** LOGGER */
    private static final Logger LOGGER = Logger.getLogger(UDate.class);

    /**
     * Convertir date to XMLGregorian
     * 
     * @param pXmlGregorianCalendar
     * @return
     */
    public static XMLGregorianCalendar convertirDateToXMLGregorianCalendar(
            final Date pDate, final String pFormat) {
        XMLGregorianCalendar dateXML = null; // NOPMD
        if (null != pDate) {

            try {
                final GregorianCalendar calendar = new GregorianCalendar();
                final SimpleDateFormat sdf = new SimpleDateFormat(pFormat, Locale.FRANCE);
                final String dateString = sdf.format(pDate);
                final Date date = sdf.parse(dateString);
                calendar.setTime(date);
                final DatatypeFactory dtf = DatatypeFactory.newInstance();
                final XMLGregorianCalendar xgc = dtf.newXMLGregorianCalendar();
                xgc.setYear(calendar.get(Calendar.YEAR));
                xgc.setMonth(calendar.get(Calendar.MONTH) + 1);
                xgc.setDay(calendar.get(Calendar.DAY_OF_MONTH));
                xgc.setHour(calendar.get(Calendar.HOUR_OF_DAY));
                xgc.setMinute(calendar.get(Calendar.MINUTE));
                xgc.setSecond(calendar.get(Calendar.SECOND));
                dateXML = xgc;
            } catch (final DatatypeConfigurationException pException) {
                LOGGER.info(pException.getMessage());
            } catch (final ParseException pException) {
                LOGGER.info(pException.getMessage());
            }
        }
        return dateXML;
    }

    /**
     * convertir XMLGregorian to date
     * 
     * @param pXmlGregorianCalendar
     * @return
     */
    public static Date convertirXMLGregorianCalendarToDate(
            final XMLGregorianCalendar pXmlGregorianCalendar) {
        final Calendar calendar = new GregorianCalendar();

        calendar.set(pXmlGregorianCalendar.getYear(), pXmlGregorianCalendar
                .getMonth() - 1, pXmlGregorianCalendar.getDay());
        return calendar.getTime();
    }

    public static String formatteDate(final Date pDateAnniversaire,
            final String pFormat) {
        // dd/MM/yyyy HH:mm:ss
        String retour;
        if (pDateAnniversaire == null) {
            retour = "";
        } else {
            final SimpleDateFormat formatter = new SimpleDateFormat(pFormat,
                    Locale.FRANCE);
            retour = formatter.format(pDateAnniversaire);
        }
        return retour;
    }

    public static Date formatteDate(final String pDate) {
        return toCalendar(pDate, ConstantesCnam.AFF_YYYYMMDD).getTime();
    }

    /**
     * formater une date à une date calendare suivant le format
     * 
     * @param pDate
     * @param pFormat
     * @return
     */
    public static Date formatteDate(final String pDate, final String pFormat) {
        return toCalendar(pDate, pFormat).getTime();
    }

    /**
     * Conversion de la chaine au format AAAA/MM/JJ vers une date.
     * 
     * @param pDate
     * @return
     * @throws ParseException
     */
    public static String formatteDateCnam(final Date pDate) {
        String dateStringRetour;
        if (pDate == null) {
            dateStringRetour = "";
        } else {
            final SimpleDateFormat formatter = new SimpleDateFormat(
                    ConstantesCnam.AFF_YYYYMMDD, Locale.FRANCE);
            dateStringRetour = formatter.format(pDate);
        }
        return dateStringRetour;
    }

    /**
     * Formattage d'une date @Date to String Format CNAM yyyyMMjj
     * 
     * @param pDate
     * @return
     */
    public static String formatteDateXSD(final Date pDate) {
        String dateStringRetour;
        if (pDate == null) {
            dateStringRetour = "";
        } else {
            final SimpleDateFormat formatter = new SimpleDateFormat(
                    DD_MM_YYYYTHH_MM_SS, Locale.FRANCE);
            dateStringRetour = formatter.format(pDate);
        }
        return dateStringRetour;
    }

    public static Date formatteDateYDMmmhh(final String pDate) {
        if (null != pDate) {
            return toCalendar(pDate, "yyyy-MM-dd HH:mm:ss").getTime();
        } else {
            return null;
        }
    }

    /**
     * Méthode utilitaire permettatn d'obtenir un objet {@link Date} à partir
     * d'un triplet de nombre indiquant le jour, le mois et l'année de la date.
     * 
     * @param pJour
     *            , le jour de la date.
     * @param pMois
     *            , le mois de la date (ex Calendar.JANUARY ...
     *            Calendar.DECEMBER).
     * @param pAnnee
     *            , l'année de la date.
     * @return la date correspondante au triplet en entrée.
     */
    public static Date getDate(final int pJour, final int pMois,
            final int pAnnee) {
        final Calendar cal = Calendar.getInstance(Locale.FRENCH);
        cal.set(pAnnee, pMois, pJour, 0, 0, 0);
        return cal.getTime();
    }

    public static java.sql.Date getSqlDateFromDate(final Date pDate) {
        final long time = pDate.getTime();
        return new java.sql.Date(time); // NOPMD.thread group
    }

    /**
     * Méthode validant une chaine de caractères représentant une date sous un
     * certain format.
     * 
     * @param pChaineDate
     *            , la chaine de caractère représentant une date.
     * @param pFormat
     *            , le format voulu.
     * @return {@code true} si la chaine est valide, {@code false} sinon.
     */
    public static boolean isDateValide(final String pChaineDate,
            final String pFormat) {
        boolean retour = false; // NOPMD
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(pFormat, Locale
                    .getDefault());
            sdf.setLenient(false);
            sdf.parse(pChaineDate);
            retour = true;
        } catch (final ParseException pParseException) {
            LOGGER.info(pParseException.getMessage());

        } catch (final IllegalArgumentException pException) {
            LOGGER.info(pException.getMessage());
        }
        return retour;
    }



   

    /**
     * Convertir Date to Calendar
     * 
     * @param pDate
     * @return
     */
    public static Calendar toCalendar(final Date pDate) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(pDate);
        return cal;
    }

    /**
     * Conversion de la chaine au format AAAA/MM/JJ vers une date.
     * 
     * @param pDate
     * @return
     * @throws ParseException
     */
    public static Calendar toCalendar(final String pDate) {
        Calendar calRetour = null; // NOPMD
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",
                    Locale.FRANCE);
            sdf.setLenient(false);
            final Date date = sdf.parse(pDate);

            calRetour = Calendar.getInstance();
            calRetour.setTime(date);
        } catch (final ParseException pParseException) {
            LOGGER.info(pParseException.getMessage());
        }
        return calRetour;
    }

    public static Calendar toCalendar(final String pChaineDate,
            final String pFormat) {
        Calendar calRetour = null; // NOPMD
        if (pChaineDate != null && !pChaineDate.isEmpty()) {
            try {
                final SimpleDateFormat sdf = new SimpleDateFormat(pFormat,
                        Locale.FRANCE);
                sdf.setLenient(false);
                calRetour = Calendar.getInstance(Locale.getDefault());
                calRetour.setTime(sdf.parse(pChaineDate));
            } catch (final ParseException pParseException) {
                LOGGER.info(pParseException.getMessage());
            } catch (final IllegalArgumentException pIException) {
                LOGGER.info(pIException.getMessage());
            }
        }
        return calRetour;
    }

    /**
     * Conversion d'un {@link XMLGregorianCalendar} vers un {@link Calendar}
     * 
     * @param pCalendar
     *            le calender à transformer
     * @return le Calendar correspondant ou null si le paramétre fourni est null
     */
    public static Calendar toCalendar(final XMLGregorianCalendar pCalendar) {

        return pCalendar == null ? null : pCalendar.toGregorianCalendar();
    }

    public static String toDateNaissance(final String pDate) {
        final Calendar calendar = toCalendar(pDate, "dd/MM/yyyy");
        if (null != calendar) {
            return formatteDate(calendar.getTime(), "yyyy-MM-dd");
        }
        return "";

    }

    /**
     * Constructeur privé.
     */
    private UDate() {
        // Classe utilitaire, ne pas instancier.
    }

}
