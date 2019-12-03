package com.zhouyi.business.core.model.xinzhen;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: first
 * @Date: 下午3:14 2019/12/1
 * @Description: 带上版本号
**/
@Data
@ConfigurationProperties(prefix = "criminal-investigation.iris")
@Component
public class IdSecretVersion extends IdSecret{

    /**
     * 版本号
     */
    private String version;


}
