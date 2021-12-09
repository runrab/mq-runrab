package com.runrab.mqrunrab.controller;
import com.runrab.mqrunrab.controller.job.UrlGetJob;
import com.runrab.mqrunrab.utils.RedisTools;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.List;
/**
 * @author o
 * 控制所有定时任务Job
 */
public class Job {
//    @Scheduled(cron = "0 */1 * * * ?")
//    public void process(){
//        //====================360图片爬取任务===============================
//        if (!RedisTools.exists("Job_Cron")){
//            RedisTools.hset("Job_Cron","url_360",0);
//        }
//        Boolean b1=(RedisTools.exists("url_360_key") && ((List)RedisTools.get("url_360_key")).size()>0);
//        Boolean b2=Integer.parseInt(RedisTools.hget("Job_Cron","url_360").toString())==1;
//        if (b1||b2){
//            UrlGetJob.urlGet();
//        }
//    }

}
