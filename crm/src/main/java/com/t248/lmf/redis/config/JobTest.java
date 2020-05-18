package com.t248.lmf.redis.config;

import com.t248.lmf.redis.entity.CstLost;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.domain.Page;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JobTest implements Job {
    public static Page<CstLost> list;
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Date time;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Date now = new Date();
        System.out.println("当前日期：" + DATE_FORMAT.format(now));
        Date newDate = stepMonth(now, -6);
        time=newDate;
        System.out.println("当前时间前6个月的日期：" + DATE_FORMAT.format(newDate));

       }
    public static Date stepMonth(Date sourceDate, int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }

}
