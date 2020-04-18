package com.t248.lmf.redis;


import java.text.SimpleDateFormat;

import java.util.Date;



import org.quartz.Job;

import org.quartz.JobExecutionContext;

import org.quartz.JobExecutionException;

public class RamJob implements Job {

    @Override
        public void execute(JobExecutionContext arg0) throws JobExecutionException {
            Date date = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            System.out.println("Current time is:"+sdf.format(date));

            //编写具体的业务逻辑

            System.out.println("波波 傻逼");

        }
}
