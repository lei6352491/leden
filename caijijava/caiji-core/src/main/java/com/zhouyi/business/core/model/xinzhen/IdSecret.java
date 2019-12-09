package com.zhouyi.business.core.model.xinzhen;

import lombok.Data;

/**
 * @Author: first
 * @Date: 下午1:31 2019/12/1
 * @Description: ID和Secret部分
**/
@Data
public class IdSecret {
   /**
    * 请求方id
    */
   private String client_id;

   /**
    * 请求方秘钥
    */
   private String client_secret;
}
