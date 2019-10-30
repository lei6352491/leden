package com.zhouyi.business.utils;

import com.zhouyi.business.core.model.LedenCollectBRecord;
import com.zhouyi.business.core.model.LedenCollectBankcard;
import com.zhouyi.business.core.vo.combine.LedenCollectBankCombine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 杜承旭
 * @ClassNmae: DataEncapsulationUtils
 * @Description: TODO
 * @date 2019/7/19 10:28
 * @Version 1.0
 **/
public class DataEncapsulationUtils {

    /**
     封装银行卡信息
     * */
    public static LedenCollectBankCombine encapsulationLedenCollectBankCombine(Map map){
        LedenCollectBankCombine ledenCollectBankCombine = new LedenCollectBankCombine();
        List list = (List) map.get("data");
        for (Object object : list){
            LedenCollectBankcard ledenCollectBankcard = (LedenCollectBankcard)object;
            List listChild = ledenCollectBankcard.getList();
            List<LedenCollectBRecord> listNew = new ArrayList<>();
            for (Object object1 : listChild){
                LedenCollectBRecord ledenCollectBRecord = (LedenCollectBRecord)object1;
                listNew.add(ledenCollectBRecord);
            }
            ledenCollectBankcard.setList(null);
            ledenCollectBankCombine.setYhkxi(ledenCollectBankcard);
            ledenCollectBankCombine.setJyjlxx(listNew);
        }
        return ledenCollectBankCombine;
    }

    /*public static void main(String[] args) {
        Map map = JsoupParseXmlUtils.jsoupParseXml("D:/xml/pom.xml", LedenCollectBankcard.class, LedenCollectBRecord.class);
        LedenCollectBankCombine ledenCollectBankCombine = encapsulationLedenCollectBankCombine(map);
        System.out.println(ledenCollectBankCombine);
    }*/
}
