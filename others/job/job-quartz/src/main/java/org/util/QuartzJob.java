package org.util;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Job Class
 * @author WODZAROD
 *
 */
public class QuartzJob implements Job {
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Hello");
		System.out.println(new Date());
	}
}
