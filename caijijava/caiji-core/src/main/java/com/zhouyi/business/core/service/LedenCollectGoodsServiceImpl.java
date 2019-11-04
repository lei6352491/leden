package com.zhouyi.business.core.service;


import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.*;
import com.zhouyi.business.core.exception.AuthenticationException;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.exception.XmlParseException;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.XmlParseUtil;
import com.zhouyi.business.core.vo.LedenCollectGoodsVo;
import com.zhouyi.business.core.vo.xml.LedenCollectGPhotoXml;
import com.zhouyi.business.core.vo.xml.LedenCollectGoodsXml;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author 李秸康
 * @ClassNmae: LedenCollectGoodsServiceImpl
 * @Description: TODO
 * @date 2019/7/5 8:26
 * @Version 1.0
 **/
@Service
public class LedenCollectGoodsServiceImpl implements LedenCollectGoodsService {

    @Autowired
    private LedenCollectGoodsMapper ledenCollectGoodsMapper;
    @Autowired
    private LedenCollectGPhotoMapper ledenCollectGPhotoMapper;
    @Autowired
    private LedenCollectGVehicleMapper ledenCollectGVehicleMapper;
    @Autowired
    private LedenCollectGCardMapper ledenCollectGCardMapper;
    @Autowired
    private LedenCollectGMobileMapper ledenCollectGMobileMapper;
    @Autowired
    private LedenCollectGBicycleMapper ledenCollectGBicycleMapper;
    @Autowired
    private LedenCollectGCurrencyMapper ledenCollectGCurrencyMapper;


    /**
     * 将xml中的随身物品数据插入DB
     *
     * @param path
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean inputGoodsByXml(String path) throws AuthenticationException, XmlParseException {


        LedenCollectGoodsVo goodsVo = (LedenCollectGoodsVo) XmlParseUtil.parseXml(path, LedenCollectGoodsVo.class);
        //获取其中的随声物品数据列表
        List<LedenCollectGoodsXml> goodsXmls = goodsVo.getData();

        //循环复制数据
        for (LedenCollectGoodsXml goodsxml :
                goodsXmls) {
            //创建goods对象/mobile对象/vehicles对象/bicycles对象/cards对象/currencies对象/photo对象
            LedenCollectGoods ledenCollectGoods = new LedenCollectGoods();
            LedenCollectGMobile ledenCollectGMobile = new LedenCollectGMobile();
            LedenCollectGVehicle ledenCollectGVehicle = new LedenCollectGVehicle();
            LedenCollectGBicycle ledenCollectGBicycle = new LedenCollectGBicycle();
            LedenCollectGCard ledenCollectGCard = new LedenCollectGCard();
            LedenCollectGCurrency ledenCollectGCurrency = new LedenCollectGCurrency();
            List<LedenCollectGPhoto> photos1 = new ArrayList<>();
            ledenCollectGoods.setWpbh(UUID.randomUUID().toString().replace("-",""));
            List dataObjects = Arrays.asList(
                    ledenCollectGoods,
                    ledenCollectGVehicle,
                    ledenCollectGMobile,
                    ledenCollectGBicycle,
                    ledenCollectGCard,
                    ledenCollectGCurrency
            );

            //将属性值copy入对象中
            XmlParseUtil.copyProperties(goodsxml, dataObjects);

            //获取每个物品的图片信息
            List<LedenCollectGPhotoXml> ledenCollectGPhotoXmls = goodsxml.getDataSon();
            for (LedenCollectGPhotoXml ledenCollectGPhotoXml :
                    ledenCollectGPhotoXmls) {
                //每次构建一个信息图片信息对象
                LedenCollectGPhoto ledenCollectGPhoto = new LedenCollectGPhoto();
                BeanUtils.copyProperties(ledenCollectGPhotoXml, ledenCollectGPhoto);
                ledenCollectGPhoto.setXxbh(UUID.randomUUID().toString().replace("-",""));
                photos1.add(ledenCollectGPhoto);
            }
            //进行新增操作
            //新增前删除原来信息
            ledenCollectGoods.setCreateUserId(goodsVo.head.getUSER_CODE());
            ledenCollectGoods.setCreateDatetime(new Date());
            ledenCollectGoodsMapper.deleteGoodsByPersonId(ledenCollectGoods.getRyjcxxcjbh());
            ledenCollectGoodsMapper.insertSelective(ledenCollectGoods);

            ledenCollectGMobileMapper.deleteGMobileByPersonId(ledenCollectGMobile.getWpbh());
            ledenCollectGMobileMapper.insertSelective(ledenCollectGMobile);

            ledenCollectGVehicleMapper.deleteVehicleByPersonId(ledenCollectGVehicle.getWpbh());
            ledenCollectGVehicleMapper.insertSelective(ledenCollectGVehicle);

            ledenCollectGBicycleMapper.deleteBicycleByWpbh(ledenCollectGBicycle.getWpbh());
            ledenCollectGBicycleMapper.insertSelective(ledenCollectGBicycle);

            ledenCollectGCardMapper.deleteCardByWpbh(ledenCollectGCard.getWpbh());
            ledenCollectGCardMapper.insertSelective(ledenCollectGCard);

            if(photos1!=null && photos1.size()>0){
                ledenCollectGPhotoMapper.deletePhotoByWpbh(photos1.get(0).getWpbh());
                ledenCollectGPhotoMapper.insertPhotos(photos1);
            }
        }
        return true;
    }

    @Override
    public PageData<LedenCollectGoods> goods(String personId, String pNo, String pSize) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("ryjcxxcjbh", personId);
        if (pNo != null && pSize != null)
            MapUtils.setPageConditions(Integer.parseInt(pNo), Integer.parseInt(pSize), conditions);

        List<LedenCollectGoods> ledenCollectGoods = ledenCollectGoodsMapper.listDataByConditions(conditions);
        encodeBase64(ledenCollectGoods);

        int total = ledenCollectGoodsMapper.getDataCountByConditions(conditions);
        PageData<LedenCollectGoods> pageData=new PageData(ledenCollectGoods,total,Integer.valueOf(pSize));
        return pageData;
    }


    /**
     * 将图片base64转码
     * @param ledenCollectGoods
     */
    private void encodeBase64(List<LedenCollectGoods> ledenCollectGoods) {
        //定义解码器
        BASE64Encoder base64Encoder = new BASE64Encoder();
        ledenCollectGoods.forEach(x -> {
            packingObjectTypeModel(x);
            //将所有图片进行base64转码
            List<LedenCollectGPhoto> photos = x.getgPhotos();
            photos.forEach(y -> {
                y.setBase64Img(String.format("data:image/png;base64,%s", base64Encoder.encode(y.getDzwjnr())));
                y.setDzwjnr(null);
            });
        });
    }

