package com.runrab.mqrunrab.bean;
import com.github.pagehelper.Page;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**统一结果返回类
 * @author o
 */
public class Result<T> implements  Serializable {

    public static final String OK = "0";

    public static final Result<Object> SUCCESS = new Result<Object>("0", "OK");

    public static class Bean implements Serializable {
        int total = 0;

        public Bean(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
    private Collection<T> beans;
    private Bean bean;

//    private Object object = new Object();
    private T object;
    private int totalCount = 0;

    private String returnMessage = "";

    private String returnCode = "";

    public Result() {
        this(null);
    }


    public Result(String returnCode, String returnMessage) {
        this(null);
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public Result(Collection<T> dataList) {
        this(dataList, dataList != null ? dataList.size() : 0);
    }

    public Result(int totalCount) {
        this((Collection<T>)null, totalCount);
    }

    public Result(Collection<T> dataList, int totalCount) {
        this.totalCount = totalCount;
        this.returnCode = Result.OK;
        this.returnMessage = Result.OK;
        this.beans = dataList;
        this.getBean().setTotal(totalCount);
    }

    public Result(Page<T> pageResult) {
        this(pageResult != null ? pageResult.getResult() : null);
        this.setTotalCount(pageResult != null ? (int)pageResult.getTotal() : 0);
    }

    public Collection<T> getBeans() {
        if (this.beans == null)
            this.beans = new ArrayList<>(0);
        return beans;
    }

    public void setBeans(Collection<T> beans) {
        this.beans = beans;
    }

    public Bean getBean() {
        if (this.bean == null)
            this.bean = new Bean(0);
        return bean;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

//    public Object getObject() {
//        return object;
//    }
//
//    public void setObject(Object object) {
//        this.object = object;
//    }
    public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	
}
