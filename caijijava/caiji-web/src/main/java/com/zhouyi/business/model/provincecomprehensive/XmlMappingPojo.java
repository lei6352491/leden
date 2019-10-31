package com.zhouyi.business.model.provincecomprehensive;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: first
 * @Date: 上午7:00 2019/10/31
 * @Description: 
**/
@Data
@ToString
public class XmlMappingPojo {

    private String personInfo;

    /**
     * 指纹编号
     */
    private String card_N;

    private String DNA_N;

    private String person_Name;

    private String personAlias;

    private String birthday;

    private String sexCode;

    private String height;

    private String weight;

    private String footLength;

    private String countryCode;

    private String education;

    /**
     * 政治面貌
     */
    private String political;

    /**
     * 籍贯代码
     */
    private String placeOfOrigin;

    private String profession;

    private String workPlace;

    private String mobile1;

    private String mobile2;

    private String mobile3;

    private String telephone;

    private String nationCode;

    private String identityCardNum;

    /**
     * 其他证件类别代码
     */
    private String certifyTypeCode;

    /**
     * 其他证件号码
     */
    private String certifyNum;

    /**
     * 户籍地行政区划代码
     */
    private String regAdminDivCode;

    /**
     * 户籍地行政区划
     */
    private String regAdminDivName;


    /**
     * 户籍地详址
     */
    private String regAddress;

    /**
     * 现住地行政区划代码
     */
    private String dwellAdminDivCode;

    /**
     * 现住地行政区划
     */
    private String dwellAdminDivName;

    /**
     * 现住地详址
     */
    private String dwellAddress;

    /**
     * 人员类别代码
     */
    private String personTypeCode;


    /**
     * 案件类别代码1
     */
    private String caseType1Code;

    /**
     * 案件类别代码2
     */
    private String caseTypeCode;


    /**
     * 案件类别代码3
     */
    private String caseType3Code;

    /**
     * 前科标识代码0：不属于前科库人员，1：属于前科库人
     */
    private String crimeRecordFlag;

    /**
     * 前科标示
     */
    private String crimeRecordFlagName;

    /**
     * 被采集原因
     */
    private String reason;

    /**
     * 采集单位代码
     */
    private String scanUnitCode;

    /**
     * 采集单位名称
     */
    private String scanUnitName;

    /**
     * 采集地点代码
     */
    private String placeUnitCode;


    /**
     * 采集民警
     */
    private String scanPersonName;


    /**
     * 采集日期
     */
    private String scanDate;

    /**
     * 采集身份证代码
     */
    private String scanCard;


    /**
     * 备注
     */
    private String remark;

    /**
     * 身份证照片
     */
    private String  idCardPhoto;
}
