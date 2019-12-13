package com.zhouyi.business.core.model.xinzhen;

import lombok.Data;
import lombok.ToString;

import java.text.SimpleDateFormat;

/**
 * @Author: first
 * @Date: 下午3:10 2019/12/9
 * @Description: 第二公共部分
**/
@Data
public class IrisCommonsSecond extends IrisCommons {
   private String zjbz="0";
   private String lrbz="1";
   private String bcjr_zjlxdm;
   private String bcjr_zjhm;
   private String bcjr_xm;
   private String bcjr_xb;
   private String bcjr_mz;
   private String bcjr_gj;
   private String bcjr_csrq;
   private String bcjr_hjdz;
   private String bcjr_jzdz;
   private String bcjr_sjhm1;
   @ToString.Exclude
   private byte[] zjzp;

   /**
    * 如果有照片则证件标志为有证
    * @param zjzp
    */
   public void setZjzp(byte[] zjzp) {
      this.zjzp = zjzp;
      if(zjzp!=null&&zjzp.length>0){
         this.zjbz="1";
      }
   }

   public void setBcjr_csrq(String bcjr_csrq) {
      this.bcjr_csrq = bcjr_csrq.substring(bcjr_csrq.indexOf(" "));
   }
}
