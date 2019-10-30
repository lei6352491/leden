package com.zhouyi.business.core.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 杜承旭
 * @ClassNmae: RoleMenuRequest
 * @Description: TODO
 * @date 2019/8/2 14:33
 * @Version 1.0
 **/

@Data
@ToString
public class RoleMenuRequest {

    private String userId;

    private String roleId;

    private String roleName;

    private String roleDescription;

    private List<String> menus;
}
