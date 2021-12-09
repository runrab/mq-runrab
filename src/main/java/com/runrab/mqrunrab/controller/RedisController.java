package com.runrab.mqrunrab.controller;
import com.runrab.mqrunrab.controller.job.UrlGetJob;
import com.runrab.mqrunrab.utils.KafkaTools;
import com.runrab.mqrunrab.utils.RedisTools;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author o
 */
@RestController
public class RedisController {
    @RequestMapping("/date")
    @ResponseBody
    public String m1(){
        Date date=new Date();
        DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RedisTools.set("test_date",sdf.format(date));
        return  RedisTools.get("test_date").toString();
    }
    @RequestMapping("/redis/{keys}")
    @ResponseBody
    public String  m9(@PathVariable(name = "keys") String keys){
        RedisTools.del(keys);
        return "Delete Redis Cluster Success !";
    }
    // ==============  Job定时任务控制  =====================
    @RequestMapping("/job/{key}/{open}")
    @ResponseBody
    public String  job(@PathVariable(name = "key") String key,@PathVariable(name = "open") int open){
        //key 为指定的任务名字   open取值0/1 1代表开启定时任务
        RedisTools.hset("Job_Cron",key,open);
        if (open!=0){
            UrlGetJob.urlGet();
        }
        return "定时任务变更成功";
    }

}