    @Override
    public LedenCollectGoods good(String wpbh) {

        Map<String, Object> conditions = new HashMap<>();
        conditions.put("wpbh", wpbh);
        List<LedenCollectGoods> list = ledenCollectGoodsMapper.listDataByConditions(conditions);
        list.stream().forEach((s)->{
            List<LedenCollectGPhoto> photosList = s.getgPhotos();
            photosList.stream().forEach((t)->{
                t.setBase64Img(new String(t.getDzwjnr()));
                t.setDzwjnr(null);
            });
        });
        return list.get(0);
    }


    /**
     * 封装物品类别模型
     *
     * @param x
     */
    private void packingObjectTypeModel(LedenCollectGoods x) {
        //查询物品类别
        switch (x.getObjectType().intValue()) {
            case 1:
                //通讯工具
                kindsModel(x, ledenCollectGMobileMapper);
                break;
            case 2:
                //交通工具
                kindsModel(x, ledenCollectGVehicleMapper);
                break;
            case 3:
                //电动自行车
                kindsModel(x, ledenCollectGBicycleMapper);
                break;
            case 4:
                //卡类
                kindsModel(x, ledenCollectGCardMapper);
                break;
            case 5:
                //货币
                kindsModel(x, ledenCollectGCurrencyMapper);
                break;
            default:
                //没有此类型
                break;

        }
    }


    /**
     * 根据不同类别代码封装不同对象
     *
     * @param x
     */
    private void kindsModel(LedenCollectGoods x, Object serviceObject) {
        //通过反射调用方法获取返回值
        try {
            Method serviceMethods = serviceObject.getClass().getDeclaredMethod("listDataByConditions", Map.class);
            serviceMethods.setAccessible(true);
            //设置参数
            Map<String, Object> conditions = new HashMap<>();
            conditions.put("wpbh", x.getWpbh());
            Object reuslt = serviceMethods.invoke(serviceObject, conditions);
            x.setObjectTypeModel(reuslt);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new BusinessException(ReturnCode.ERROR_1039);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new BusinessException(ReturnCode.ERROR_1040);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new BusinessException(ReturnCode.ERROR_1041);
        }


    }


}
