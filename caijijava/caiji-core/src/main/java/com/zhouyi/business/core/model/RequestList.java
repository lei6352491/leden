package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author 杜承旭
 * @ClassNmae: RequestList
 * @Description: TODO
 * @date 2019/8/17 10:21
 * @Version 1.0
 **/

@Data
@ToString
public class RequestList implements Serializable {
    private String path;
    private List<String> list;
}
