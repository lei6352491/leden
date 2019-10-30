package com.zhouyi.business.core.model;

import lombok.Data;


/**
 * @author 李秸康
 * @ClassNmae: NodeEmpowersDto
 * @Description: TODO 节点授权对象
 * @date 2019/8/6 15:30
 * @Version 1.0
 **/
@Data
public class NodeEmpowersDto {
    private LedenCollectNode ledenCollectNode; //所有节点信息
    private LedenShareEmpowers ledenShareEmpowers; //已授权列表
}
