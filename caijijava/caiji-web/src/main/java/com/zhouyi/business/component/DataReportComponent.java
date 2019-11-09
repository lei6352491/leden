package com.zhouyi.business.component;

import com.zhouyi.business.config.CronConfiguration;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.*;
import com.zhouyi.business.core.service.*;
import com.zhouyi.business.core.model.provincecomprehensive.*;
import com.zhouyi.business.core.model.provincecomprehensive.utils.DataInfo;
import com.zhouyi.business.core.model.provincecomprehensive.utils.MIS;
import com.zhouyi.business.core.model.provincecomprehensive.utils.PlatformMappedMap;
import com.zhouyi.business.runnable.UploadRunnable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: first
 * @Date: 下午10:34 2019/11/4
 * @Description: 数据上报组件
 **/
@Component
@Slf4j
public class DataReportComponent {

    @Autowired
    private LedenUploadLogService ledenUploadLogService;


    @Autowired
    private LedenCollectFingerService ledenCollectFingerService;
    @Autowired
    private LedenCollectIrisService ledenCollectIrisService;
    @Autowired
    private LedenCollectVoiceprintService ledenCollectVoiceprintService;
    @Autowired
    private LedenCollectPersonService ledenCollectPersonService;
    @Autowired
    private LedenCollectGoodsService ledenCollectGoodsService;
    @Autowired
    private LedenCollectPalmService ledenCollectPalmService;
    @Autowired
    private LedenCollectPortraitService ledenCollectPortraitService;
    @Autowired
    private LedenCollectFootprintService ledenCollectFootprintService;
    @Autowired
    private LedenCollectSignService ledenCollectSignService;
    @Autowired
    private LedenCollectHandWritingService ledenCollectHandWritingService;
    @Autowired
    private UploadProvinceComponent uploadProvinceComponent;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;





    @Autowired
    private UploadRunnable uploadRunnable;
    /**
     * 从数据库读取等待上报的数据
     *
     * @return 待上报的人员编号
     */



    /**
     * @Author: first
     * @Date: 下午9:15 2019/11/6
     * @Description: 上传状态枚举
     **/
    @AllArgsConstructor
    public enum UPLOAD_STATUS {
        NO_UPLOAD(0, "未上传"),
        PACKING(1, "打包中"),
        UPLOAD_LOSE(2, "上传失败"),
        UPLOADING(3, "正在上传"),
        UPLOADED(4, "已上传");


