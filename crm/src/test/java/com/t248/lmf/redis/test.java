package com.t248.lmf.redis;

import org.junit.jupiter.api.Test;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class test {
    public static void main(String[] args) throws Exception {
        //创建一个JobDetail实例，将该实例与HelloJob class绑定
        JobDetail jobDetail = JobBuilder.newJob(RamJob.class).withIdentity("myJob","group1").build();
        //创建一个Trigger实例，定义该Job立即执行，并且每隔2秒钟重复执行一次，直到永远
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();
        //创建Scheduler实例
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
    @Test
    public void a(){
        for (int i=1;i<10;i++){
            for (int j=1;j<=i;j++){
                System.out.print(i+"*"+j+"="+i*j+" ");
            }
            System.out.println();
        }
    }
}
