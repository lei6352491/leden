package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenCollectIrisMapper;
import com.zhouyi.business.core.model.LedenCollectIris;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.StandardIris;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.LedenCollectIrisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LedenCollectIrisServiceImpl
        extends BaseServiceImpl<LedenCollectIris, LedenCollectIrisVo>
        implements LedenCollectIrisService{

    @Autowired
    private LedenCollectIrisMapper ledenCollectIrisMapper;

    @Override
    @Transactional
    public Response<Object> saveMapToRepository(List list,String userUnitCode,String ryjcxxcjbh) {
        if (list == null){
            return ResponseUtil.returnError(ReturnCode.ERROR_14);
        }

        //删除原有虹膜信息
        if(list!=null&&list.size()>0) {
            String cjrybh = ((LedenCollectIris) list.get(0)).getRyjcxxcjbh();
            if (cjrybh != null){
                ledenCollectIrisMapper.deleteDataByPersonCode(cjrybh);
            }else {
                ledenCollectIrisMapper.deleteDataByPersonCode(ryjcxxcjbh);
            }
        }

        for (Object object : list){
            LedenCollectIris ledenCollectIris = (LedenCollectIris)object;
            ledenCollectIris.setPkId(UUID.randomUUID().toString().replace("-",""));
            ledenCollectIris.setRyjcxxcjbh(ryjcxxcjbh);
            this.saveData(ledenCollectIris);
        }
        return ResponseUtil.getResponseInfo(true);
    }

    /**
     * 根据人员编号查询虹膜信息
     * */
    @Override
    public Response<List<LedenCollectIris>> selectIrisByPersonCode(String id) {
        List<LedenCollectIris> list = ledenCollectIrisMapper.selectDataByPersonCode(id);
        list.stream().forEach((s)->{
            s.setHmzp(new String(s.getHmsj()));
            s.setHmsj(null);
        });
        //加密图面信息
//        if (list != null && list.size() > 0){
////            list.stream().forEach(s ->{
////                if ("0".equals(s.getQzcjbz())){
//////                    BASE64Encoder base64Encoder = new BASE64Encoder();
////                    if ("0".equals(s.getHmywdm())){
////                        String encode = new String(s.getHmsj());
////                        StringBuilder stringBuilder = new StringBuilder();
////                        stringBuilder.append("data:image/png;base64,");
////                        stringBuilder.append(encode);
////                        s.setHmzp(stringBuilder.toString());
////                        s.setHmsj(null);
////                    finge}
////                    else if ("1".equals(s.getHmywdm())){
////                        String encode = new String(s.getHmsj());
////                        StringBuilder stringBuilder = new StringBuilder();
////                        stringBuilder.append("data:image/png;base64,");
////                        stringBuilder.append(encode);
////                        s.setHmzp(stringBuilder.toString());
////                        s.setHmsj(null);
////                    }
////                    else {
////                        String encode = new String(s.getHmsj());
////                        StringBuilder stringBuilder = new StringBuilder();
////                        stringBuilder.append("data:image/png;base64,");
////                        stringBuilder.append(encode);
////                        s.setHmzp(stringBuilder.toString());
////                        s.setHmsj(null);
////                    }
////                }
//            });
//            return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
//        }
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }

    @Override
    public List<StandardIris> listIrisByPersonCode(String personCode) {
        return (List)ledenCollectIrisMapper.listDataByConditions(new HashMap<String,Object>(1){{put("personCode",personCode);}});
    }

    //获取主键
    /*private String getPrimaryKey(String userUnitCode,Long serialNumber){
        StringBuffer stringBuffer = new StringBuffer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String dateString = dateFormat.format(new Date());
        stringBuffer.append("HM").append(userUnitCode).append(dateString).append(String.format("%06d", serialNumber));
        return stringBuffer.toString();
    }*/
}
