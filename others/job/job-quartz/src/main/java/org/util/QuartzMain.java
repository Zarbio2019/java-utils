package org.util;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
/**
 * Trigger class
 * @author WODZAROD
 * @link https://www.youtube.com/watch?v=6ogkeEn1h7o
 */
public class QuartzMain
{
   public static void main(String[] args) throws SchedulerException
   {
	   // Define a job and tie it to our job class 
	   JobDetail job = JobBuilder.newJob(QuartzJob.class).build(); // QuartzJob.class -> QuartzJob.java
	   
	   // Trigger
	   // simple trigger: execute now only one time
	   //Trigger t1 = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
	   // cron trigger: with cron expression
	   //Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
	   // cron trigger: each 5 seconds
	   Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
			   //.startNow()
			   .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
			   .build();
	   
	   // Scheduler
	   Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
	   
	   sc.start();
	   sc.scheduleJob(job, t1);
	   //sc.shutdown();
   }
}