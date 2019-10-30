package com.zhouyi.business.core.service;

import com.zhouyi.business.core.dao.LedenBbsAttachmentMapper;
import com.zhouyi.business.core.exception.FileuploadException;
import com.zhouyi.business.core.model.LedenBbsAttachment;
import com.zhouyi.business.core.model.PageData;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author 李秸康
 * @ClassNmae: LedenBbsAttachmentServiceImpl
 * @Description: 公告附件业务实现
 * @date 2019/6/25 13:52
 * @Version 1.0
 **/
@Service
public class LedenBbsAttachmentServiceImpl implements LedenBbsAttachmentService {

    @Autowired
    private LedenBbsAttachmentMapper ledenBbsAttachmentMapper;


    /**
     * 分页获取附件数据
     *
     * @param conditions
     * @return
     */
    @Override
    public PageData<LedenBbsAttachment> getBbsAttachmentPage(Map<String, Object> conditions) {
        List<LedenBbsAttachment> attachmentList = ledenBbsAttachmentMapper.listBbsAttachmentByConditions(conditions);
        Integer totalCount = ledenBbsAttachmentMapper.getBbsAttachmentCountByConditions(conditions);
        PageData<LedenBbsAttachment> pageData = new PageData<>(attachmentList, totalCount, (int) conditions.get("pSize"));
        return pageData;
    }


    /**
     * 添加附件信息
     *
     * @param ledenBbsAttachments
     * @return
     */
    @Override
    @Transactional
    public Boolean addBbsAttachment(LedenBbsAttachment[] ledenBbsAttachments, MultipartFile[] files) throws FileUploadException {
        List<LedenBbsAttachment> ledenBbsAttachments1=new ArrayList<>();
        //1.进行新增操作
        for (int i = 0; i < files.length; i++) {
            ledenBbsAttachments[i].setPkId(UUID.randomUUID().toString());
            ledenBbsAttachments1.add(ledenBbsAttachments[i]);
        }
        //生成主键
        ledenBbsAttachmentMapper.insertMulti(ledenBbsAttachments1);
        if (files != null) {
            for (int i = 0; i < ledenBbsAttachments.length; i++) {
                if(ledenBbsAttachments[i]==null)
                    continue;
                String realFileName = ledenBbsAttachments[i].getFilename() + "." + ledenBbsAttachments[i].getFiletype();
                //将文件存在本地
                File file1 = new File(realFileName);
                try {
                    files[i].transferTo(file1);
                } catch (IOException e) {
                    e.printStackTrace();
                    //如果报错则回滚操作
                    throw new FileuploadException(e.getMessage());
                }
            }

        }

        return true;
    }

    /**
     * 删除附件信息
     *
     * @param attachId
     * @return
     */
    @Override
    public Boolean removeBbsAttachment(String attachId) {
        return ledenBbsAttachmentMapper.deleteByPrimaryKey(attachId) == 1 ? true : false;
    }

    /**
     * 更新附件信息
     *
     * @param ledenBbsAttachment
     * @return
     */
    @Override
    public Boolean updateBbsAttachment(LedenBbsAttachment ledenBbsAttachment) {
        return ledenBbsAttachmentMapper.updateByPrimaryKeySelective(ledenBbsAttachment) == 1 ? true : false;
    }

    /**
     * 根据id获取附件信息
     *
     * @param attachmentId
     * @return
     */
    @Override
    public LedenBbsAttachment getLedenBbsAttachmentById(String attachmentId) {
        return ledenBbsAttachmentMapper.selectByPrimaryKey(attachmentId);
    }

    /**
     * 根据条件查询附件列表信息
     * @param conditions
     * @return
     */
    @Override
    public List<LedenBbsAttachment> listAttachmentByConditions(Map<String, Object> conditions) {
        return ledenBbsAttachmentMapper.listBbsAttachmentByConditions(conditions);
    }

    /**
     * 删除多个附件
     * @param attachmentIds
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteAttachments(String[] attachmentIds) {
        LedenBbsAttachment ledenBbsAttachment=new LedenBbsAttachment();
        ledenBbsAttachment.setDeletag("1");
        for (String attachmentId : attachmentIds) {
            ledenBbsAttachment.setPkId(attachmentId);
            ledenBbsAttachmentMapper.updateByPrimaryKey(ledenBbsAttachment);
        }
        return true;
    }
}
