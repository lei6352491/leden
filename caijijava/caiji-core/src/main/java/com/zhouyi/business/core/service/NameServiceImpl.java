package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.NameMapper;
import com.zhouyi.business.core.model.CombineInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NameServiceImpl implements NameService {

    @Autowired
    private NameMapper nameMapper;

    @Override
    public int addExternalName(List<Object> nameList) {
        return nameMapper.addExternalName(nameList);
    }

    @Override
    public List<Object> charCombineList(Long pendingCombineId, String firstChar, String lastChar, Integer recordCount) {
        return nameMapper.charCombineList(pendingCombineId,firstChar,lastChar,recordCount);
    }

    @Override
    public List<Object> surnameList(String surname, String yinjie) {
        return nameMapper.surnameList(surname, yinjie);
    }

    @Override
    public List<Object> distributableCombineList(Long userId, Integer recordCount) {
        return nameMapper.distributableCombineList(userId, recordCount);
    }

    @Override
    public List<Object> pendingCombineList(Long userId) {
        return nameMapper.pendingCombineList(userId);
    }

    @Override
    public List<Object> combineReviewedList(Long userId, CombineInfo combineInfo,Integer pStart,Integer pSize) {
        return nameMapper.combineReviewedList(userId, combineInfo,pStart,pSize);
    }

    @Override
    public int combineReviewedCount(Long userId, CombineInfo combineInfo) {
        return nameMapper.combineReviewedCount(userId, combineInfo);
    }

    @Override
    public int allocationCombine(Long userId, List<Object> combineList) {
        return nameMapper.allocationCombine(userId,combineList);
    }

    @Override
    @Transactional
    public void pendingCombineAccept(CombineInfo combineInfo, Long userId) {
        nameMapper.changePendingCombineStatus(combineInfo.getCombine(),3,userId);

        List<Object> combineList = nameMapper.charCombineList(combineInfo.getPendingCombineId(),null,null,10);
        if (combineList.size() == 0) {
            nameMapper.saveCharCombine(combineInfo,userId);
        }
    }

    @Override
    @Transactional
    public int pendingCombineReject(String combine, Long userId, Integer soundReject, Integer meaningReject, Integer writeReject) {
        nameMapper.changePendingCombineStatus(combine,4,userId);
        nameMapper.updateCombineRejectInfo(combine, userId, soundReject,meaningReject,writeReject);
        return 0;
    }

    @Override
    public int externalNameCount() {
        return nameMapper.externalNameCount();
    }

    @Override
    public int allCombineCount() {
        return nameMapper.allCombineCount();
    }

    @Override
    public int acceptCombineCount() {
        return nameMapper.acceptCombineCount();
    }
}
