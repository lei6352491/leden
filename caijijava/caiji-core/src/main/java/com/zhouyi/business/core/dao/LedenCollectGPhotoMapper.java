package com.zhouyi.business.core.dao;

import com.zhouyi.business.core.model.LedenCollectGPhoto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedenCollectGPhotoMapper extends BaseMapper<LedenCollectGPhoto,String>{


    /**
     * 插入多张图片
     * @param list
     * @return
     */
    int insertPhotos(List<LedenCollectGPhoto> list);


    /**
     * 根据物品编号删除物品图片
     * @param wpbh
     * @return
     */
    int deletePhotoByWpbh(String wpbh);
}