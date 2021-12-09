package com.runrab.mqrunrab.sevice;
import cn.hutool.json.JSONUtil;
import com.runrab.mqrunrab.bean.KafkaConsumerBean;
import com.runrab.mqrunrab.utils.RedisTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @author o
 * kafka消息中 type 类型 ERASE删除表  CREATE INSERT DELETE
 */
@Service("KafkaConsumer")
public class KafkaConsumerListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);
    @KafkaListener(topics = {"canal"})
    public void processMessage(String content) {
        try {
            this.handle(content);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    private void handle(String content) {
        System.out.println(content);
        //json 转对象 hutool
        KafkaConsumerBean kafkaConsumerBean = JSONUtil.toBean(content, KafkaConsumerBean.class);
        String type = kafkaConsumerBean.getType();
        List<Map<String, Object>> mapList=kafkaConsumerBean.getData();
        if ("DELETE".equals(type)) {
            for(int i=0;i<mapList.size();i++){
                String id=String.valueOf(kafkaConsumerBean.getData().get(i).get("id"));
                String database = kafkaConsumerBean.getDatabase();
                String table = kafkaConsumerBean.getTable();
                String key = database + "_" + table ;
                RedisTools.hdel(key,key+"_"+id);
            }
        }
        if ("INSERT".equals(type)||"UPDATE".equals(type)){
            for(int i=0;i<mapList.size();i++){
                String id=String.valueOf(kafkaConsumerBean.getData().get(i).get("id"));
                String database = kafkaConsumerBean.getDatabase();
                String table = kafkaConsumerBean.getTable();
                String key = database + "_" + table ;
                RedisTools.hset(key,key+"_"+id,kafkaConsumerBean.getData().get(i));
            }
        }
    }
}