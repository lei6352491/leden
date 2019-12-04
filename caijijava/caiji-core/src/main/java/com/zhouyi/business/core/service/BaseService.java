package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.Head;
import com.zhouyi.business.core.model.Response;

import java.util.List;
import java.util.Map;

public interface BaseService<T,V> {

    Response findDataById(String id);

    Response<Map<String,Object>> findDataList(V v);

    Response saveData(T t);

    Response updateData(T t);

    Response deleteData(String id);

    boolean checkHead(Head head);


    int resoveSaveData(T t);




}
