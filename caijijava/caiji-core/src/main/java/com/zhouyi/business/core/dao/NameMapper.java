package com.zhouyi.business.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhouyi.business.core.model.CombineInfo;
import com.zhouyi.business.core.model.NameInfo;

@Mapper
public interface NameMapper extends BaseMapper <NameInfo, Long> {
    /**
     * 保存外部采集的名字
     * @param nameList 名字列表
     * @return
     */
    int addExternalName(@Param("externalNames") List<Object> nameList);

    /**
     * 可分配待审名字组合
     * @param userId 用户编号
     * @param recordCount 用户返回记录数
     * @return
     */
    List<Object> distributableCombineList(@Param("userId") Long userId, @Param("recordCount") Integer recordCount);

    /**
     * 待审名字组合
     * @param userId 用户编号
     * @return
     */
    List<Object> pendingCombineList(@Param("userId") Long userId);

    /**
     * 已审核用字组合列表
     * @param userId 用户编号
     * @param combineInfo 起名用字信息
     * @param pStart 记录起始位置
     * @param pSize 记录数
     * @return
     */
    List<Object> combineReviewedList(@Param("userId") Long userId, @Param("combineInfo") CombineInfo combineInfo, @Param("pStart") Integer pStart, @Param("pSize") Integer pSize);

    /**
     * 已审核用字组合列表数量
     * @param userId 用户编号
     * @param combineInfo 起名用字信息
     * @return
     */
    int combineReviewedCount(@Param("userId") Long userId, @Param("combineInfo") CombineInfo combineInfo);

    /**
     * 分配用字组合给初审员
     * @param userId 用户编号
     * @param combineList 用字组合列表
     * @return
     */
    int allocationCombine(@Param("userId") Long userId, @Param("combineList") List<Object> combineList);

    /**
     * 更改起名用字组合的审核状态
     * @param combine 组合用字
     * @param status 状态码
     * @param userId 用户编号
     * @return
     */
    int changePendingCombineStatus(@Param("combine") String combine, @Param("status") Integer status, @Param("userId") Long userId);

    /**
     * 起名用字审核被拒原因记录
     * @param combine 组合用字
     * @param userId 用户编号
     * @param soundReject 发音拗口  0-false 1-true
     * @param meaningReject 寓意不佳 0-false 1-true
     * @param writeReject 书写不利 0-false 1-true
     * @return
     */
    int updateCombineRejectInfo(@Param("combine") String combine, @Param("userId") Long userId, @Param("soundReject") Integer soundReject, @Param("meaningReject") Integer meaningReject, @Param("writeReject") Integer writeReject);

    /**
     * 保存起名用字组合
     * @param combineInfo 组合用字详情
     * @param userId 用户编号
     * @return
     */
    int saveCharCombine(@Param("combineInfo") CombineInfo combineInfo, @Param("userId") Long userId);

    /**
     * 获取姓氏列表
     * @param surname 姓氏
     * @param yinjie 音节
     * @return
     */
    List<Object> surnameList(@Param("surname") String surname, @Param("yinjie") String yinjie);

    /**
     * 获取起名用字组合列表
     * @param pendingCombineId 待审用字组合编号
     * @param firstChar 指定第一个汉字
     * @param lastChar 指定第二个汉字
     * @param recordCount 返回记录条数
     * @return
     */
    List<Object> charCombineList(@Param("pendingCombineId") Long pendingCombineId, @Param("firstChar") String firstChar, @Param("lastChar") String lastChar, @Param("recordCount") Integer recordCount);

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
     * 过审用字组合总树立
     * @return
     */
    int acceptCombineCount();
}

