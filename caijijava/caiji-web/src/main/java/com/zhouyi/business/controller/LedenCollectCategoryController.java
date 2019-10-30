package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.CollectIncomplete;
import com.zhouyi.business.core.model.LedenCollectCategory;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectCategoryService;
import com.zhouyi.business.core.vo.CollectIncompleteVo;
import com.zhouyi.business.core.vo.LedenCollectCategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/category")
@Api(value = "分类接口",hidden =true )
public class LedenCollectCategoryController {

    @Autowired
    private BaseService<LedenCollectCategory, LedenCollectCategoryVo> baseService;

    @Autowired
    private LedenCollectCategoryService ledenCollectCategoryService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id")String id){
       return baseService.findDataById(id);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectCategory ledenCollectCategory){
        //补全信息
        ledenCollectCategory.setPkId(UUID.randomUUID().toString().replace("-",""));
        ledenCollectCategory.setCreateDatetime(new Date());

        return baseService.saveData(ledenCollectCategory);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectCategoryVo ledenCollectCategoryVo){
        LedenCollectCategoryVo ledenCollectCategoryVo1 = ledenCollectCategoryVo;
        if (ledenCollectCategoryVo == null){
            ledenCollectCategoryVo1 = new LedenCollectCategoryVo();
        }
        return baseService.findDataList(ledenCollectCategoryVo1);
    }

    @RequestMapping(value = "/update")
    public Response update(@RequestBody LedenCollectCategory ledenCollectCategory){
        //补全信息

        ledenCollectCategory.setUpdateDatetime(new Date());
        return baseService.updateData(ledenCollectCategory);
    }

    @RequestMapping(value = "/delete/{id}")
    public Response delete(@PathVariable(value = "id") String id){
        return baseService.deleteData(id);
    }

    @RequestMapping(value = "/selectcollectincomplete")
    public Response selectCollectIncomplete(@RequestBody CollectIncompleteVo  collectIncompleteVo){
        return ledenCollectCategoryService.selectCollectIncomplete(collectIncompleteVo);
    }
}
