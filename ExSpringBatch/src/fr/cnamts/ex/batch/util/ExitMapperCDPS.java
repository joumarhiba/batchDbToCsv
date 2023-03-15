package fr.cnamts.ex.batch.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.launch.support.ExitCodeMapper;

import fr.cnamts.ex.batch.util.CDPSConstantes;
/**
 * Mapper de code retour interne vers leur équivalent en code retour externe.
 * 
 * @author NORSYS
 */
public class ExitMapperCDPS implements ExitCodeMapper {

    /** ExitStatus sans erreur bloquante. */
    public static final ExitStatus EXIT_STATUS_OK_WARNING = new ExitStatus("WARNING");

    /** ExitStatus avec erreur bloquante. */
    public static final ExitStatus EXIT_STATUS_KO = ExitStatus.FAILED;

    /** Code retour sans aucune erreur. */
    public static final Integer CODE_RETOUR_OK = 0;

    /** Code retour sans erreur bloquante. */
    public static final Integer CODE_RETOUR_OK_WARNING = 1;

    /** Code retour si erreur bloquante. */
    public static final Integer CODE_RETOUR_KO = 2;

    /** Logger. */
    private static final Logger LOGGER = Logger.getLogger(ExitMapperCDPS.class);

    /**
     * Table de correspondance entre les codes de status et les codes de retour.
     */
    private final transient Map<String, Integer> tableCodeRetour;

    public ExitMapperCDPS() {
        super();
        // Chargement de la table de correspondance.
        tableCodeRetour = new HashMap<String, Integer>();
        tableCodeRetour.put(ExitStatus.COMPLETED.getExitCode(), CODE_RETOUR_OK);
        tableCodeRetour.put(CDPSConstantes.EXIT_STATUT_ERREUR.getExitCode(), CODE_RETOUR_OK_WARNING);
        tableCodeRetour.put(CDPSConstantes.EXIT_STATUT_BLOQUANTE.getExitCode(), CODE_RETOUR_KO);
        tableCodeRetour.put(ExitStatus.STOPPED.getExitCode(), CODE_RETOUR_KO);
        tableCodeRetour.put(ExitStatus.FAILED.getExitCode(), CODE_RETOUR_KO);
    }

    @Override
    public int intValue(final String pExitCode) {
        Integer retour = tableCodeRetour.get(pExitCode);
        if (retour == null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Statut de fin inconnu : " + pExitCode);
            }
            retour = 6;
        } 
        return retour;
    }

}
