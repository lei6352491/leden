package com.zhouyi.business.core.vo.headvo;

import lombok.ToString;

/**
 * @author 李秸康
 * @ClassNmae: HeaderVo
 * @Description: 定义解析xml
 * @date 2019/7/1 9:28
 * @Version 1.0
 **/

@ToString
public class HeaderVo {

    public String EQUIPMENT_CODE;
    public String USER_CODE;
    public String USER_UNIT_CODE;


    public String getEQUIPMENT_CODE() {
        return EQUIPMENT_CODE;
    }

    public void setEQUIPMENT_CODE(String EQUIPMENT_CODE) {
        this.EQUIPMENT_CODE = EQUIPMENT_CODE;
    }

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public void setUSER_CODE(String USER_CODE) {
        this.USER_CODE = USER_CODE;
    }

    public String getUSER_UNIT_CODE() {
        return USER_UNIT_CODE;
    }

    public void setUSER_UNIT_CODE(String USER_UNIT_CODE) {
        this.USER_UNIT_CODE = USER_UNIT_CODE;
    }
}