        private int value;
        private String name;


        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    /**
     * 获取映射的数据
     *
     * @return
     */
    public DataInfoMis getMappedData(String personCode,String equipmentCode) throws Exception {
        DataInfoMis dataInfoMis = new DataInfoMis();

        //2.获取指纹信息
        List<LedenCollectFinger> ledenCollectFingers = ledenCollectFingerService.listFingerByConditions(personCode, "0000");
        //3.获取掌纹信息
        List<LedenCollectPalm> ledenCollectPalms = ledenCollectPalmService.listPalmsByPersonCode(personCode);
        //4.获取人像信息
        List<LedenCollectPortrait> ledenCollectPortraits = ledenCollectPortraitService.listPortraitsByPersonCode(personCode);
        //5.获取足记信息
        List<LedenCollectFootprint> ledenCollectFootprints = ledenCollectFootprintService.listFootPrintByPersonCode(personCode);

        //10.获取笔记数据
        List<LedenCollectHandwriting> ledenCollectHandwritings = ledenCollectHandWritingService.listHandWritingByPersonCode(personCode);



        try {

            PersonInfo personInfo=new PersonInfo();
            List<GDSInfo> gdsInfos = new ArrayList<>();
            List<BodySignInfo> bodySignInfos = new ArrayList<>();
            List<IrisInfo> irisInfos = new ArrayList<>();
            VoiceInfo voiceInfo = new VoiceInfo();

            //1.生成人员信息
            StandardPerson standardPerson = ledenCollectPersonService.getStandardPerson(personCode);
            //2.生成随身物品信息
            List<StandardGoods> collectGoods = ledenCollectGoodsService.listGoodsByPersonCode(personCode);
            //3.生成体貌特征信息
            List<StandardSign> standardSigns = ledenCollectSignService.listSignsByPersonCode(personCode);
            //4.生成虹膜
            List<StandardIris> collectIrises = ledenCollectIrisService.listIrisByPersonCode(personCode);
            //5.生成声纹
            StandardVoice standardVoByPersonCode = ledenCollectVoiceprintService.getStandardVoByPersonCode(personCode);

            //修改之后代码使用的人员编号
            final String newPersonCode = standardPerson.getJzrybh()==null?uploadProvinceComponent.generateDataNumber(standardPerson.getRyjcxxcjbh(),standardPerson.getCjdwdm()):standardPerson.getJzrybh();
            List<?> standardObjects= Arrays.asList(standardPerson,collectGoods,standardSigns,collectIrises,standardVoByPersonCode);
            standardObjects.forEach(x->replacePersonCode(x,newPersonCode));




            List<DataInfo> dataInfos=new ArrayList<>();

            //封装人员信息
            String idCardFileName=newPersonCode+"_IDCARD.JPG";
            standardPerson.setIdCardPhoto(idCardFileName);
            standardPerson.setCjddm(equipmentCode);
            DataInfo personDataInfo=new DataInfo(idCardFileName,standardPerson.getJdxp(),"JPG");
            dataInfos.add(personDataInfo);

            //封装随身物品信息
            if(collectGoods!=null){
               for (int i=0;i<collectGoods.size();i++){
                   StandardGoods standardGood=collectGoods.get(i);
                   standardGood.setImagesInfos(new ArrayList<>());
                   for (int j=0;j<standardGood.getgPhotos().size();i++){
                       LedenCollectGPhoto photo = (LedenCollectGPhoto)standardGood.getgPhotos().get(j);
                       String fileName=standardGood.getRyjcxxcjbh()+"_"+"i_"+j+"_good.JPG";
                       DataInfo dataInfo=new DataInfo(fileName,photo.getDzwjnr(),"JPG");
                       dataInfos.add(dataInfo);
                       standardGood.getImagesInfos().add(standardGood.new ImagesInfo(fileName));
                   }
                   GDSInfo gdsInfo=new GDSInfo();
                   transferModelData(standardGood,gdsInfo);
                   gdsInfos.add(gdsInfo);
               }

            }



            //指纹
            if (ledenCollectFingers != null) {
                ledenCollectFingers.forEach(x -> {
                    String fingerPosition = x.getZwzwdm();
                    if (fingerPosition.startsWith("0")) {
                        //滚动指纹
                        fingerPosition = fingerPosition.substring(1);
                        dataInfos.add(new DataInfo(newPersonCode + "_RP_" + fingerPosition, x.getZwTxsj(), ".Bmp"));
                    } else {
                        //平面指纹
                        dataInfos.add(new DataInfo(newPersonCode + "_FP_" + fingerPosition, x.getZwTxsj(), ".Bmp"));
                    }
                });
            }

            //掌纹
            if (ledenCollectPalms != null) {
                ledenCollectPalms.forEach(x -> {
                    StringBuffer fileName = new StringBuffer(newPersonCode);
                    switch (x.getZhwzhwdm()) {
                        case "31":
                            fileName.append("_PM_R");
                            break;
                        case "32":
                            fileName.append("_PM_L");
                            break;
                        case "33":
                            fileName.append("_PW_R");
                            break;
                        case "34":
                            fileName.append("_PW_");
                            break;
                    }
                    dataInfos.add(new DataInfo(fileName.toString(), x.getZhwTxsj(), ".bmp"));
                });
            }


            //三面人像
            if (ledenCollectPortraits != null) {
                ledenCollectPortraits.forEach(x -> {
                    StringBuffer fileName = new StringBuffer(newPersonCode);
                    switch (x.getRxzplxdm()) {
                        case "1":
                            fileName.append("_PH_F");
                            break;
                        case "2":
                            fileName.append("_PH_L");
                            break;
                        case "4":
                            fileName.append("_PH_R");
                            break;
                    }

                    dataInfos.add(new DataInfo(fileName.toString(), x.getRxtxsj(), ".JPG"));
                });
            }


            //足迹
            if (ledenCollectFootprints != null) {
                ledenCollectFootprints.forEach(x -> {
                    StringBuffer fileName = new StringBuffer(newPersonCode);
                    switch (x.getZjbwdm()) {
                        case "0":
                            fileName.append("_FT_L");
                            break;
                        case "1":
                            fileName.append("_FT_R");
                            break;
                    }
                    dataInfos.add(new DataInfo(fileName.toString(), x.getZjsj(), ".jpg"));
                });
            }

            //体表标记
            if(standardSigns!=null){
                for (int i=0;i<standardSigns.size();i++){
                    StandardSign standardSign=standardSigns.get(i);
                    String fileName=standardSign.getRyjcxxcjbh()+"_"+i+"_sign.JPG";
                    DataInfo dataInfo=new DataInfo(fileName,standardSign.getTstzZp(),"JPG");
                    dataInfos.add(dataInfo);
                    standardSign.setPhoto(fileName);

                    BodySignInfo bodySignInfo=new BodySignInfo();
                    transferModelData(standardSign,bodySignInfo);
                    bodySignInfos.add(bodySignInfo);
                }
            }

            //虹膜
            if (collectIrises != null) {
                collectIrises.forEach(x -> {
                    StringBuffer fileName = new StringBuffer(newPersonCode);
                    switch (x.getHmywdm()) {
                        case "0":
                            fileName.append("_IRIS_L");
                            break;
                        case "1":
                            fileName.append("_IRIS_R");
                            break;
                    }
                    dataInfos.add(new DataInfo(fileName.toString(), x.getHmsj(), ".jpg"));
                    IrisInfo irisInfo=new IrisInfo();
                    transferModelData(x,irisInfo);
                    irisInfos.add(irisInfo);
                });
            }



            //声纹
            if (standardVoByPersonCode != null) {
                String fileName = personCode + "_voc";
                dataInfos.add(new DataInfo(fileName, standardVoByPersonCode.getYpsj(), ".wav"));
                transferModelData(standardVoByPersonCode,voiceInfo);
            }



            //笔记
            if (ledenCollectHandwritings != null && ledenCollectHandwritings.size() > 0) {
                String fileName = personCode + "_PEN";
                dataInfos.add(new DataInfo(fileName, ledenCollectHandwritings.get(0).getBjsj(), ".jpg"));
            }

            //将数据封装进DatInfoMsi
            dataInfoMis.setDataInfos(dataInfos);
            dataInfoMis.setMis(new MIS(personInfo, gdsInfos, bodySignInfos, irisInfos, voiceInfo));
            return dataInfoMis;
        } catch (Exception e) {
            throw new Exception("封装数据失败" + e.getMessage());
        }
    }


