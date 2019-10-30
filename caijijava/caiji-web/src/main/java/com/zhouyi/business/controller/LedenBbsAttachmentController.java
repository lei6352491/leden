package com.zhouyi.business.controller;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.model.LedenBbsAttachment;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenBbsAttachmentService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.ParseUtil;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.dto.LedenBbsAttachmentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author 李秸康
 * @ClassNmae: LedenBbsAttachmentController
 * @Description: 公告附件控制器
 * @date 2019/6/25 14:03
 * @Version 1.0
 **/
@RestController
@RequestMapping(value="/api/bbsAttachment")
@Api(description = "公告附件接口",hidden = true)
public class LedenBbsAttachmentController {

    @Autowired
    private LedenBbsAttachmentService ledenBbsAttachmentService;

    /**
     * 分页获取附件分页数据
     * @param ledenBbsAttachmentDto
     * @return
     */
    @RequestMapping(value="/listAttachment")
    public Response<PageData<LedenBbsAttachment>> listBbsAttachment(@RequestBody LedenBbsAttachmentDto ledenBbsAttachmentDto){
        Map<String,Object> conditions= MapUtils.setPageConditions(ledenBbsAttachmentDto.getpNo(),ledenBbsAttachmentDto.getpSize());
        PageData<LedenBbsAttachment> pageData=ledenBbsAttachmentService.getBbsAttachmentPage(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,pageData);
    }






    /**
     * 删除附件信息
     * @param attachmentId
     * @return
     */
    @RequestMapping(value="/delBbsAttachment/{attachmentId}")
    public Response<Object> delBbsAttachment(@PathVariable String attachmentId){
        boolean result=ledenBbsAttachmentService.removeBbsAttachment(attachmentId);
        return ResponseUtil.getResponseInfo(result);
    }


    /**
     * 根据id获取附件对象信息
     * @param attachmentId
     * @return
     */
    @RequestMapping(value="/getAttachment/{attachmentId}")
    public Response<LedenBbsAttachment> getBbsAttachmentById(@PathVariable String attachmentId){
        LedenBbsAttachment ledenBbsAttachment=ledenBbsAttachmentService.getLedenBbsAttachmentById(attachmentId);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS,ledenBbsAttachment);
    }




    @ApiOperation(value = "附件下载")
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void downloadAttachment(String path, HttpServletResponse response){
        String suffix=path.substring(path.lastIndexOf(".")+1);
        String fileName="BbsAttachment."+suffix;
       if(path!=null){
           FileInputStream inputStream=null;
           BufferedInputStream bufferedInputStream=null;
           OutputStream outputStream=null;

           try {
               File file=new File(path);
               if(file.exists()){
                  response.setHeader("Content-Type","application/octet-stream");
                  response.setHeader("Content-Disposition","attachment;filename=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
                  inputStream=new FileInputStream(file);
                  bufferedInputStream=new BufferedInputStream(inputStream);
                  outputStream=response.getOutputStream();

                  byte[] buffer=new byte[1024];
                  int len=0;
                  while((len=bufferedInputStream.read(buffer))!=-1){
                      outputStream.write(buffer,0,len);
                  }
               }else{
                   ReturnCode returnCode=ReturnCode.ERROR_1028;
                   PrintWriter writer = response.getWriter();
                   writer.write(JSON.toJSONString(returnCode));
                   writer.close();
               }
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }finally {
               try {
                   if(inputStream!=null)
                       inputStream.close();
                   if(bufferedInputStream!=null)
                       bufferedInputStream.close();
                   if(outputStream!=null)
                       outputStream.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
       }
    }


    @RequestMapping(value = "/download",method=RequestMethod.POST)
    @ApiOperation(value = "下载附件2")
    public void downloadAttachment2(String path,HttpServletResponse response){
        String suffix=path.substring(path.lastIndexOf(".")+1);
        String fileName="公告附件."+suffix;
        try {
            InputStream inputStream=new BufferedInputStream(new FileInputStream(path));
            byte[] bytes=new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();

            response.reset();

            OutputStream toClient=new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));// 如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
            toClient.write(bytes);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
