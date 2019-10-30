package com.zhouyi.business.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 李秸康
 * @ClassNmae: ShareAppDetailDto
 * @Description: TODO app注册详情信息数据传输对象
 * @date 2019/8/13 16:15
 * @Version 1.0
 **/
@ApiModel(value = "共享应用详情信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class ShareAppDetailDto extends LedenShareApp{

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;
    @ApiModelProperty(value = "修改人姓名")
    private String updateUserName;

}
