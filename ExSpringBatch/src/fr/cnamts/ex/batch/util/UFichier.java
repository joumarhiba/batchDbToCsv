package fr.cnamts.ex.batch.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 * Classe utilitaire de méthode de manipulation de fichier.
 * 
 * @author Norsys - asallem
 */
public final class UFichier {
    /** YYYY_M_MDD_HHMMSS */
    private static final String YYYY_M_MDD_HHMMSS = "yyyyMMdd_hhmmss";
    /** Logger. */
    private static final Logger LOGGER = Logger.getLogger(UFichier.class);

    /**
     * Constructeur privé.
     */
    private UFichier() {
        // Classe utilitaire, ne pas instancier.
    }

    public static boolean copier(final File pSource, final File pDestination) {
        // canal d'entrée
        FileChannel input = null; // NOPMD
        // canal de sortie
        FileChannel output = null; // NOPMD

        boolean resultat;
        try {
            input = new FileInputStream(pSource).getChannel();
            output = new FileOutputStream(pDestination).getChannel();

            input.transferTo(0, input.size(), output);
            resultat = true;
        } catch (final IOException pIOException) {
            LOGGER.error(pIOException.getMessage(), pIOException);
            resultat = false;
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (final IOException exception) {
                    LOGGER.error(exception.getMessage(), exception);
                }
            }
            if (null != output) {
                try {
                    output.close();
                } catch (final IOException exception) {
                    LOGGER.error(exception.getMessage(), exception);
                }
            }
        }
        return resultat;
    }

    public static boolean deplacer(final File pSource, final File pDestination) {
        boolean resultat; // NOPMD
        if (pDestination.exists()) {
            resultat = false;
        } else {
            if (!pDestination.getParentFile().exists()) {
                pDestination.getParentFile().mkdirs();
            }

            boolean result = pSource.renameTo(pDestination);
            if (!result) {
                result = true;
                result &= copier(pSource, pDestination);
                if (result) {

                    pSource.delete();
                }
            }
            resultat = result;
        }
        return resultat;
    }

    /**
     * Méthode utilitaire indiquant qu'un fichier ou répertoire existe.
     * 
     * @param pNomFichier
     *            , nom du fichier ou répertorie.
     * @return
     */
    public static boolean existe(final String pNomFichier) {
        boolean resultat; // NOPMD
        if (null == pNomFichier || pNomFichier.isEmpty()) {
            resultat = false;
        } else {
            final File fichier = new File(pNomFichier);
            resultat = fichier.exists();
        }
        return resultat;
    }

    /**
     * Méthode utilitaire indiquant qu'un fichier ou répertoire existe.
     * 
     * @param pNomFichier
     * @return
     */
    public static boolean isNonexiste(final String pNomFichier) {

        return !existe(pNomFichier);
    }

    /**
     * Méthode utilitaire indiquant qu'un fichier existe.
     * 
     * @param pNomFichier
     *            , nom du fichier.
     * @return
     */
    public static boolean existeFichier(final String pNomFichier) {
        boolean resultat; // NOPMD
        if (null == pNomFichier || pNomFichier.isEmpty()) {
            resultat = false;
        } else {
            final File fichier = new File(pNomFichier);
            resultat = fichier.exists() && fichier.isFile();
        }
        return resultat;
    }

    /**
     * Ecrire dans un fichier
     * 
     * @param pNomFic
     * @param pTexte
     */
    public static boolean ecrireFichier(final String pRepertoire, final String pNomFic, final String pTexte) {
        boolean resultat = false; // NOPMD

        // final File file = new File(pNomFic);
        // if (file.exists()) {
        // file.delete();
        // }
        // on va chercher le chemin et le nom du fichier et on me tout ca dans
        // un String

        FileOutputStream fileOutputStream = null;// NOPMD
        // DataflowAnomalyAnalysis
        // Besoin de l'initialiser avant le try/catch/finally
        OutputStreamWriter outputStreamWriter = null;// NOPMD
        // DataflowAnomalyAnalysis
        // Besoin de l'initialiser avant le try/catch/finally
        // on met try si jamais il y a une exception
        try {
            /**
             * BufferedWriter a besoin d un FileWriter, les 2 vont ensemble, on
             * donne comme argument le nom du fichier true signifie qu on ajoute
             * dans le fichier (append), on ne marque pas par dessus
             */
            final File repertoire = new File(pRepertoire);
            if (!repertoire.exists()) {
                repertoire.mkdir();
            }
            fileOutputStream = new FileOutputStream(repertoire.getAbsoluteFile() + File.separator + pNomFic, true);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            fileOutputStream.write(pTexte.getBytes());
            fileOutputStream.flush();
            resultat = true;
        } catch (final IOException ioe) {
            LOGGER.info("ecrireFichier : " + ioe.getMessage());
            resultat = false;
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (final IOException ioe) {
                    LOGGER.info("ecrireFichier : " + ioe.getMessage());
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (final IOException ioe) {
                    LOGGER.info("ecrireFichier : " + ioe.getMessage());
                }
            }
        }
        return resultat;
    }

    /**
     * methode pour vider le dossier de sortie
     * 
     * @param pRepTravail
     * @param pExtension
     */
    public static void initRepertoire(final String pRepTravail, final String pExtension) {
        if (UFichier.existe(pRepTravail)) {
            final File repertoireFile = new File(pRepTravail);
            if (repertoireFile.exists() && null != repertoireFile.listFiles()) {
                for (final File fichier : repertoireFile.listFiles()) {
                    if (fichier.isFile() && (null == pExtension || pExtension.isEmpty()
                            || fichier.getName().endsWith(pExtension))) {
                        fichier.delete();

                    }
                }
            }
        }
    }

    /**
     * Deplacer le contenu d'un repertoire source vers un repertoire
     * destination.
     * 
     * @param pRepertoireSource
     * @param pRepertoireDest
     *            :Repertoire Destination
     */
    public static void deplacerRepertoireContent(final File pRepertoireSource,
            final File pRepertoireDest) {
        if (null != pRepertoireSource.listFiles()) {
            for (final File fichier : pRepertoireSource.listFiles()) {
                if (fichier.isFile()) {
                    deplacer(fichier, new File(pRepertoireDest// NOPMD
                            // Avoid instantiating new objects inside loops
                            .getAbsoluteFile() + File.separator
                            + fichier.getName()));
                }
            }
        }
    }

    /**
     * permet de suprrimer un fichier exist
     * 
     * @param pNomFichier
     * @return
     */
    public static boolean deleteFichier(final String pNomFichier) {
        boolean result = false;// NOPMD
        if (null != pNomFichier) {
            final File fichierAsupprimer = new File(pNomFichier);
            if (fichierAsupprimer.exists() && fichierAsupprimer.isFile()) {
                result = fichierAsupprimer.delete();
            }
        }
        return result;
    }

    /**
     * après chaque lancement du batch on déplace les fichiers entrées sous ce
     * format : « NOM_FICHIER_»_yyyymmjj_hhmmss.EXTENSION
     * 
     * @param pSource
     * @param pDestination
     * @return
     */
    public static boolean archiver(final File pSource, final File pDestination) {
        boolean resultat = false; // NOPMD
        final String novoName = FilenameUtils.getBaseName(pDestination.getName()) + "_"
                + UDate.formatteDate(new Date(), YYYY_M_MDD_HHMMSS) + "."
                + FilenameUtils.getExtension(pDestination.getName());
        final File novoDist = new File(pDestination.getParentFile() + File.separator + novoName);
        if (novoDist.exists()) {
            resultat = false;
        } else {
            if (!novoDist.getParentFile().exists()) {
                novoDist.getParentFile().mkdirs();
            }
            resultat = copier(pSource, novoDist);
        }
        return resultat;
    }

}
