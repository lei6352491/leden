package com.zhouyi.business.component;

import com.zhouyi.business.core.model.SysDictList;
import com.zhouyi.business.core.service.SysDictListService;
import com.zhouyi.business.core.vo.SysDictListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: ApplicationRunner
 * @Description: TODO
 * @date 2019/7/10 10:27
 * @Version 1.0
 **/

@Component
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

    public static Map<String, List<SysDictList>> map = new HashMap<>();

    @Autowired
    private SysDictListService sysDictListService;

    @Override
    public void run(ApplicationArguments applicationArguments) {

        SysDictListVo sysDictListVo = new SysDictListVo();
        sysDictListVo.setSign("DICT_RYGJ");
        sysDictListVo.setPage(1);
        sysDictListVo.setSize(1000);
        List<SysDictList> nationalityOptions = sysDictListService.findSysDiceListByModel(sysDictListVo);
        map.put("nationalityOptions",nationalityOptions);

        sysDictListVo.setSign("DICT_RYMZ");
        sysDictListVo.setPage(1);
        sysDictListVo.setSize(1000);
        List<SysDictList> nationOptions = sysDictListService.findSysDiceListByModel(sysDictListVo);
        map.put("nationOptions",nationOptions);

        sysDictListVo.setSign("DICT_XZQH");
        sysDictListVo.setPage(1);
        sysDictListVo.setSize(4000);
        List<SysDictList> nativePlaceOptions = sysDictListService.findSysDiceListByModel(sysDictListVo);
        map.put("nativePlaceOptions",nativePlaceOptions);

        sysDictListVo.setSign("DICT_RYXL");
        sysDictListVo.setPage(1);
        sysDictListVo.setSize(4000);
        List<SysDictList> educationOptions = sysDictListService.findSysDiceListByModel(sysDictListVo);
        map.put("educationOptions",educationOptions);

        sysDictListVo.setSign("DICT_RYZHZHMM");
        sysDictListVo.setPage(1);
        sysDictListVo.setSize(50);
        List<SysDictList> politicalOutlookOptions = sysDictListService.findSysDiceListByModel(sysDictListVo);
        map.put("politicalOutlookOptions",politicalOutlookOptions);

        sysDictListVo.setSign("DICT_RYGRSHF");
        sysDictListVo.setPage(1);
        sysDictListVo.setSize(300);
        List<SysDictList> identityOptions = sysDictListService.findSysDiceListByModel(sysDictListVo);
        map.put("identityOptions",identityOptions);

        sysDictListVo.setSign("DICT_RYZJXY");
        sysDictListVo.setPage(1);
        sysDictListVo.setSize(50);
        List<SysDictList> religionOptions = sysDictListService.findSysDiceListByModel(sysDictListVo);
        map.put("religionOptions",religionOptions);

        sysDictListVo.setSign("DICT_RYLB");
        sysDictListVo.setPage(1);
        sysDictListVo.setSize(20);
        List<SysDictList> personnelCategoryOptions = sysDictListService.findSysDiceListByModel(sysDictListVo);
        map.put("personnelCategoryOptions",personnelCategoryOptions);

        sysDictListVo.setSign("DICT_AJLB");
        sysDictListVo.setPage(1);
        sysDictListVo.setSize(1500);
        List<SysDictList> CaseOptions = sysDictListService.findSysDiceListByModel(sysDictListVo);
        map.put("CaseOptions",CaseOptions);

        sysDictListVo.setSign("DICT_RYCHYZHJ");
        sysDictListVo.setPage(1);
        sysDictListVo.setSize(200);
        List<SysDictList> certificatesOptions = sysDictListService.findSysDiceListByModel(sysDictListVo);
        map.put("certificatesOptions",certificatesOptions);

    }

}
