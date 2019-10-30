package com.zhouyi.business.core.service;

import com.zhouyi.business.core.model.LedenBbsAttachment;
import com.zhouyi.business.core.model.PageData;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 公告附件接口
 */
public interface LedenBbsAttachmentService {

    /**
     * 获取附件分页对象
     * @param conditions
     * @return
     */
    PageData<LedenBbsAttachment> getBbsAttachmentPage(Map<String,Object> conditions);


    /**
     * 新增公告附件
     * @param ledenBbsAttachment
     * @return
     */
    Boolean addBbsAttachment(LedenBbsAttachment[] ledenBbsAttachment, MultipartFile[] file) throws FileUploadException;

    /**
     * 删除公告附件
     * @param attachId
     * @return
     */
    Boolean removeBbsAttachment(String attachId);

    /**
     * 修改公告附件
     * @param ledenBbsAttachment
     * @return
     */
    Boolean updateBbsAttachment(LedenBbsAttachment ledenBbsAttachment);

    /**
     * 根据id获取公告附件
     * @param attachmentId
     * @return
     */
    LedenBbsAttachment getLedenBbsAttachmentById(String attachmentId);


    /**
     * 根据条件查询公告附件
     * @param conditions
     * @return
     */
    List<LedenBbsAttachment> listAttachmentByConditions(Map<String,Object> conditions);


    /**
     * 删除多个附件
     * @param attachmentIds
     * @return
     */
    Boolean deleteAttachments(String[] attachmentIds);
}
