package com.zhouyi.business.core.service;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.dao.LedenBbsMapper;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.model.LedenBbs;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.vo.BbsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenBbsServiceImpl
 * @Description: TODO
 * @date 2019/6/24 14:51
 * @Version 1.0
 **/
@Service
public class LedenBbsServiceImpl implements LedenBbsService {

    @Autowired
    private LedenBbsMapper ledenBbsMapper;


    /**
     * 查询公告page对象
     * @param conditions
     * @return
     */
    @Override
    public PageData<LedenBbs> searchBbsByConditions(Map<String, Object> conditions) {

        List<LedenBbs> list=ledenBbsMapper.listLedenBbsByConditions(conditions);
        //如果没有修改日期，则将创建日期作为修改日期
        list.forEach(x->{
            if(x.getUpdateDatetime()==null)
                try {
                    x.setUpdateDatetime(x.getCreateDatetime());
                } catch (Exception e) {
                    throw new BusinessException(ReturnCode.ERROR_1042);
                }

        });
        int totalCount=ledenBbsMapper.getLedenBbsCountByConditions(conditions);
        PageData<LedenBbs> pageData=new PageData<>(list,totalCount,(int)conditions.get("pSize"));
        return pageData;
    }

    /**
     * 修改公告信息
     * @param bbsVo
     * @return
     */
    @Override
    public Boolean modifyLendenBbs(BbsVo bbsVo) {
        return ledenBbsMapper.updateByPrimaryKeySelective(bbsVo)==1?true:false;
    }

    /**
     * 新增公告信息
     * @param bbsVo
     * @return
     */
    @Override
    public Boolean addLendenBbs(BbsVo bbsVo) {
        return ledenBbsMapper.insertSelective(bbsVo)==1?true:false;
    }


    /**
     * 删除公告信息
     * @param pkId
     * @return
     */
    @Override
    public Boolean removeLendenBbs(String pkId) {
        BbsVo ledenBbs=new BbsVo();
        ledenBbs.setPkId(pkId.toString());
        ledenBbs.setDeletag("1");
        return ledenBbsMapper.updateByPrimaryKeySelective(ledenBbs)==1?true:false;
    }

    @Override
    public Response selectBbsByDate() {
        List<LedenBbs> list = ledenBbsMapper.selectBbsByDate();
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,list);
    }
}
