package com.example.car.utils;//package com.qf.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.qf.job.MyJob;
//import com.qf.pojo.ScheduleJob;
//import org.quartz.*;
//
//public class ScheduleUtils {
//    public static void createSchedule(ScheduleJob scheduleJob, Scheduler scheduler){
//        //scheduleJob  beanName(任务类)  methodName(定时任务执行的方法)
//        try {
//            //1,创建JobDetail持久化到数据库
//            //job的名称是唯一的，如何保证唯一？
//            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myJob_"+scheduleJob.getJobId()).build();
//            //在MyJob类需要知道该调用哪个beanName的什么方法??
//            //传递参数?
//            String json = JSON.toJSONString(scheduleJob);
//            jobDetail.getJobDataMap().put("schedule_job_key",json);
//            //2,创建触发器
//            Trigger trigger = TriggerBuilder.newTrigger().
//                    withSchedule(CronScheduleBuilder.cronSchedule
//                            (scheduleJob.getCronExpression())).withIdentity("myTrigger_"+scheduleJob.getJobId()).build();
//            //3,创建Schedule对象
//            //在QuartzConfig中已经创建了
//            //4,注册触发器和JobDetail
//            scheduler.scheduleJob(jobDetail,trigger);
//            //5,启动任务
//            //  schedulerFactoryBean.setAutoStartup(true);//定时任务是否自动启动 自动启动
//            //scheduler.start();//后面可控制，可不需在这里启动
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 只运行一次
//     * @param scheduler
//     * @param jobId
//     */
//    public  static  void  runTask(Scheduler scheduler,long jobId){
//        try {
//            scheduler.triggerJob(JobKey.jobKey("myJob_"+jobId));
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public  static  void  pauseTask(Scheduler scheduler,long jobId){
//        try {
//            scheduler.pauseJob(JobKey.jobKey("myJob_"+jobId));
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void resumeTask(Scheduler scheduler,long jobId){
//        try {
//            scheduler.resumeJob(JobKey.jobKey("myJob_"+jobId));
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static void deleteTask(Scheduler scheduler,long jobId){
//        try {
//            scheduler.deleteJob(JobKey.jobKey("myJob_"+jobId));
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//
//    }
//    /**
//     * 修改任务的执行表达式cron:* * * * * ?
//     * @param scheduler
//     * @param scheduleJob
//     */
//    public static void updateTask(Scheduler scheduler,ScheduleJob scheduleJob){
//        try {
//            //得到触发器的key
//            TriggerKey triggerKey = TriggerKey.triggerKey("myTrigger_"+scheduleJob.getJobId());
//            //得到原来的触发器对象
//            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//            //替换触发器的表达式
//            trigger = trigger.getTriggerBuilder().withSchedule
//                    (CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())).build();
//            //重置触发器
//            scheduler.rescheduleJob(triggerKey,trigger);
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
