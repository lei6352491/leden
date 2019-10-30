package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectPTerminal;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.BaseService;
import com.zhouyi.business.core.service.LedenCollectPTerminalService;
import com.zhouyi.business.core.vo.LedenCollectPTerminalVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/terminal")
@Api(hidden = true)
public class LedenCollectPTerminalController {

    @Autowired
    private BaseService<LedenCollectPTerminal, LedenCollectPTerminalVo> baseService;

    @Autowired
    private LedenCollectPTerminalService ledenCollectPTerminalService;

    @RequestMapping(value = "/get/{id}")
    public Response getDataById(@PathVariable(value = "id") String id){
        return baseService.findDataById(id);
    }

    @RequestMapping(value = "/getlist")
    public Response getList(@RequestBody LedenCollectPTerminalVo ledenCollectPTerminalVo){
        LedenCollectPTerminalVo ledenCollectPTerminalVo1 = ledenCollectPTerminalVo;
        if (ledenCollectPTerminalVo == null){
            ledenCollectPTerminalVo1 = new LedenCollectPTerminalVo();
        }
        return baseService.findDataList(ledenCollectPTerminalVo1);
    }

    @RequestMapping(value = "/save")
    public Response saveData(@RequestBody LedenCollectPTerminal ledenCollectPTerminal){
        return baseService.saveData(ledenCollectPTerminal);
    }

    @RequestMapping(value = "/update")
    public Response updateData(@RequestBody LedenCollectPTerminal ledenCollectPTerminal){
        return baseService.updateData(ledenCollectPTerminal);
    }

    @RequestMapping("/delete/{id}")
    public Response deleteData(@PathVariable(value = "id")String id){
        return baseService.deleteData(id);
    }

    @RequestMapping(value = "/getTerminal/{id}")
    public Response selectMiniDataByPersonCode(@PathVariable String id){
        return ledenCollectPTerminalService.selectMiniDataByPersonCode(id);
    }

    @RequestMapping(value = "/getTerminalMany/{id}")
    public Response selectDataByPersonCode(@PathVariable String id){
        return ledenCollectPTerminalService.selectDataByPersonCode(id);
    }
}
