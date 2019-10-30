package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenCollectFinger;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.vo.LedenCollectFingerVo;

import javax.management.modelmbean.XMLParseException;
import java.io.IOException;
import java.util.List;

public interface LedenCollectFingerService extends BaseService<LedenCollectFinger, LedenCollectFingerVo> {

    /**
     * 录入指纹数据
     * @param path
     * @return
     */
    Boolean inputFingersByXml(String path) throws XMLParseException;

    /**
     * 根据人员编号查询指纹信息
     * */
    Response selectFingerByPersonCode(String id);

    /**
     * 生成xml
     * @return
     */
    Boolean generateXml(String cjrybh) throws IOException;
}
