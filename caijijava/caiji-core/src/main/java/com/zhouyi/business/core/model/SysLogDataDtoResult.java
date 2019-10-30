package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 李秸康
 * @ClassNmae: SysLogDataDtoResult
 * @Description: TODO 数据日志结果对象
 * @date 2019/8/19 10:25
 * @Version 1.0
 **/
@ApiModel(value = "数据结果模型")
@Data
public class SysLogDataDtoResult {
    @ApiModelProperty(value = "基本数据")
    private SysLogData sysLogData;
    @ApiModelProperty(value = "附带数据")
    private SysLogDataDto sysLogDataDto;
}
