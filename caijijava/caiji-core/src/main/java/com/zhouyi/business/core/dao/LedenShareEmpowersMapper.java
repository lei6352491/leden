package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenShareEmpowers;
import com.zhouyi.business.core.model.NodeEmpowersDto;
import com.zhouyi.business.core.vo.LedenShareEmpowersVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LedenShareEmpowersMapper
        extends BuffBaseMapper<LedenShareEmpowers, LedenShareEmpowersVo>{

    /**
     * 获取应用授权信息
     */
    List<LedenShareEmpowers> listShareEmpowersByConditions(Map<String,Object> conditions);


    /**
     * 查询节点信息
     */
    List<NodeEmpowersDto> getNodeEmpowersByAppId(String appId);


    /**
     * 添加多条数据信息
     */
    int insertMultiEmpowers(List<LedenShareEmpowers> empowers);

    /**
     * 批量修改
      * @param empowers
     * @return
     */
    int updateMultiEmpowers(List<LedenShareEmpowers> empowers);
}