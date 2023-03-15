package fr.cnamts.ex.batch;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.converter.JobParametersConverter;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobExecutionNotFailedException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobExecutionNotStoppedException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.JvmSystemExiter;
import org.springframework.batch.core.launch.support.SystemExiter;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import fr.cnamts.ex.batch.util.CDPSConstantes;
import fr.cnamts.ex.batch.util.ExitMapperCDPS;
import fr.cnamts.ex.batch.util.MessagesConstantes;
import fr.cnamts.ex.batch.util.UDateBDPaiement;
import fr.cnamts.jk.soclebatch.core.constante.ConstantesCnam;
import fr.cnamts.jk.soclebatch.core.exception.SkException;
/**
 * Launcher spécifique à composition des documents ps
 *
 * @author NORSYS
 */

public class CommandLineJobrunner {// NOPMD.More Than One Logger
	 /** format de date DD_MM_YYYY_HH_MM_SS */
    public static final String DD_MM_YYYY_HH_MM_SS = ConstantesCnam.AFF_DD_MM_YYYY + " "
            + ConstantesCnam.AFF_HH_MM_SS;
	  /** Logger */
    private static final Log LOGGER = LogFactory.getLog(CommandLineJobrunner.class);


    /** Logger du RAPPORT . */
    private static final Logger LOGGER_RAPPORT = Logger.getLogger(CDPSConstantes.LOGGER_RAPPORT_CDPS_GLOBAL);

    /**
     * Retrieve the error message set by an instance of
     * {@link CommandLineJobRunner} as it exits. Empty if the last job launched
     * was successful.
     * 
     * @return the error message
     */
    public static String getErrorMessage() {
        return message;
    }

