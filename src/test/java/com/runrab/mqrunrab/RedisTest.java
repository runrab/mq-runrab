package com.runrab.mqrunrab;

import com.runrab.mqrunrab.utils.KafkaTools;
import org.junit.jupiter.api.Test;

public class RedisTest {
@Test
    public void m1(){
    KafkaTools.sendMessage("example","hello");
}
}
