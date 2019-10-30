package com.zhouyi.business.core.vo.xml;

import com.zhouyi.business.core.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: PhonePackXml
 * @Description: 手机数据包xml对象
 * @date 2019/7/11 15:42
 * @Version 1.0
 **/
public class PhonePackXml {

    private List<LedenCollectPTerminal> ledenCollectPTerminal; //终端数据
    private List<LedenCollectPAddressbook> ledenCollectPAddressbook; //通讯录信息
    private List<LedenCollectPCallrecords> ledenCollectPCallrecords; //通话记录
    private List<LedenCollectPMessagerecords> ledenCollectPMessagerecords; //短信记录
    private List<LedenCollectPTotal> ledenCollectPTotals=new ArrayList<>();


    public List<LedenCollectPTerminal> getLedenCollectPTerminal() {
        return ledenCollectPTerminal;
    }

    public void setLedenCollectPTerminal(List<LedenCollectPTerminal> ledenCollectPTerminal) {
        this.ledenCollectPTerminal = ledenCollectPTerminal;
    }

    public List<LedenCollectPAddressbook> getLedenCollectPAddressbook() {
        return ledenCollectPAddressbook;
    }

    public void setLedenCollectPAddressbook(List<LedenCollectPAddressbook> ledenCollectPAddressbook) {
        this.ledenCollectPAddressbook = ledenCollectPAddressbook;
    }

    public List<LedenCollectPCallrecords> getLedenCollectPCallrecords() {
        return ledenCollectPCallrecords;
    }

    public void setLedenCollectPCallrecords(List<LedenCollectPCallrecords> ledenCollectPCallrecords) {
        this.ledenCollectPCallrecords = ledenCollectPCallrecords;
    }

    public List<LedenCollectPMessagerecords> getLedenCollectPMessagerecords() {
        return ledenCollectPMessagerecords;
    }

    public void setLedenCollectPMessagerecords(List<LedenCollectPMessagerecords> ledenCollectPMessagerecords) {
        this.ledenCollectPMessagerecords = ledenCollectPMessagerecords;
    }

    public List<LedenCollectPTotal> getLedenCollectPTotals() {
        return ledenCollectPTotals;
    }

    public void setLedenCollectPTotals(List<LedenCollectPTotal> ledenCollectPTotals) {
        this.ledenCollectPTotals = ledenCollectPTotals;
    }
}
