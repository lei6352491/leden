package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectCategoryNode;
import com.zhouyi.business.core.model.LedenEquipmentParets;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectCategoryNodeService;
import com.zhouyi.business.core.service.LedenEquipmentParetsService;
import com.zhouyi.business.core.vo.LedenCollectCategoryNodeVo;
import com.zhouyi.business.core.vo.LedenCollectCategoryVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/categoryNode")
@Api(value = "节点分类接口",hidden = true)
public class LedenCollectCategoryNodeController {

    @Autowired
    private BaseService<LedenCollectCategoryNode, LedenCollectCategoryNodeVo> baseService;

    @Autowired
    private LedenCollectCategoryNodeService ledenCollectCategoryNodeService;

    @Autowired
    private LedenEquipmentParetsService ledenEquipmentParetsService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id")String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectCategoryNode ledenCollectCategoryNode){
        //初始化排序字段
        if (ledenCollectCategoryNode.getNodeCode().equals("000000000001")){
            ledenCollectCategoryNode.setNodeOrd("1");
        }else {
            LedenCollectCategoryNodeVo ledenCollectCategoryNodeVo = new LedenCollectCategoryNodeVo();
            ledenCollectCategoryNodeVo.setUnitCode(ledenCollectCategoryNode.getUnitCode());
            ledenCollectCategoryNodeVo.setCategoryId(ledenCollectCategoryNode.getCategoryId());
            Response<Map<String, Object>> dataList = baseService.findDataList(ledenCollectCategoryNodeVo);
            Map map = dataList.getData();
            List list = (List)map.get("list");

            if (list == null || list.size() < 1){
                ledenCollectCategoryNode.setNodeOrd("2");
            }else {
                LedenCollectCategoryNode categoryNode = (LedenCollectCategoryNode)list.get(0);
                String nodeOrd = categoryNode.getNodeOrd();
                Integer parseInt = Integer.parseInt(nodeOrd) + 1;
                ledenCollectCategoryNode.setNodeOrd(parseInt.toString());
            }
        }
        ledenCollectCategoryNode.setPkId(UUID.randomUUID().toString().replace("-",""));
        ledenCollectCategoryNode.setIsSkip("1");

        Response response = baseService.saveData(ledenCollectCategoryNode);

        //添加插件信息
        LedenEquipmentParets ledenEquipmentParets = new LedenEquipmentParets();
        ledenEquipmentParets.setPkId(UUID.randomUUID().toString().replace("-",""));
        ledenEquipmentParets.setEquipmentCode(ledenCollectCategoryNode.getEquipmentCode());
        ledenEquipmentParets.setNodeCode(ledenCollectCategoryNode.getNodeCode());
        ledenEquipmentParets.setDeleteFlag("0");
        ledenEquipmentParets.setCreateUserId(ledenCollectCategoryNode.getUserCode());
        ledenEquipmentParets.setCreateDatetime(new Date());
        ledenEquipmentParets.setParetsPlug(ledenCollectCategoryNode.getPlugInId());
        ledenEquipmentParetsService.addLedenEquipmentParets(ledenEquipmentParets);
        return response;
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectCategoryNodeVo ledenCollectCategoryNodeVo){
        if (ledenCollectCategoryNodeVo == null){
            ledenCollectCategoryNodeVo = new LedenCollectCategoryNodeVo();
        }
        return baseService.findDataList(ledenCollectCategoryNodeVo);
    }

    @RequestMapping(value = "/update")
    public Response update(@RequestBody LedenCollectCategoryNode ledenCollectCategoryNode){
        return baseService.updateData(ledenCollectCategoryNode);
    }

    @RequestMapping(value = "/delete")
    public Response delete(@RequestBody LedenCollectCategoryNode ledenCollectCategoryNode){
        Object data = baseService.findDataById(ledenCollectCategoryNode.getPkId()).getData();
        LedenCollectCategoryNode ledenCollectCategoryNodeRequest = (LedenCollectCategoryNode)data;

        LedenCollectCategoryNodeVo ledenCollectCategoryNodeVo = new LedenCollectCategoryNodeVo();
        ledenCollectCategoryNodeVo.setUnitCode(ledenCollectCategoryNode.getUnitCode());
        ledenCollectCategoryNodeVo.setCategoryId(ledenCollectCategoryNode.getCategoryId());
        Response<Map<String, Object>> dataList = baseService.findDataList(ledenCollectCategoryNodeVo);
        Map map = dataList.getData();
        List list = (List)map.get("list");

        for (Object object : list){
            LedenCollectCategoryNode ledenCollectCategoryNodeResult = (LedenCollectCategoryNode) object;
            Integer requestInt = Integer.parseInt(ledenCollectCategoryNodeRequest.getNodeOrd());
            Integer resultInt = Integer.parseInt(ledenCollectCategoryNodeResult.getNodeOrd());
            Integer resultIntNew = resultInt - 1;
            if (requestInt < resultInt){
                ledenCollectCategoryNodeResult.setNodeOrd(resultIntNew.toString());
                baseService.updateData(ledenCollectCategoryNodeResult);
            }
        }
        return baseService.deleteData(ledenCollectCategoryNode.getPkId());
    }

    @RequestMapping(value = "/moveDown")
    public Response moveDown(@RequestBody LedenCollectCategoryNode ledenCollectCategoryNode){
        return ledenCollectCategoryNodeService.moveDown(ledenCollectCategoryNode);
    }

    @RequestMapping(value = "/moveUpward")
    public Response moveUpward(@RequestBody LedenCollectCategoryNode ledenCollectCategoryNode){
        return ledenCollectCategoryNodeService.moveUpward(ledenCollectCategoryNode);
    }
}
