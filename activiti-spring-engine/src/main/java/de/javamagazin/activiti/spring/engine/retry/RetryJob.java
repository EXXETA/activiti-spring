package de.javamagazin.activiti.spring.engine.retry;

import java.util.List;

import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.Job;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class RetryJob implements ApplicationListener<ContextRefreshedEvent> {

    private Log LOG = LogFactory.getLog(RetryJob.class);

    @Autowired
    private ProcessEngine processEngine;

    private int retryCount;

    private boolean active = false;

    private boolean contextInitialized = false;

    public void execute() {
        if (!active || !contextInitialized) {
            LOG.info("Retry Service Inactive");
            return;
        }
        LOG.info("Starting retry job");
        ManagementService managementService = processEngine.getManagementService();
        List<Job> jobs = managementService.createJobQuery().messages().orderByJobRetries().asc().list();
        LOG.info("Found approx. " + jobs.size() + " jobs to retry");
        for (Job job : jobs) {
            if (job.getRetries() < (-1 * retryCount)) {
                // Skip job in case of more than 20 manual retries (and continue with the next job)
                continue;
            } else if (job.getRetries() > 0) {
                // Skip the jobs that are still in the auto-retry phase
                break;
            } else
                LOG.info("Retrying job id " + job.getId());
            managementService.executeJob(job.getId());
        }
        LOG.info("Retry job done");
    }

    @Required
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    @Required
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        contextInitialized = true;
        LOG.info("release RetryJobs startup Lock.");
    }

}
