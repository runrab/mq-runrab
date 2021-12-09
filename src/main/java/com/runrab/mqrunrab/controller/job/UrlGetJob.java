package com.runrab.mqrunrab.controller.job;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.runrab.mqrunrab.utils.RedisTools;
import java.util.*;
public class UrlGetJob {
    public static void urlGet() {
        //redis中配置string key对应cron的开启和关闭  hash  key:cron  filed1:url360 filed2:open/close或者0/1
        //http://wallpaper.apc.360.cn/index.php?c=WallPaper&a=getAppsByCategory&cid=10&start=500&count=50&from=360chrome
        //http://cdn.apc.360.cn/index.php?c=WallPaper&a=getAllCategoriesV2&from=360chrome
        if (!RedisTools.exists("url_360_key")){
            List<Integer> lst = Arrays.asList(36,6,30,9,15,26,11,14,5,12,10,29,7,13,22,16,18,35);
            RedisTools.set("url_360_key",lst);
            RedisTools.set("url_360_value",0);
        }else {
            List lst=((List) RedisTools.get("url_360_key"));
            String cid = lst.get(0).toString();
            int start=Integer.parseInt(RedisTools.get("url_360_value").toString());
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("c", "WallPaper");
            paramMap.put("a", "getAppsByCategory");
            paramMap.put("cid", cid);
            paramMap.put("start", ""+start);
            paramMap.put("count", "100");
            paramMap.put("from", "360chrome");
            System.out.println(paramMap);
            String result = HttpUtil.get("http://wallpaper.apc.360.cn/index.php?", paramMap);
            RedisTools.set("url_360_value",Integer.valueOf(start + 100));
            JSONObject jsonObject= JSONUtil.parseObj(result);
            int total=Integer.parseInt(String.valueOf(Integer.valueOf(jsonObject.get("total").toString())-100));
            if (total<=Integer.parseInt(RedisTools.get("url_360_value").toString())){
                lst.remove(((List)RedisTools.get("url_360_key")).get(0));
            }
            System.out.println(result);
//            KafkaTools.sendMessage("example", result);
        }
    }
}
