package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.CombineInfo;

import java.util.Date;
import java.util.List;

public interface NameService {

    /**
     * 保存外部采集的名字
     * @param nameList 名字列表
     * @return
     */
    int addExternalName(List<Object> nameList);

    /**
     * 姓氏列表
     * @param surname 中文姓氏
     * @param yinjie 音节 (黄：huang2)
     * @return
     */
    List<Object> surnameList(String surname, String yinjie);

    /**
     * 起名用字组合列表
     * @param pendingCombineId 待审用字组合编号
     * @param firstChar 指定第一个汉字
     * @param lastChar 指定第二个汉字
     * @param recordCount 返回记录条数
     * @return
     */
    List<Object> charCombineList(Long pendingCombineId, String firstChar, String lastChar, Integer recordCount);

    /**
     * 可分配待审用字组合
     * @param userId 用户编号
     * @param recordCount 返回记录条数
     * @return
     */
    List<Object> distributableCombineList(Long userId, Integer recordCount);

    /**
     * 当前已分配但未处理的用字组合
     * @param userId 用户编号
     * @return
     */
    List<Object> pendingCombineList(Long userId);

    /**
     * 审核员已审核的用字组合
     * @param userId 用户编号
     * @param combineInfo 起名用字信息
     * @param pStart 记录起始位置
     * @param pSize 记录数
     * @return
     */
    List<Object> combineReviewedList(Long userId, CombineInfo combineInfo,Integer pStart,Integer pSize);

    /**
     * 审核员已审核的用字组合数量
     * @param userId 用户编号
     * @param combineInfo 起名用字信息
     * @return
     */
    int combineReviewedCount(Long userId, CombineInfo combineInfo);

    /**
     * 分配用字组合给初审员
     * @param userId 用户编号
     * @param combineList 用字组合列表
     * @return
     */
    int allocationCombine(Long userId, List<Object> combineList);

    /**
     * 用字组合审核通过
     * @param combineInfo 用字组合对象
     * @param userId 用户编号
     * @return
     */
    void pendingCombineAccept(CombineInfo combineInfo, Long userId);

    /**
     * 用字组合审核拒绝
     * @param combine 用字组合
     * @param userId 用户编号
     * @param soundReject 发音拗口  0-false 1-true
     * @param meaningReject 寓意不佳 0-false 1-true
     * @param writeReject 书写不利 0-false 1-true
     * @return
     */
    int pendingCombineReject(String combine, Long userId, Integer soundReject, Integer meaningReject, Integer writeReject);

    /**
     * 外部采集的名字总数量
     * @return
     */
    int externalNameCount();

    /**
     * 所有用字组合总数量
     * @return
     */
    int allCombineCount();

    /**
     * 过审用字组合总数量
     * @return
     */
    int acceptCombineCount();
}
