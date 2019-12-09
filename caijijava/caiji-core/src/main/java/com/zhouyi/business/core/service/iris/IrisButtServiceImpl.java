package com.zhouyi.business.core.service.iris;


import com.zhouyi.business.core.dao.LedenCollectIrisMapper;
import com.zhouyi.business.core.model.xinzhen.IdSecretVersion;
import com.zhouyi.business.core.model.xinzhen.IrisCommons;
import com.zhouyi.business.core.model.xinzhen.IrisComparsion;
import com.zhouyi.business.core.model.xinzhen.IrisReceive;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: first
 * @Date: 下午4:32 2019/12/9
 * @Description: 虹膜对接实现
**/
@Service
public class IrisButtServiceImpl implements IrisButtService{

    @Autowired
    private LedenCollectIrisMapper ledenCollectIrisMapper;
    @Autowired
    private IdSecretVersion idSecretVersion;

    @Override
    public IrisReceive irisCollect(String rybh) {

        IrisReceive irisReceive = ledenCollectIrisMapper.irisCollectSearch(rybh);
        BeanUtils.copyProperties(idSecretVersion,irisReceive);
        return irisReceive;
    }

    @Override
    public IrisCommons irisExamine(String rybh) {
        IrisCommons irisCommons = ledenCollectIrisMapper.selectCommons(rybh);
        return irisCommons;
    }

    @Override
    public IrisComparsion irisCheck(String rybh) {
        IrisComparsion irisComparsion = ledenCollectIrisMapper.irisComparsionSearch(rybh);
        return irisComparsion;
    }
}
