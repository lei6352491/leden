package com.zhouyi.business.core.utils;

import com.google.common.base.CaseFormat;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.model.enums.AuthoirtyEnum;
import com.zhouyi.business.core.vo.headvo.PackageHeadVo;
import com.zhouyi.business.core.vo.xml.FingerXml;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.annotation.PostConstruct;
import javax.management.modelmbean.XMLParseException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: FingerXmlParse
 * @Description: 解析指纹xml入库
 * @date 2019/7/6 10:01
 * @Version 1.0
 **/
@Component
public class FingerXmlParse {


    private static SecurityUtil securityUtil;

    @Autowired
    public void setSecurityUtil(SecurityUtil securityUtil) {
        FingerXmlParse.securityUtil = securityUtil;
    }

    private static Logger logger = LoggerFactory.getLogger(FingerXmlParse.class);

    /**
     * 解析fptx文件
     *
     * @param path
     * @return
     */
    public static FingerAndPalm parseFptx(String path) throws XMLParseException, AuthenticationException {
        FingerAndPalm fingerAndPalm = new FingerAndPalm();
        SAXReader saxReader = new SAXReader();
        saxReader.setEncoding("GB2312");
        try {
            Document document = null;
            String path2 = path.substring(0, 3);
            if (path2.equalsIgnoreCase("ftp"))
                document = saxReader.read(path);
            else
                document = saxReader.read(new ByteArrayInputStream(path.getBytes("GBK")));

            Element packageElement = document.getRootElement(); //获取根节点


            //创建head对象
            PackageHeadVo packageHeadVo = new PackageHeadVo();
            //获取头部类型
            Class packageHeaderVoClass = PackageHeadVo.class;

            //获取packageHead节点
            Element packageHeadElement = packageElement.element("packageHead");
            Iterator iterator = packageHeadElement.elementIterator();
            while (iterator.hasNext()) {
                Element node = (Element) iterator.next();

                //将节点名称转为属性名称
                String node_properties = XmlParseUtil.firstCharToLowerCase(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, node.getName()));
                //获取头部字段
                Field targetField;
                try {
                    targetField = packageHeaderVoClass.getDeclaredField(node_properties);
                } catch (NoSuchFieldException e) {
                    //如果不能获取私有字段，则向父类获取
                    targetField = packageHeaderVoClass.getField(node_properties);
                }

                targetField.setAccessible(true);
                if (targetField.getType() != Date.class)
                    targetField.set(packageHeadVo, node.getStringValue());
                else
                    targetField.set(packageHeadVo, new SimpleDateFormat("yyyyMMddHHmmss").parse(node.getStringValue()));
            }


            //校验头部信息
//            boolean flag=securityUtil.repairpermissions(packageHeadVo,AuthoirtyEnum.FINGERPLAM);
//            if(!flag){
//                throw new AuthenticationException(ReturnCode.ERROR_1037);
//            }


            //解析指纹数据包
            Element fingerPrintPackageElement = (Element) packageElement.element("fingerprintPackage");


            //解析描述信息
            DescripttiveMsg desciptiveMsg = packageEntityData(fingerPrintPackageElement.element("descriptiveMsg"), DescripttiveMsg.class);

            //解析集合信息
            CollectInfoMsg collectInfoMsg = packageEntityData(fingerPrintPackageElement.element("collectInfoMsg"), CollectInfoMsg.class);

            //解析指纹信息
            List<FingerXml> fingers = packageDataEntityList(fingerPrintPackageElement.element("fingers"), FingerXml.class);

            //解析掌纹信息
            List<LedenCollectPalm> palmss = packageDataEntityList(fingerPrintPackageElement.element("palms"), LedenCollectPalm.class);

            //解析四指信息
            List<LedenCollectFourfinger> fourprintMsgs = packageDataEntityList(fingerPrintPackageElement.element("fourprints"), LedenCollectFourfinger.class);

            //解析全掌纹
            List<LedenCollectFullpalm> fullplams = packageDataEntityList(fingerPrintPackageElement.element("fullpalms"), LedenCollectFullpalm.class);

            //解析指节纹
            List<LedenCollectPhalange> phalanges = packageDataEntityList(fingerPrintPackageElement.element("knuckleprints"), LedenCollectPhalange.class);


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddHHMMss");
            //将需要的信息填入指纹
            String rybh = desciptiveMsg.getYsxtAsjxgrybh();//人员编号
            String jzrybh = desciptiveMsg.getJzrybh(); //警综编号
            String nysj = collectInfoMsg.getNysj(); //采集时间
            for (LedenCollectFinger x : fingers) {
                x.setRyjcxxcjbh(rybh);
                x.setCreateUserId(jzrybh);
                x.setCreateDatetime(dateFormat.parse(nysj));
            }

            fingerAndPalm.setFingers(fingers);
            fingerAndPalm.setFourfingers(fourprintMsgs);
            return fingerAndPalm;

        } catch (DocumentException e) {
            e.printStackTrace();
            throw new XmlParseException("文档解析失败:" + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new XMLParseException("文档解析失败：节点不匹配：" + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new XmlParseException("日期格式转换错误：" + e.getMessage());
        } catch (AuthenticationException e) {
            throw new AuthenticationException(e.getReturnCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmlParseException("未知错误:" + e.getMessage());
        }
        return null;
    }


    @Data
    public class CollectInfoMsg {
        private String zwbdxtlxms;
        private String nydwGajgjgdm;
        private String nydwGajgmc;
        private String nyryXm;
        private String nyryGmsfhm;
        private String nyryLxdh;
        private String nysj;


        public FingerXmlParse getOutClass() {
            return FingerXmlParse.this;
        }

    }


    @Data
    public class DescripttiveMsg {
        private String ysxtAsjxgrybh;
        private String jzrybh;
        private String asjxgrybh;
        private String zzhwkbh;
        private String collectingreasonset;
        private String xm;
        private String bmch;
        private String xbdm;
        private String csrq;
        private String gjdm;
        private String mzdm;
        private String cyzjdm;
        private String zjhm;
        private String hjdzXzqhdm;
        private String hjdzDzmc;
        private String xzzXzqhdm;
        private String xzzDzmc;
        private String bz;

        @Data
        class CollectingReasonSet {
            private String cjxxyydm;
        }

    }

    /**
     * 将节点信息封装进对应的实体
     *
     * @param element
     * @param targetClass
     */
    private static <T> T packageEntityData(Element element, Class<T> targetClass) throws NoSuchFieldException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {

        if (element == null) {
            logger.info("采集数据中缺少数据节点：" + targetClass.getSimpleName());
            return null;
        } else if (element.elements().size() == 0) {
            logger.info(targetClass.getSimpleName() + "数据节点中子节点为0");
        }
        T resultObject = null;
        if (targetClass.isMemberClass()) {
            System.out.println("该类的构造方法有" + targetClass.getConstructors().length);
            System.out.println("查看构造方法:" + targetClass.getConstructors()[0]);
            System.out.println("使用该构造初始化一个内部类:" + targetClass.getConstructors()[0]);
            if (targetClass.getDeclaringClass() != null) {
                //实例化外部对象
                Class<?> outClass = targetClass.getDeclaringClass();
                Constructor<?>[] constructors = targetClass.getConstructors();
                resultObject = (T) constructors[0].newInstance(outClass.newInstance());
            }
        } else {
            //如果不是内部类则直接实例化
            resultObject = targetClass.newInstance();
        }
        //迭代节点
        Iterator iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element node = (Element) iterator.next();
            //将节点名称转为属性名称
            String node_properties = XmlParseUtil.firstCharToLowerCase(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, node.getName()));
            Field field = null;
            try {
                field = targetClass.getDeclaredField(node_properties);
            } catch (NoSuchFieldException e) {
                //如果没有该字段则记录跳过
                logger.info("系统不存在字段:" + node_properties);
                continue;
            }

            field.setAccessible(true);
            if (field.getType() == String.class) {
                //直接封装数据
                logger.info("字段:" + field.getName() + ":" + node.getStringValue());
                field.set(resultObject, node.getStringValue());
            } else if (field.getType() == byte[].class) {
                //如果是图片数据
                field.set(resultObject, node.getStringValue().getBytes());
            } else {
                //如果是其他类型：如时间、对象
                if (field.getType() == CollectInfoMsg.class) {
                    //生成一个CollectMsg对象
                    DescripttiveMsg.CollectingReasonSet collectInfoMsg = new FingerXmlParse().new DescripttiveMsg().new CollectingReasonSet();
                    collectInfoMsg.setCjxxyydm(node.element("cjxxyydm").getStringValue());
                }
            }
        }

        return resultObject;
    }


    /**
     * 封装节点数据集合
     *
     * @param element
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> packageDataEntityList(Element element, Class<T> targetClass) throws IllegalAccessException, NoSuchFieldException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        if (element == null) {
            logger.info("采集数据中缺少数据节点：" + targetClass.getSimpleName());
            return null;
        } else if (element.elements().size() == 0) {
            logger.info(targetClass.getSimpleName() + "数据节点中子节点为0");
        }
        Iterator iterator = element.elementIterator();
        List<T> targetDataList = new ArrayList<>();
        while (iterator.hasNext()) {
            Element childElement = (Element) iterator.next();
            T t = packageEntityData(childElement, targetClass);
            targetDataList.add(t);
        }
        return targetDataList;
    }

}