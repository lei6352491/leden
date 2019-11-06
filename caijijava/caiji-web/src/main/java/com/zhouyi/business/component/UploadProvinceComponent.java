package com.zhouyi.business.component;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.config.ProvinceFtpConfig;
import com.zhouyi.business.core.dao.LedenCollectPersonMapper;
import com.zhouyi.business.core.model.LedenCollectPerson;
import com.zhouyi.business.core.utils.HttpUtil;
import com.zhouyi.business.core.vo.ResponseVo;
import com.zhouyi.business.model.provincecomprehensive.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: first
 * @Date: 上午6:40 2019/11/6
 * @Description: 上报省综组件
**/
@Component
public class UploadProvinceComponent {

    @Autowired
    private ProvinceFtpConfig provinceFtpConfig;

    @Value("${provinceComprehensive.interfaces.dataNumber}")
    private String generatePersonCode;

    @Autowired
    private LedenCollectPersonMapper ledenCollectPersonMapper;
    /**
     * 向省综获取人员编号
     * @param personCode 用户编号
     * @param unitCode 单位代码
     */
    private String packingPersonCode(String personCode,String unitCode) throws Exception {
        //获取数据库的警综人员编号（省综的人员编号）
        List<LedenCollectPerson> persons = ledenCollectPersonMapper.getLedenCollectPersonByConditions(new HashMap<String,Object>(){{put("ryjcxxcjbh",personCode);}});
        if(persons==null||persons.size()==0){
            throw new Exception("没有"+personCode+"人员的信息");
        }
        if("".equals(persons.get(0).getJzrybh())){
           //如果没有则获取调用省综接口
            ResponseVo response = HttpUtil.sendPostByJson(provinceFtpConfig.getServerAddress() + generatePersonCode, new HashMap<String, String>(1) {{
                put("unitCode", unitCode);
            }});

            if(response.isOk()){
               //如果为调用成功则获取人员编号
                String data = response.getData();
                ResponseData responseData = JSON.parseObject(data, ResponseData.class);
                if(response.getStatus()==1){
                    //生成的人员编号
                    String generatedPersonCode=response.getData();
                    //将人员编号存入数据库
                    LedenCollectPerson waitingSavePerson=new LedenCollectPerson(personCode,generatedPersonCode);
                    ledenCollectPersonMapper.insertSelective(waitingSavePerson);
                    return generatedPersonCode;
                }else{
                    throw new Exception("生成编号出错");
                }

            }
        }
        //如果不为空则表示已经已经调用过接口了
        return persons.get(0).getJzrybh();
    }
}
