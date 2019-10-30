package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectNode;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectNodeService;
import com.zhouyi.business.core.vo.CollectNodeVo;
import com.zhouyi.business.core.vo.LedenCollectNodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api/conllectnode")
@Api(description = "采集节点API接口")
public class LedenCollectNodeController {

    @Autowired
    private BaseService<LedenCollectNode, LedenCollectNodeVo> baseService;

    @Autowired
    private LedenCollectNodeService ledenCollectNodeService;

    @RequestMapping(value = "/getnode")
    @ApiOperation(value = "采集节点详情",notes = "根据采集节点id查询采集节点详情")
    public Response getDataById(@RequestBody LedenCollectNode ledenCollectNode){
        if (ledenCollectNode != null && StringUtils.isNotEmpty(ledenCollectNode.getNodeCode())){
            return baseService.findDataById(ledenCollectNode.getNodeCode());
        }
        return null;
    }

    @ApiOperation(value = "获取采集节点集合",notes = "根据条件获取采集节点集合")
    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectNodeVo ledenCollectNodeVo){
        LedenCollectNodeVo ledenCollectNodeVo1 = ledenCollectNodeVo;
        if (ledenCollectNodeVo == null){
            ledenCollectNodeVo1 = new LedenCollectNodeVo();
        }
        return baseService.findDataList(ledenCollectNodeVo1);
    }

    @ApiOperation(value = "保存采集节点",notes = "添加采集节点数据信息")
    @RequestMapping(value = "/savenode")
    public Response saveData(@RequestBody LedenCollectNode ledenCollectNode){
        //补全信息
        //创建人，更新人信息，从登陆用户中获取

        ledenCollectNode.setCreateDatetime(new Date());
        ledenCollectNode.setUpdateDatetime(ledenCollectNode.getCreateDatetime());
        return baseService.saveData(ledenCollectNode);
    }

    @ApiOperation(value = "更新采集节点",notes = "更新采集节点数据信息")
    @RequestMapping(value = "/updatenode")
    public Response updateData(@RequestBody LedenCollectNode ledenCollectNode){
        //补全信息
        //更新人信息从登陆用户中获取

        ledenCollectNode.setUpdateDatetime(new Date());
        return baseService.updateData(ledenCollectNode);
    }

    @RequestMapping("/deletenode")
    @ApiOperation(value = "删除采集节点",notes = "根据采集节点id删除采集节点数据信息")
    public Response deleteData(@RequestBody LedenCollectNode ledenCollectNode){
        if (ledenCollectNode != null && StringUtils.isNotEmpty(ledenCollectNode.getNodeCode())){
            return baseService.deleteData(ledenCollectNode.getNodeCode());
        }
        return null;
    }

    @RequestMapping(value = "findcollectnodelist")
    @ApiOperation(value = "获取节点节点集合信息",notes = "根据部门编号和采集类型编号查询采集节点集合")
    public Response findCollectNodeList(@RequestBody CollectNodeVo collectNodeVo){
        return ledenCollectNodeService.selectCollectNodeListByCategoryOrUnit(collectNodeVo);
    }
}
