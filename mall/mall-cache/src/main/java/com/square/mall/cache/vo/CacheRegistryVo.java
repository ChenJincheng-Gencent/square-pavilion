package com.square.mall.cache.vo;

import java.io.Serializable;
import java.util.Arrays;
import com.square.mall.cache.constant.WorkModel;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 *  缓存注册VO
 *
 * @author Gencent
 * @date 2020/7/14
 */
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

    public String[] getAddresses() {
        if (this.addresses == null && StringUtils.isNotBlank(this.host) && StringUtils.isNotBlank(this.port)) {
            String address = this.host + ":" + this.port;
            this.addresses = new String[] { address };
        }
        return this.addresses;
    }

    public void setAddress(String address) {
        if (StringUtils.isNotEmpty(address)) {
            String[] addr = address.split(",");
            this.addresses = new String[addr.length];
            for (int i = 0; i < addr.length; i++) {
                this.addresses[i] = addr[i];
            }
        }
    }


    @Override
    public String toString() {
        return "CacheRegistryVo [type=" + this.type + ", host=" + this.host + ", port=" + this.port + ", appId=" + this.appId + ", appSecret=" + this.appSecret + ", maxIdleConnectionTotal=" + this.maxIdle + ", maxConnectionPoolTotal=" + this.maxTotal + ", maxWaitMillis=" + this.maxWaitMillis + ", livetime=" + this.liveTime + ", dbIndex=" + this.dbIndex + ", workModel=" + this.workModel + ", addresses=" +

                Arrays.toString(this.addresses) + "]";
    }
}
