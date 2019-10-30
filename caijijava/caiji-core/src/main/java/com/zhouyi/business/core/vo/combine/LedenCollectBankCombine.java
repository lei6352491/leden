package com.zhouyi.business.core.vo.combine;

import com.zhouyi.business.core.model.LedenCollectBRecord;
import com.zhouyi.business.core.model.LedenCollectBankcard;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectBankCombine
 * @Description: TODO 银行卡解析组合对象
 * @date 2019/7/18 15:25
 * @Version 1.0
 **/
public class LedenCollectBankCombine {
    private LedenCollectBankcard yhkxi; //银行卡记录信息属性
    private List<LedenCollectBRecord> jyjlxx; //银行卡交易记录属性

    public LedenCollectBankcard getYhkxi() {
        return yhkxi;
    }

    public void setYhkxi(LedenCollectBankcard yhkxi) {
        this.yhkxi = yhkxi;
    }


    public List<LedenCollectBRecord> getJyjlxx() {
        return jyjlxx;
    }

    public void setJyjlxx(List<LedenCollectBRecord> jyjlxx) {
        this.jyjlxx = jyjlxx;
    }

    @Override
    public String toString() {
        return "LedenCollectBankCombine{" +
                "yhkxi=" + yhkxi +
                ", jyjlxx=" + jyjlxx +
                '}';
    }
}
