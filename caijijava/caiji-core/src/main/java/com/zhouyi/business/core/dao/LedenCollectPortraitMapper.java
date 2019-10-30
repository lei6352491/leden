package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectPortrait;
import com.zhouyi.business.core.vo.LedenCollectPortraitVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LedenCollectPortraitMapper extends
        BuffBaseMapper<LedenCollectPortrait, LedenCollectPortraitVo>{


    /**
      * @author 李秸康
      * @Description 新增肖像数据集
      * @date 2019/7/2
      * @param
      * @return
     **/
    int insertLedenCollectPortraits(@Param("list")List<LedenCollectPortrait> ledenCollectPortraits);

    /**
     * 根据人员编号查询人像信息
     * */
    List<LedenCollectPortrait> selectPortraitByPersonCode(String id);

    /**
     * 根据人员编号查询人像个数
     * @param person
     * @return
     */
    int getPortraitCountByPerson(String person);


    /**
     * 根据人员编号删除编号信息
     * @param personId
     * @return
     */
    int deletePortraitByPerson(String personId);

}