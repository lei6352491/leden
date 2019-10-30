package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.ZhaoBiao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: ZhaoBiaoMapper
 * @Description: TODO 招标接口
 * @date 2019/7/15 11:39
 * @Version 1.0
 **/
@Mapper
public interface ZhaoBiaoMapper {

    /**
     * 根据标题模糊查询
     * @param title
     * @return
     */
    List<ZhaoBiao> listZhaoBiaoInfoByTitle(@Param("title") String title);
}