    public static void main(final String[] pArgs) throws SkException { // NOPMD:NPathComplexity

        final CommandLineJobrunner command = new CommandLineJobrunner();

        final List<String> newargs = new ArrayList<String>(Arrays.asList(pArgs));

        try {
            if (System.in.available() > 0) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // NOPMD
                String line = " ";
                while (StringUtils.hasLength(line)) {
                    if (!(line.charAt(0) == '#') && StringUtils.hasText(line)) {
                        LOGGER.debug("Stdin arg: " + line);
                        newargs.add(line);
                    }
                    line = reader.readLine();
                }
            }
        } catch (final IOException e) {
            LOGGER.warn("Could not access stdin (maybe a platform limitation)");
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Exception details", e);
            }
        }

        final Set<String> opts = new HashSet<String>();
        final List<String> params = new ArrayList<String>();

        int count = 0; // NOPMD
        String jobPath = null; // NOPMD
        String jobIdentifier = null; // NOPMD

        for (final String arg : newargs) {
            if (arg.charAt(0) == '-') {
                opts.add(arg);
            } else {
                switch (count) {
                case 0:
                    jobPath = arg;
                    break;
                case 1:
                    jobIdentifier = arg;
                    break;
                default:
                    params.add(arg);
                    break;
                }
                count++;
            }
        }
        params.add("time=".concat(String.valueOf(System.currentTimeMillis())));
        params.add("date="+new Date());
        if ((jobPath == null) || (jobIdentifier == null)) {
            final String msg = "At least 2 arguments are required: JobPath and jobIdentifier.";
            LOGGER.error(msg);
            CommandLineJobrunner.message = msg;
            command.exit(1);
        }

        final String[] parameters = params.toArray(new String[params.size()]);

        final int result = command.start(jobPath, jobIdentifier, parameters, opts);
        command.exit(result);
    }

    /** Exit code mapper */
    private transient ExitCodeMapper exitCodeMapper = new ExitMapperCDPS();

    /** Laucnher */
    private transient JobLauncher launcher;
    /** Job locator */
    private transient JobLocator jobLocator;
    /** Exiter. Package private for unit test */
    private static SystemExiter systemExiter = new JvmSystemExiter();
    /** Message */
    private static String message = "";

    /**
     * Static setter for the {@link SystemExiter} so it can be adjusted before
     * dependency injection. Typically overridden by
     * {@link #setSystemExiter(SystemExiter)}.
     * 
     * @param systemExitor
     */
    public static void presetSystemExiter(final SystemExiter pSystemExiter) {
        CommandLineJobrunner.systemExiter = pSystemExiter;
    }

    /** Job Parameters converter */
    private transient JobParametersConverter jobParametersConverter = new DefaultJobParametersConverter();

    /** Job explorer */
    private transient JobExplorer jobExplorer;

    /** Job repository */
    private transient JobRepository jobRepository;

    /**
     * executerJob
     * 
     * @param pJobParameters
     * @param pJob
     * @return
     * @throws JobExecutionAlreadyRunningException
     * @throws JobRestartException
     * @throws JobInstanceAlreadyCompleteException
     * @throws JobParametersInvalidException
     */
    private int executerJob(final JobParameters pJobParameters, final Job pJob)
            throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
            JobParametersInvalidException {
        final JobExecution jobExecution = launcher.run(pJob, pJobParameters);
        final String codeRetour = jobExecution.getExitStatus().getExitCode();
        return exitCodeMapper.intValue(codeRetour);
    }

    /**
     * Delegate to the exiter to (possibly) exit the VM gracefully.
     * 
     * @param pStatus
     */
    public void exit(final int pStatus) {
        systemExiter.exit(pStatus);
    }

    /**
     * gererAbandonOption
     * 
     * @param pJobIdentifier
     * @throws JobExecutionNotStoppedException
     */
    private void gererAbandonOption(final String pJobIdentifier) throws JobExecutionNotStoppedException {
        final List<JobExecution> jobExecutions = getStoppedJobExecutions(pJobIdentifier);
        if ((jobExecutions == null) || jobExecutions.isEmpty()) {
            throw new JobExecutionNotStoppedException("No stopped execution found for job=" + pJobIdentifier);
        }
        for (final JobExecution jobExecution : jobExecutions) {
            jobExecution.setStatus(BatchStatus.ABANDONED);
            jobRepository.update(jobExecution);
        }
    }

    /**
     * gereStopOption
     * 
     * @param pJobIdentifier
     * @throws JobExecutionNotRunningException
     */
    private void gereStopOption(final String pJobIdentifier) throws JobExecutionNotRunningException {
        final List<JobExecution> jobExecutions = getRunningJobExecutions(pJobIdentifier);
        if ((jobExecutions == null) || jobExecutions.isEmpty()) {
            throw new JobExecutionNotRunningException("No running execution found for job=" + pJobIdentifier);
        }
        for (final JobExecution jobExecution : jobExecutions) {
            jobExecution.setStatus(BatchStatus.STOPPING);
            jobRepository.update(jobExecution);
        }
    }

    private List<JobExecution> getJobExecutionsWithStatusGreaterThan(final String pJobIdentifier,
            final BatchStatus pMinStatus) {

        final Long executionId = getLongIdentifier(pJobIdentifier);
        if (executionId != null) {
            final JobExecution jobExecution = jobExplorer.getJobExecution(executionId);
            if (jobExecution.getStatus().isGreaterThan(pMinStatus)) {
                return Arrays.asList(jobExecution);
            }
            return Collections.emptyList();
        }

        int start = 0;
        final int count = 100;
        final List<JobExecution> executions = new ArrayList<JobExecution>();
        List<JobInstance> lastInstances = jobExplorer.getJobInstances(pJobIdentifier, start, count);

        while (!lastInstances.isEmpty()) {

            for (final JobInstance jobInstance : lastInstances) {
                final List<JobExecution> jobExecutions = jobExplorer.getJobExecutions(jobInstance);
                if ((jobExecutions == null) || jobExecutions.isEmpty()) {
                    continue;
                }
                for (final JobExecution jobExecution : jobExecutions) {
                    if (jobExecution.getStatus().isGreaterThan(pMinStatus)) {
                        executions.add(jobExecution);
                    }
                }
            }

            start += count;
            lastInstances = jobExplorer.getJobInstances(pJobIdentifier, start, count);

        }

        return executions;

    }

    private JobExecution getLastFailedJobExecution(final String pJobIdentifier) {
        final List<JobExecution> jobExecutions = getJobExecutionsWithStatusGreaterThan(pJobIdentifier,
                BatchStatus.STOPPING);
        if (jobExecutions.isEmpty()) {
            return null;
        }
        return jobExecutions.get(0);
    }

    private Long getLongIdentifier(final String pJobIdentifier) {
        try {
            return Long.valueOf(pJobIdentifier);
        } catch (final NumberFormatException e) {
            // Not an ID - must be a name
            return null;
        }
    }

   
    private List<JobExecution> getRunningJobExecutions(final String pJobIdentifier) {
        final List<JobExecution> jobExecutions = getJobExecutionsWithStatusGreaterThan(pJobIdentifier,
                BatchStatus.COMPLETED);
        if (jobExecutions.isEmpty()) {
            return null;
        }
        final List<JobExecution> result = new ArrayList<JobExecution>();
        for (final JobExecution jobExecution : jobExecutions) {
            if (jobExecution.isRunning()) {
                result.add(jobExecution);
            }
        }
        return result;
    }

    private List<JobExecution> getStoppedJobExecutions(final String pJobIdentifier) {
        final List<JobExecution> jobExecutions = getJobExecutionsWithStatusGreaterThan(pJobIdentifier,
                BatchStatus.STARTED);
        if (jobExecutions.isEmpty()) {
            return null;
        }
        final List<JobExecution> result = new ArrayList<JobExecution>();
        for (final JobExecution jobExecution : jobExecutions) {
            if (jobExecution.getStatus() != BatchStatus.ABANDONED) {
                result.add(jobExecution);
            }
        }
        return result;
    }

    /**
     * Injection setter for the {@link ExitCodeMapper}.
     * 
     * @param pExitCodeMapper
     *            the exitCodeMapper to set
     */
    public void setExitCodeMapper(final ExitCodeMapper pExitCodeMapper) {
        exitCodeMapper = pExitCodeMapper;
    }

    /**
     * Injection setter for {@link JobExplorer}.
     * 
     * @param pJobExplorer
     *            the {@link JobExplorer} to set
     */
    public void setJobExplorer(final JobExplorer pJobExplorer) {
        jobExplorer = pJobExplorer;
    }

    public void setJobLocator(final JobLocator pJobLocator) {
        jobLocator = pJobLocator;
    }

    /**
     * Injection setter for {@link JobParametersConverter}.
     * 
     * @param pJobParametersConverter
     */
    public void setJobParametersConverter(final JobParametersConverter pJobParametersConverter) {
        jobParametersConverter = pJobParametersConverter;
    }

    public void setJobRepository(final JobRepository pJobRepository) {
        jobRepository = pJobRepository;
    }

    /**
     * Injection setter for the {@link JobLauncher}.
     * 
     * @param pLauncher
     *            the launcher to set
     */
    public void setLauncher(final JobLauncher pLauncher) {
        launcher = pLauncher;
    }

    /**
     * Injection setter for the {@link SystemExiter}.
     * 
     * @param systemExitor
     */
    public void setSystemExiter(final SystemExiter pSystemExiter) {
        CommandLineJobrunner.systemExiter = pSystemExiter;
    }

    private int start(final String pJobPath, // NOPMD:NPathComplexity
            final String pJobIdentifier, final String[] pParameters, final Set<String> pOpts)
            throws SkException {

        ConfigurableApplicationContext context = null; // NOPMD

        try {
            context = new ClassPathXmlApplicationContext(pJobPath);
            context.getAutowireCapableBeanFactory().autowireBeanProperties(this,
                    AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);

            Assert
                    .state(launcher != null, "A JobLauncher must be provided.  "
                            + "Please add one to the configuration.");
            if (pOpts.contains("-restart") || pOpts.contains("-next")) {
                Assert.state(jobExplorer != null, "A JobExplorer must be provided for a restart "
                        + "or start next operation." + "  Please add one to the configuration.");
            }

            final String jobName;

            final JobParameters jobParameters = jobParametersConverter.getJobParameters(StringUtils
                    .splitArrayElementsIntoProperties(pParameters, "="));
            Assert.isTrue((pParameters == null) || (pParameters.length == 0) || !jobParameters.isEmpty(),
                    "Invalid JobParameters " + Arrays.asList(pParameters) + ". If parameters are provided "
                            + "they should be in the form name=value (no whitespace).");

            if (pOpts.contains("-stop")) {
                gereStopOption(pJobIdentifier);
                return exitCodeMapper.intValue(ExitStatus.COMPLETED.getExitCode());
            }

            if (pOpts.contains("-abandon")) {
                gererAbandonOption(pJobIdentifier);
                return exitCodeMapper.intValue(ExitStatus.COMPLETED.getExitCode());
            }

            if (pOpts.contains("-restart")) {
                final JobExecution jobExecution = getLastFailedJobExecution(pJobIdentifier);
                if (jobExecution == null) {
                    throw new JobExecutionNotFailedException("No failed or stopped execution found for job="
                            + pJobIdentifier);
                }
                jobName = jobExecution.getJobInstance().getJobName();
            } else {
                jobName = pJobIdentifier;
            }

            final Job job;
            if (jobLocator == null) {
                job = (Job) context.getBean(jobName);
            } else {
                job = jobLocator.getJob(jobName);
            }

            return executerJob(jobParameters, job);

        } catch (final Throwable pExcept) {// NOPMD.Avoid Catching Throwable
            // CE_ ENVIRONNEMENT_EXEC voir CNAM_00130878
            if (null != pExcept) {
                if ((null != pExcept.getMessage())
                        && pExcept.getMessage().toLowerCase().contains(
                                "fr.cnamts.jb.Store".toLowerCase())) {
                    LOGGER_RAPPORT
                            .info(MessagesConstantes.CE_ENVIRONNEMENT_EXEC_01
                                    + MessagesConstantes.DISPONIBILITE_CDPS_O);
                } else if ((null != pExcept.getMessage())
                        && pExcept.getMessage().toLowerCase().contains(
                                "NoClassDefFoundError".toLowerCase())) {
                    LOGGER_RAPPORT
                            .info(MessagesConstantes.CE_ENVIRONNEMENT_EXEC_01
                                    + MessagesConstantes.DISPONIBILITE_CDPS_O);
                } else if ((null != pExcept.getCause())
                        && (null != pExcept.getCause().getCause())
                      ) {
                    LOGGER_RAPPORT
                            .info(MessagesConstantes.CE_ENVIRONNEMENT_EXEC_01
                                    + MessagesConstantes.DISPONIBILITE_CDPS_O);
                } else {
                    LOGGER_RAPPORT.info(MessagesConstantes.CE_ENVIRONNEMENT_EXEC_02);
                }
                
                LOGGER_RAPPORT.info("Date de fin de traitement : "
                        + UDateBDPaiement.formatteDate(new Date(), DD_MM_YYYY_HH_MM_SS) + " avec le code retour : "
                        + exitCodeMapper.intValue(ExitStatus.FAILED.getExitCode()));
            }
         

            final String msg = "Job Terminated in error: " + pExcept.getMessage();
            LOGGER.error(msg, pExcept);
            CommandLineJobrunner.message = msg;
            return exitCodeMapper.intValue(ExitStatus.FAILED.getExitCode());
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }}
