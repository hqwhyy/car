package com.example.car.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class QuartzConfig {
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource") DataSource dataSource){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //真正的任务持久化保存到数据库中QRTZ_XXX
        //quartz参数
        Properties prop = new Properties();
        //配置实例
        prop.put("org.quartz.scheduler.instanceName", "MyScheduler");//实例名称
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        //线程池配置
        prop.put("org.quartz.threadPool.threadCount", "5");
        //JobStore配置  持久化的类
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");

        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setQuartzProperties(prop);
        //定时任务是否自动启动 默认是true 可不需设置
       // schedulerFactoryBean.setAutoStartup(false);
        schedulerFactoryBean.setStartupDelay(5);//延迟5秒启动
        //启动时更新己存在的Job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        return  schedulerFactoryBean;
    }
}
