package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 杜承旭
 * @ClassNmae: UploadParam
 * @Description: TODO
 * @date 2019/11/12 13:53
 * @Version 1.0
 **/

@Data
@ToString
public class UploadParam {
    private List<String> list;
}