    /**
     * 将模型数据进行拷贝
     *
     * @param origin
     * @param target
     */
    public void transferModelData(Object origin, Object target) {
        Field[] targetFields = target.getClass().getDeclaredFields();

        for (Field targetField : targetFields) {
            targetField.setAccessible(true);
            //获取标采类
            Class<?> standardClass = origin.getClass();

            //获取标采对应的字段名
            String standardFiledName = PlatformMappedMap.fieldNameMapping.get(targetField.getName());
            //从标采中获取值填入省综对象
            try {
                Field standardFieldEach = null;
                if(standardFiledName!=null) {
                    try {
                        standardFieldEach = standardClass.getDeclaredField(standardFiledName);
                    } catch (NoSuchFieldException e) {
                        //如果获取失败则向父类获取
                        standardFieldEach = standardClass.getSuperclass().getDeclaredField(standardFiledName);
                    }
                    standardFieldEach.setAccessible(true);
                    targetField.set(target, standardFieldEach.get(origin));
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                log.error("模型数据转换字段不匹配:" + e.getMessage());
            } catch (IllegalAccessException e) {
                log.error("模型数据上报字段不可访问：" + e.getMessage());
                e.printStackTrace();
            }
        }


    }





    @Data
    public class DataInfoMis {
        private List<DataInfo> dataInfos;
        private MIS mis;
    }


    /**
     * 替换为省的人员编号
     * @param target
     * @param provincePersonCode
     */
    private void replacePersonCode(Object target,String provincePersonCode){
        if(target==null){
            return;
        }
       if(target instanceof List){
          //如果是集合
           List targetList=(List)target;
           for (Object o : targetList) {
               replacePersonCode(o,provincePersonCode);
           }
       }else{
          //则获取字段修改值
           Field personCodeField=null;
           try {
               personCodeField = target.getClass().getDeclaredField("ryjcxxcjbh");

           } catch (NoSuchFieldException e) {
               log.info(target.getClass().getSimpleName()+"没有人员编号字段,尝试从父类获取");
               try {
                   personCodeField=target.getClass().getSuperclass().getDeclaredField("ryjcxxcjbh") ;
               } catch (NoSuchFieldException ex) {
                   log.error(target.getClass().getSimpleName()+"父类并找到人员编号字段,封装人员编号失败");
                   ex.printStackTrace();
               }
           }
           try {
               personCodeField.setAccessible(true);
               personCodeField.set(target,provincePersonCode);
           } catch (IllegalAccessException e) {
               log.error(target.getClass().getSimpleName()+"的人员编号字段不可以访问,替换省编号失败");
               e.printStackTrace();
           }

       }
    }

}
