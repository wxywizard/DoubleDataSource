package com.sailing.qstaccess.configuration;

import com.sailing.qstaccess.service.AccessService;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 *  @author: wangxy
 * 配置任务
 */
@Configuration
public class QuartzConfiguration {

    @Value("${cron}")
    private String cron;
    /**
     *  配置任务
     * @param quartzTask QuartzTask为需要执行的任务
     * @return
     */
    @Bean(name = "dataTransmissionJob")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(AccessService quartzTask) {

        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();

        // 是否并发执行
        jobDetail.setConcurrent(false);

        // 设置任务的名字
        jobDetail.setName("dataTransmissionJob");

        // 设置任务的分组，在多任务的时候使用
        jobDetail.setGroup("dataTransmissionJobGroup");

        // 需要执行的对象
        jobDetail.setTargetObject(quartzTask);

        /*
         * TODO  非常重要
         * 执行AccessService类中的需要执行方法
         */
        jobDetail.setTargetMethod("test");
        return jobDetail;
    }

    /**
     * 定时触发器
     * @param dataTransmissionJob 任务
     * @return
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(JobDetail dataTransmissionJob){

        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();

        tigger.setJobDetail(dataTransmissionJob);

        //cron表达式
        tigger.setCronExpression(cron);
        tigger.setName("dataTransmissionTrigger");
        return tigger;
    }

    /**
     * 调度工厂
     * @param jobTrigger 触发器
     * @return
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger jobTrigger) {

        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();

        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        factoryBean.setOverwriteExistingJobs(true);

        // 延时启动，应用启动1秒后
        factoryBean.setStartupDelay(1);

        // 注册触发器
        factoryBean.setTriggers(jobTrigger);
        return factoryBean;
    }

}

