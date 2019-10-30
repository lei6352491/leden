package com.zhouyi.business.core.dao;

import java.util.List;
import java.util.Map;

/**
 * 操作数据基础类
 * 
 *
 * @param <T>
 * @param <V>
 */
public interface BaseMapper<T, V> {
	/**
	 * 通过主键删除记录
	 * @param id  主键
	 * @return    删除行数
	 */
    int deleteByPrimaryKey(V id);
    /**
     * 插入数据
     * @param data 数据
     * @return 1-成功，0-失败
     */
    int insert(T data);
    /**
     * 批量插入数据
     * @param data 数据
     * @return 1-成功，0-失败
     */
    int insertBatch(List<T> list);
    /**
     * 插入数据并且返回主键
     * 主键在data中返回
     * @param data 数据
     * @return 1-成功，0-失败
     */
    int insertAndReturnId(T data);
    /**
     * 插入数据，忽略空字段
     * @param data 数据
     * @return 1-成功，0-失败
     */
    int insertSelective(T data);
    /**
     * 插入数据返回主键，忽略空字段
     * 主键在data中返回
     * @param data 数据
     * @return 1-成功，0-失败
     */
    int insertSelectiveAndReturnId(T data);
    /**
     * 获取所有数据
     * @return    数据列表
     */
    List<T> selectAll();

    /**
     * 通过主键获取数据
     * @param id  主键
     * @return    一行数据
     */
    T selectByPrimaryKey(V id);
    /**
     * 通过Model获取数据
     * @param data  Model数据，非空字段都做为条件查询
     * @return    数据列表
     */
    List<T> selectByModel(T data);    
    /**
     * 更新数据，忽略空字段
     * 主键为更新条件，其他非空字段为数据
     * @param data 数据
     * @return 更新结果行数
     */
    int updateByPrimaryKeySelective(T data);
    /**
     * 更新数据
     * 主键为更新条件，其他为数据
     * @param data 数据
     * @return 更新结果行数
     */
    int updateByPrimaryKey(T data);
    
    int replaceBatch(List<T> list);

    /**
     * 根据条件获取信息
     * @param conditions
     * @return
     */
    List<T> listDataByConditions(Map<String,Object> conditions);

    /**
     * 根据条件获取记录数量
     * @param conditions
     * @return
     */
    int getDataCountByConditions(Map<String,Object> conditions);


    /**
     * 根据条件获取数据对象
     * @param conditions
     * @return
     */
    T getDataByConditions(Map<String,Object> conditions);


}