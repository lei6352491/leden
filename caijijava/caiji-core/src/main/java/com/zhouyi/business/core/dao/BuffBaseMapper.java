package com.zhouyi.business.core.dao;

public interface BuffBaseMapper<T,V> extends BaseMapper<T,V> {
    int deleteByPrimaryKey(String pkId);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(String pkId);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

    int findTotal(V v);
}
