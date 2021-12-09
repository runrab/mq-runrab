package com.runrab.mqrunrab.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author o
 */
@Getter
@Setter
@Data
public class KafkaConsumerBean implements Serializable {
    private static final long serialVersionUID = 5231134212346077681L;
    private List<Map<String, Object>> data;
    private String database;
    private String es;
    private int id;
    private boolean isDdl;
    private Map mysqlType;
    private String old;
    private List pkNames;
    private String sql;
    private String sqlType;
    private String table;
    private String ts;
    // ERASE  CREATE INSERT DELETE

    private String type;

}
