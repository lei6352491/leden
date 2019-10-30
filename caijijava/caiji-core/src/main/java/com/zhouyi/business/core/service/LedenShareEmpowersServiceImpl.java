package com.zhouyi.business.core.service;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.zhouyi.business.core.dao.LedenCollectNodeMapper;
import com.zhouyi.business.core.dao.LedenShareEmpowersMapper;
import com.zhouyi.business.core.model.*;
import com.zhouyi.business.core.utils.MathUtil;
import com.zhouyi.business.core.vo.LedenCollectNodeVo;
import com.zhouyi.business.core.vo.LedenShareEmpowersVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 杜承旭
 * @ClassNmae: LedenShareEmpowersServiceImpl
 * @Description: TODO
 * @date 2019/7/17 13:57
 * @Version 1.0
 **/
@Service
public class LedenShareEmpowersServiceImpl
        extends BaseServiceImpl<LedenShareEmpowers, LedenShareEmpowersVo>
        implements LedenShareEmpowersService {

    @Autowired
    private LedenShareEmpowersMapper ledenShareEmpowersMapper;
    @Autowired
    private LedenCollectNodeMapper nodeMapper;

    @Autowired
    private LedenShareAppService ledenShareAppService;
    @Autowired
    private LedenCollectNodeService ledenCollectNodeService;

    @Override
    public List<LedenShareApp> listShareEmpowersByConditions(Map<String, Object> conditions) {
        //获取所有应用
        List<LedenShareApp> shareApps = ledenShareAppService.listAllShareApps(conditions);
        //获取所有的采集节点
        Response dataList = ledenCollectNodeService.findDataList(new LedenCollectNodeVo());
        Object o = ((Map) dataList.getData()).get("list");
        List<LedenCollectNode> nodes=(List)o;

        Map<String, String> codeSignMap = nodes.stream().collect(Collectors.toMap(LedenCollectNode::getNodeCode, LedenCollectNode::getNodeSign));

        if (shareApps != null && shareApps.size() > 0) {
            //遍历所有的应用并查询授权信息
            shareApps.stream().forEach(x -> {
                List<NodeEmpowersDto> nodeEmpower = ledenShareEmpowersMapper.getNodeEmpowersByAppId(x.getAppId());
                //将授权信息标识
                nodeEmpower.forEach(nodeEmpowersDto -> {
                    if(nodeEmpowersDto.getLedenShareEmpowers()!=null){
                        LedenShareEmpowers shareEmpowers=nodeEmpowersDto.getLedenShareEmpowers();
                        if(shareEmpowers.getDeletag().equals("1"))
                            shareEmpowers.setDeletag2(false);
                        else if(shareEmpowers.getDeletag().equals("0"))
                            shareEmpowers.setDeletag2(true);
                        if(shareEmpowers.getShareType().equals("1"))
                            shareEmpowers.setMultiple(true);
                        else if(shareEmpowers.getShareType().equals("2"))
                            shareEmpowers.setRecive(true);
                        else if(shareEmpowers.getShareType().equals("3"))
                            shareEmpowers.setIssue(true);
                        nodeEmpowersDto.setLedenShareEmpowers(shareEmpowers);
                    }else{
                        LedenShareEmpowers ledenShareEmpowers=new LedenShareEmpowers();//生成一个空的授权对象并赋值基本数据
                        ledenShareEmpowers.setAppId(x.getAppId());
                        ledenShareEmpowers.setNodeSign(codeSignMap.get(nodeEmpowersDto.getLedenCollectNode().getNodeCode()));
                        nodeEmpowersDto.setLedenShareEmpowers(ledenShareEmpowers);
                    }
                });
                x.setNodeEmpowersDto(nodeEmpower);
            });
        }


        return shareApps;
    }

    @Override
    @Transactional
    public boolean changAppEmpowers(List<LedenShareEmpowers> ledenShareEmpowers) {
        //根据appId查询应用所有的授权信息
        String appId=ledenShareEmpowers.get(0).getAppId();
        //根据id查询出该应用下所有的授权信息
        Map<String,Object> conditions=new HashMap<>();
        conditions.put("appId",appId);
        //获取所有节点和授权信息信息
        List<NodeEmpowersDto> nodeEmpowersByAppId = ledenShareEmpowersMapper.getNodeEmpowersByAppId(appId);
        //对改appId所有的授权进行判断
        Map<String, String> collects = nodeEmpowersByAppId.stream().filter(x -> x.getLedenShareEmpowers() != null)
                .map(x -> x.getLedenShareEmpowers()).collect(Collectors.toMap(LedenShareEmpowers::getNodeSign, LedenShareEmpowers::getPkId));
        //改：提取出节点和pkId
//        对添加的节点进行赋值并分类进行批量新增或修改
        ledenShareEmpowers.stream().forEach(x->{
            if(collects.get(x.getNodeSign())!=null){
                x.setUpdateDatetime();
                x.setPkId(collects.get(x.getNodeSign()));
            }else{
                 x.setCreateDatetime();
                x.setCreateUserId(x.getUpdateUserId());
                x.setPkId(System.currentTimeMillis()+MathUtil.generateRandomCode(18));
            }
        });

        //按照新增和修改的信息分组，true为新增，false为修改
        Map<Boolean, List<LedenShareEmpowers>> empowersPacket = ledenShareEmpowers.stream().collect(Collectors.groupingBy(x -> x.getUpdateDatetime() == null));
        //在新增的节点中过滤出掉未授权的
        if(empowersPacket.get(true)!=null)
            empowersPacket.put(true,empowersPacket.get(true).stream().filter(x->x.getShareType().equals("1")||x.getDeletag().equals("0")).collect(Collectors.toList()));
        System.out.println("----to-------");
        //分批次进行授权
        if(empowersPacket.get(true)!=null && empowersPacket.get(true).size()>0)
            ledenShareEmpowersMapper.insertMultiEmpowers(empowersPacket.get(true));
        if(empowersPacket.get(false)!=null && empowersPacket.get(false).size()>0)
            ledenShareEmpowersMapper.updateMultiEmpowers(empowersPacket.get(false));
        return true;
    }


    public static void main(String[] args) {
//        long str=System.currentTimeMillis();
//        String s = String.valueOf(str);
//        System.out.println(s.length());


    }

}

