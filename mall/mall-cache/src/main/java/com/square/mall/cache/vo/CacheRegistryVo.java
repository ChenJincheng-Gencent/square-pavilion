package com.square.mall.cache.vo;

import java.io.Serializable;
import java.util.Arrays;
import com.square.mall.cache.constant.WorkModel;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class CacheRegistryVo implements Serializable {

    private static final long serialVersionUID = 1389978615223522831L;

    private String type;
    @Deprecated
    private String host;
    @Deprecated
    private String port;
    private String appId;
    private String appSecret;
    private int maxIdle = 20;
    private int maxTotal = 30;
    private int maxWaitMillis = 3000;
    private int liveTime = 86370;

    private int connectionTimeout = 10;
    private int soTimeout = 10;
    private int maxAttempts = 3;

    private int dbIndex = 0;

    private String workModel = WorkModel.SINGLE.getName();



    private String[] addresses;



    public String getType() { return this.type; }







    public void setType(String type) { this.type = type; }






    public String getHost() { return this.host; }







    public void setHost(String host) { this.host = host; }






    public String getPort() { return this.port; }







    public void setPort(String port) { this.port = port; }






    public String getAppId() { return this.appId; }







    public void setAppId(String appId) { this.appId = appId; }






    public String getAppSecret() { return this.appSecret; }







    public void setAppSecret(String appSecret) { this.appSecret = appSecret; }



    public int getMaxIdle() { return this.maxIdle; }



    public void setMaxIdle(int maxIdle) { this.maxIdle = maxIdle; }



    public int getMaxTotal() { return this.maxTotal; }



    public void setMaxTotal(int maxTotal) { this.maxTotal = maxTotal; }



    public int getMaxWaitMillis() { return this.maxWaitMillis; }



    public void setMaxWaitMillis(int maxWaitMillis) { this.maxWaitMillis = maxWaitMillis; }






    public int getLiveTime() { return this.liveTime; }







    public void setLiveTime(int liveTime) { this.liveTime = liveTime; }



    public int getDbIndex() { return this.dbIndex; }



    public void setDbIndex(int dbIndex) { this.dbIndex = dbIndex; }



    public boolean validate() { return (StringUtils.isNotBlank(this.host) && StringUtils.isNotBlank(this.port) && StringUtils.isNotBlank(this.type)); }



    public String getWorkModel() { return this.workModel; }



    public void setWorkModel(String workModel) { this.workModel = workModel; }


    public String[] getAddresses() {
        if (this.addresses == null && StringUtils.isNotBlank(this.host) && StringUtils.isNotBlank(this.port)) {
            String address = this.host + ":" + this.port;
            this.addresses = new String[] { address };
        }
        return this.addresses;
    }


    public void setAddresses(String[] addresses) { this.addresses = addresses; }


    public void setAddress(String address) {
        if (StringUtils.isNotEmpty(address)) {
            String[] addr = address.split(",");
            this.addresses = new String[addr.length];
            for (int i = 0; i < addr.length; i++) {
                this.addresses[i] = addr[i];
            }
        }
    }


    public String toString() {
        return "CacheRegistryVo [type=" + this.type + ", host=" + this.host + ", port=" + this.port + ", appId=" + this.appId + ", appSecret=" + this.appSecret + ", maxIdleConnectionTotal=" + this.maxIdle + ", maxConnectionPoolTotal=" + this.maxTotal + ", maxWaitMillis=" + this.maxWaitMillis + ", livetime=" + this.liveTime + ", dbIndex=" + this.dbIndex + ", workModel=" + this.workModel + ", addresses=" +

                Arrays.toString(this.addresses) + "]";
    }
}
