package com.zhouyi.business.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: menuTreeNodeVo
 * @Description: TODO
 * @date 2019/6/28 16:43
 * @Version 1.0
 **/
public class MenuTreeNodeVo {

    private String menuCode;

    private String menuName;

    private List<MenuTreeNode> menuTreeNodes = new ArrayList<>();
}
