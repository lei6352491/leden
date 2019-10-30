package com.zhouyi.business.dto;

import com.zhouyi.business.core.model.LedenShareApp;
import com.zhouyi.business.core.model.NodeEmpowersDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: ShareAppInsertDto
 * @Description: TODO 新增共享信息数据对象
 * @date 2019/8/5 9:56
 * @Version 1.0
 **/
@ApiModel(description = "共享信息对象")
@Data
@ToString
public class ShareAppInsertDto extends LedenShareApp {
   @ApiModelProperty(hidden = true)
   private String appId;

   @ApiModelProperty(hidden = true)
   private String updateUserId;

   @ApiModelProperty(hidden = true)
   private Date updateDatetime;

   @ApiModelProperty(required = true)
   private String createUserId;

   @ApiModelProperty(hidden = true)
   private List<NodeEmpowersDto> nodeEmpowersDto;

}
