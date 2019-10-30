package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPAddressbook;
import com.zhouyi.business.core.vo.LedenCollectPAddressbookVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectPAddressbookMapper extends BuffBaseMapper<LedenCollectPAddressbook, LedenCollectPAddressbookVo> {


    /**
     * 插入多条通讯录记录
     * @param addressbooks
     * @return
     */
    int insertAddressBooks(List<LedenCollectPAddressbook> addressbooks);

    /**
     * 根据主键查询通讯录列表
     * */
    List<LedenCollectPAddressbook> findDataListById(LedenCollectPAddressbookVo ledenCollectPAddressbookVo);

    Integer findDataListByIdCount(LedenCollectPAddressbookVo ledenCollectPAddressbookVo);


    /**
     * 删除目标对象的通讯录信息
     * @param targetId
     * @return
     */
    int deleteAddressByTargetId(String targetId);
}