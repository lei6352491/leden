package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.ZhaoBiaoMapper;
import com.zhouyi.business.core.model.ZhaoBiao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李秸康
 * @ClassNmae: ZhaoBiaoServiceImpl
 * @Description: TODO 招标业务实现
 * @date 2019/7/15 12:46
 * @Version 1.0
 **/
@Service
public class ZhaoBiaoServiceImpl implements ZhaoBiaoService{
    @Autowired
    private ZhaoBiaoMapper zhaoBiaoMapper;

    /**
     * 根据标题查询招标信息
     * @param title
     * @return
     */
    @Override
    public List<ZhaoBiao> searchZhaoBiaoInfoBytitle(String title) {

        return zhaoBiaoMapper.listZhaoBiaoInfoByTitle(title);
    }
}
