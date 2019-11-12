package com.zhouyi.business.component;

import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.model.provincecomprehensive.pojo.*;
import com.zhouyi.business.core.model.provincecomprehensive.utils.ProvinceZipUtils;
import com.zhouyi.business.core.service.*;
import com.zhouyi.business.core.model.provincecomprehensive.*;
import com.zhouyi.business.core.model.provincecomprehensive.utils.DataInfo;
import com.zhouyi.business.core.model.provincecomprehensive.utils.MIS;
import com.zhouyi.business.core.model.provincecomprehensive.utils.PlatformMappedMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: first
 * @Date: 下午10:34 2019/11/4
 * @Description: 数据上报组件
 **/
@Component
@Slf4j
public class DataReportComponent {

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

    @Value("${provinceComprehensive.generate.dir}")
    private String generateDir;
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
        UPLOADED(4, "已上传"),
        WAITING_RESOLVE(5,"正在等待解析");


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
    public MIS getMappedData(String personCode, String equipmentCode) throws Exception {

        PersonInfo personInfo = new PersonInfo();
        List<GDSInfo> gdsInfos = new ArrayList<>();
        List<BodySignInfo> bodySignInfos = new ArrayList<>();
        List<IrisInfo> irisInfos = new ArrayList<>();
        VoiceInfo voiceInfo = new VoiceInfo();

        final StringBuffer fileNameBuffer = new StringBuffer(generateDir);
        //1.生成人员信息
        StandardPerson standardPerson = ledenCollectPersonService.getStandardPerson(personCode);

        //修改之后代码使用的人员编号
        final String newPersonCode = standardPerson.getJzrybh() == null ? uploadProvinceComponent.generateDataNumber(standardPerson.getRyjcxxcjbh(), standardPerson.getCjdwdm()) : standardPerson.getJzrybh();
        fileNameBuffer.append(newPersonCode);
        fileNameBuffer.append(File.separator);

        String idCardFileName = newPersonCode + "_IDCARD.JPG";
        standardPerson.setIdCardPhoto(idCardFileName);
        standardPerson.setCjddm(equipmentCode);
        standardPerson.setZwbh(standardPerson.getRyjcxxcjbh().substring(1));
        standardPerson.setRyjcxxcjbh(newPersonCode);
        List data = null;
        //2.获取指纹信息
        data = ledenCollectFingerService.listFingerByConditions(personCode, "0000");
        //指纹
        if (data != null) {
            log.info("指纹数据条数为：" + data.size());
            data.forEach(x -> {
                String fingerPosition = ((LedenCollectFinger) x).getZwzwdm();
                ((LedenCollectFinger) x).setRyjcxxcjbh(newPersonCode);
                if (fingerPosition.startsWith("0")) {
                    //滚动指纹
                    fingerPosition = fingerPosition.substring(1);
                    fileNameBuffer.append("_RP_");
                } else {
                    //平面指纹
                    fileNameBuffer.append("_FP_");
                }
                fileNameBuffer.append(fingerPosition);
                fileNameBuffer.append(".Bmp");
                ProvinceZipUtils.generatePictureOrVoiceFile(fileNameBuffer.toString(), ((LedenCollectFinger) x).getZwTxsj());
                //清空fileBuffer
                flushFilePathBuffer(fileNameBuffer, newPersonCode);
            });
        }

        //3.获取掌纹信息
        data = ledenCollectPalmService.listPalmsByPersonCode(personCode);
        if (data != null) {
            log.info("掌纹数据:" + data.size());
            data.forEach(x -> {
                fileNameBuffer.append(newPersonCode);
                switch (((LedenCollectPalm) x).getZhwzhwdm()) {
                    case "31":
                        fileNameBuffer.append("_PM_R.bmp");
                        break;
                    case "32":
                        fileNameBuffer.append("_PM_L.bmp");
                        break;
                    case "33":
                        fileNameBuffer.append("_PW_R.bmp");
                        break;
                    case "34":
                        fileNameBuffer.append("_PW_L.bmp");
                        break;
                }
                ((LedenCollectPalm) x).setRyjcxxcjbh(newPersonCode);
                ProvinceZipUtils.generatePictureOrVoiceFile(fileNameBuffer.toString(), ((LedenCollectPalm) x).getZhwTxsj());
                flushFilePathBuffer(fileNameBuffer, newPersonCode);
            });

        }

        //4.获取人像信息
        data = ledenCollectPortraitService.listPortraitsByPersonCode(personCode);
        if (data != null) {
            log.info("人像数据:" + data.size());
            data.forEach(x -> {
                fileNameBuffer.append(newPersonCode);
                switch (((LedenCollectPortrait) x).getRxzplxdm()) {
                    case "1":
                        fileNameBuffer.append("_PH_F.JPG");
                        break;
                    case "2":
                        fileNameBuffer.append("_PH_L.JPG");
                        break;
                    case "4":
                        fileNameBuffer.append("_PH_R.JPG");
                        break;
                }
                ((LedenCollectPortrait) x).setRyjcxxcjbh(newPersonCode);
                ProvinceZipUtils.generatePictureOrVoiceFile(fileNameBuffer.toString(), ((LedenCollectPortrait) x).getRxtxsj());
                flushFilePathBuffer(fileNameBuffer, newPersonCode);
            });
        }


        //5.获取足记信息
        data = ledenCollectFootprintService.listFootPrintByPersonCode(personCode);
        if (data != null) {
            log.info("足记信息:" + data.size());
            data.forEach(x -> {
                fileNameBuffer.append(newPersonCode);
                switch (((LedenCollectFootprint) x).getZjbwdm()) {
                    case "0":
                        fileNameBuffer.append("_FT_L.jpg");
                        break;
                    case "1":
                        fileNameBuffer.append("_FT_R.jpg");
                        break;
                }
                ((LedenCollectFootprint) x).setRyjcxxcjbh(newPersonCode);
                ProvinceZipUtils.generatePictureOrVoiceFile(fileNameBuffer.toString(), ((LedenCollectFootprint) x).getZjsj());
                flushFilePathBuffer(fileNameBuffer, newPersonCode);
            });
        }
            //10.获取笔记数据
            data = ledenCollectHandWritingService.listHandWritingByPersonCode(personCode);
            if (data != null) {
                log.info("笔记:" + data.size());
                fileNameBuffer.append(newPersonCode);
                fileNameBuffer.append("_PEN.jpg");
                ProvinceZipUtils.generatePictureOrVoiceFile(fileNameBuffer.toString(), ((LedenCollectHandwriting) data.get(0)).getBjsj());
                flushFilePathBuffer(fileNameBuffer, newPersonCode);
            }


            //2.生成随身物品信息
            data = ledenCollectGoodsService.listGoodsByPersonCode(personCode);
            if (data != null) {
                StandardGoods standardGood = null;
                log.info("随身物品信息：" + data.size());
                for (int i = 0; i < data.size(); i++) {
                    standardGood = (StandardGoods) data.get(i);
                    standardGood.setRyjcxxcjbh(newPersonCode);
                    standardGood.setImagesInfos(new ArrayList<>());
                    for (int j = 0; j < standardGood.getgPhotos().size(); i++) {
                        LedenCollectGPhoto photo = (LedenCollectGPhoto) standardGood.getgPhotos().get(j);
                        fileNameBuffer.append(newPersonCode).append("_").append(i).append("_").append(j).append("_good.JPG");
                        ProvinceZipUtils.generatePictureOrVoiceFile(fileNameBuffer.toString().substring(fileNameBuffer.toString().indexOf("R")), photo.getDzwjnr());
                        standardGood.getImagesInfos().add(standardGood.new ImagesInfo(fileNameBuffer.toString()));
                        flushFilePathBuffer(fileNameBuffer, newPersonCode);
                    }
                    GDSInfo gdsInfo = new GDSInfo();
                    transferModelData(standardGood, gdsInfo);
                    gdsInfos.add(gdsInfo);
                }

            }

            //3.生成体貌特征信息
            data = ledenCollectSignService.listSignsByPersonCode(personCode);
            if (data != null) {
                StandardSign standardSign = null;
                log.info("体貌特征数量为:" + data.size());
                for (int i = 0; i < data.size(); i++) {
                    standardSign = (StandardSign) data.get(i);
                    standardPerson.setRyjcxxcjbh(newPersonCode);
                    standardSign.setRyjcxxcjbh(newPersonCode);
                    fileNameBuffer.append(newPersonCode).append("_").append(i).append("_sign.JPG");
                    ProvinceZipUtils.generatePictureOrVoiceFile(fileNameBuffer.toString(), standardSign.getTstzZp());
                    standardSign.setPhoto(fileNameBuffer.toString().substring(fileNameBuffer.toString().indexOf("R")));
                    BodySignInfo bodySignInfo = new BodySignInfo();
                    transferModelData(standardSign, bodySignInfo);
                    bodySignInfos.add(bodySignInfo);
                    flushFilePathBuffer(fileNameBuffer, newPersonCode);
                }
            }

            //虹膜信息
            data = ledenCollectIrisService.listIrisByPersonCode(personCode);
            if (data != null) {
                data.forEach(x -> {
                    fileNameBuffer.append(newPersonCode);
                    switch (((StandardIris) x).getHmywdm()) {
                        case "0":
                            fileNameBuffer.append("_IRIS_L.jpg");
                            break;
                        case "1":
                            fileNameBuffer.append("_IRIS_R.jpg");
                            break;
                    }
                    ((StandardIris) x).setRyjcxxcjbh(newPersonCode);
                    ProvinceZipUtils.generatePictureOrVoiceFile(fileNameBuffer.toString(), ((StandardIris) x).getHmsj());
                    IrisInfo irisInfo = new IrisInfo();
                    transferModelData(x, irisInfo);
                    irisInfos.add(irisInfo);
                    flushFilePathBuffer(fileNameBuffer, newPersonCode);
                });
            }

            //5.生成声纹
            StandardVoice standardVoByPersonCode = ledenCollectVoiceprintService.getStandardVoByPersonCode(personCode);
            //声纹
            if (standardVoByPersonCode != null) {
                log.info("存在声纹数据");
                fileNameBuffer.append(newPersonCode).append("_voc.wav");
                ProvinceZipUtils.generatePictureOrVoiceFile(fileNameBuffer.toString(), standardVoByPersonCode.getSwsj());
                transferModelData(standardVoByPersonCode, voiceInfo);
            }
            /**
             * 数据转换
             */
            transferModelData(standardPerson, personInfo);
            return new MIS(personInfo, gdsInfos, bodySignInfos, irisInfos, voiceInfo);

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
                if (standardFiledName != null) {
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
     *
     * @param target
     * @param provincePersonCode
     */
    private void replacePersonCode(Object target, String provincePersonCode) {
        if (target == null) {
            return;
        }
        if (target instanceof List) {
            //如果是集合
            List targetList = (List) target;
            for (Object o : targetList) {
                replacePersonCode(o, provincePersonCode);
            }
        } else {
            //则获取字段修改值
            Field personCodeField = null;
            try {
                personCodeField = target.getClass().getDeclaredField("ryjcxxcjbh");

            } catch (NoSuchFieldException e) {
                log.info(target.getClass().getSimpleName() + "没有人员编号字段,尝试从父类获取");
                try {
                    personCodeField = target.getClass().getSuperclass().getDeclaredField("ryjcxxcjbh");
                } catch (NoSuchFieldException ex) {
                    log.error(target.getClass().getSimpleName() + "父类并找到人员编号字段,封装人员编号失败");
                    ex.printStackTrace();
                }
            }
            try {
                personCodeField.setAccessible(true);
                personCodeField.set(target, provincePersonCode);
            } catch (IllegalAccessException e) {
                log.error(target.getClass().getSimpleName() + "的人员编号字段不可以访问,替换省编号失败");
                e.printStackTrace();
            }

        }
    }

    /**
     * 刷新文件路径buffer
     *
     * @param stringBuffer
     * @param dir
     */
    private void flushFilePathBuffer(StringBuffer stringBuffer, String dir) {
        stringBuffer.setLength(0);
        stringBuffer.append(generateDir);
        stringBuffer.append(dir);
        stringBuffer.append(File.separator);
    }


}
