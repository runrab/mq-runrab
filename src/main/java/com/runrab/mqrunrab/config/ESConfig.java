package com.runrab.mqrunrab.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
//https://blog.csdn.net/weixin_44335140/article/details/121162331

/**
 * @author o
 */
@Configuration
public class ESConfig {
    @Bean
    public RestHighLevelClient getRestHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                //如果是集群再配置多个
                RestClient.builder(new HttpHost("node01",9200,"http"),
                        new HttpHost("node02",9200,"http"),
                        new HttpHost("node03",9200,"http")
                )
        );
        return client;
    }
}
