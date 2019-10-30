package com.zhouyi.business.controller;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import com.zhouyi.business.core.model.LedenBbs;
import com.zhouyi.business.core.model.LedenBbsAttachment;
import com.zhouyi.business.core.model.PageData;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenBbsAttachmentService;
import com.zhouyi.business.core.service.LedenBbsService;
import com.zhouyi.business.core.utils.MapUtils;
import com.zhouyi.business.core.utils.MathUtil;
import com.zhouyi.business.core.utils.ResponseUtil;
import com.zhouyi.business.core.utils.ZipUtils;
import com.zhouyi.business.core.vo.BbsVo;
import com.zhouyi.business.dto.BbsConditionsDto;
import com.zhouyi.business.dto.BbsDto;
import com.zhouyi.business.dto.BbsInsertDto;
import io.swagger.annotations.*;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author 李秸康
 * @ClassNmae: LedenBbsController
 * @Description: 公告控制器
 * @date 2019/6/24 15:02
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/api/ledenBbs")
@Api(description = "公告相关api接口")
public class LedenBbsController {


    @Autowired
    private LedenBbsService ledenBbsService;
    @Value("${file.upload-location}")
    private String path;
    @Autowired
    private LedenBbsAttachmentService ledenBbsAttachmentService;
    @Value("${file.upload-location-temp}")
    private String tempPath;

    /**
     * 分页查询公告接口
     *
     * @param bbsConditionsDto
     * @return
     */

    @ApiOperation(value = "查询公告列表", notes = "分页查询公告信息")
//    @ApiImplicitParam(name = "bbsConditionsDto",value = "查询附带得条件")
    @RequestMapping(value = "/listBbs", method = RequestMethod.POST)
    public Response<Object> listLedenBbsByConditions(@RequestBody BbsConditionsDto bbsConditionsDto) {
        Map<String, Object> conditions = MapUtils.setPageConditions(bbsConditionsDto.getpNo(), bbsConditionsDto.getpSize());
        conditions.put("title", bbsConditionsDto.getTitle());
        conditions.put("draft", bbsConditionsDto.getDraft());
        conditions.put("publish", bbsConditionsDto.getPublish());
        conditions.put("beginDate", bbsConditionsDto.getBeginDate() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bbsConditionsDto.getBeginDate()) : null);
        conditions.put("endDate", bbsConditionsDto.getEndDate() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bbsConditionsDto.getEndDate()) : null);
        conditions.put("sort2",bbsConditionsDto.getSort2());
        PageData<LedenBbs> pageData = ledenBbsService.searchBbsByConditions(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, pageData);
    }


    /**
     * 修改公告接口
     *
     * @param bbsDto
     * @return
     */
    @ApiOperation(value = "修改公告接口", notes = "修改公告信息")
    @RequestMapping(value = "/updateBbs", method = RequestMethod.PUT)
    public Response<Object> modifyLedenBbs(@RequestBody BbsDto bbsDto) {
        bbsDto.setCreateDatetime();
        BbsVo bbsVo = new BbsVo();
        BeanUtils.copyProperties(bbsDto, bbsVo);
        boolean result = ledenBbsService.modifyLendenBbs(bbsVo);
        return ResponseUtil.getResponseInfo(result);
    }


    @RequestMapping(value = "/cannel/bbs")
    @ApiOperation(value = "取消发布接口")
    @ApiImplicitParam(value = "主键id", name = "pkId", paramType = "path")
    public Response<Object> cannelPublish(@PathVariable String pkId) {
        BbsVo bbsDto = new BbsVo();
        bbsDto.setPkId(pkId);
        bbsDto.setStatus("0");
        boolean result = ledenBbsService.modifyLendenBbs(bbsDto);
        return ResponseUtil.getResponseInfo(result);

    }

    /**
     * 发布公告接口
     *
     * @param bbsDto
     * @return
     */
    @ApiOperation(value = "新增公告", notes = "发布公告信息")
    @RequestMapping(value = "/insertBbs", method = RequestMethod.POST)
    public Response<String> addLedenBbs(@RequestBody BbsInsertDto bbsDto, HttpServletRequest request) {
        System.out.println("=================");
        System.out.println(request.getRequestURI());
        BbsVo bbsVo = new BbsVo();
        BeanUtils.copyProperties(bbsDto, bbsVo);
        String pkId = System.currentTimeMillis() + MathUtil.generateRandomCode(19);
        bbsVo.setPkId(pkId);
        bbsVo.setCreateDatetime(new Date());
        ledenBbsService.addLendenBbs(bbsVo);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, pkId);
    }

    /**
     * 删除公告接口
     *
     * @param pkId
     * @return
     */
    @ApiOperation(value = "删除公告", notes = "删除公告信息")
    @ApiImplicitParam(name = "pkId", value = "公告ID", paramType = "path")
    @RequestMapping(value = "/delteBbs/{pkId}", method = RequestMethod.DELETE)
    public Response<Object> deleteBbs(@PathVariable String pkId) {
        boolean flag = ledenBbsService.removeLendenBbs(pkId);
        return ResponseUtil.getResponseInfo(flag);
    }


//    @RequestMapping(value = "/upload",method=RequestMethod.POST, headers = "content-type=multipart/form-data")
//    @ApiOperation(value = "文件上传接口",response = String.class)
//   @ApiImplicitParams({
//           @ApiImplicitParam(name = "bbsId",value = "公告id",required = true,dataType = "String",paramType = "query")
//   })
//    public Response<Object> upload(
//                                     @RequestParam(value = "bbsId") String bbsId,
//                                       @RequestParam(value = "file") MultipartFile file
//                                  ){
//
//
//        //构建附件对象
//        LedenBbsAttachment ledenBbsAttachment=setLedenBbsAttachment(file,bbsId);//构建出附件对象
//        //进行上传
//        try {
//            ledenBbsAttachmentService.addBbsAttachment(ledenBbsAttachment,file);
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//            return ResponseUtil.ntrError(e.getMessage());
//        }
//        return ResponseUtil.getResponseInfo(true);
//    }


    /**
     * 上传附件2
     *
     * @param
     * @param file
     * @return
     */
    @ApiOperation(value = "上传文件接口2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "公告标题", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "contentss", value = "公告内容", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "deletag", value = "删除标志", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "annex", value = "备注", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "createUserId", value = "创建人id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "unitCode", value = "部门Id", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/upload2", method = RequestMethod.POST)
    public Response<Object> upload(@RequestParam(value = "file", required = false) MultipartFile[] file,
                                   @RequestParam(value = "title") String title,
                                   @RequestParam(value = "contentss") String contentss,
                                   @RequestParam(value = "status") String status,
                                   @RequestParam(value = "deletag") String deletag,
                                   @RequestParam(value = "annex") String annex,
                                   @RequestParam(value = "createUserId") String createUserId,
                                   @RequestParam(value = "unitCode") String unitCode) {


        BbsVo bbsVo = new BbsVo();
        bbsVo.setTitle(title);
        bbsVo.setContentss(contentss);
        bbsVo.setStatus(status);
        bbsVo.setDeletag(deletag);
        bbsVo.setAnnex(annex);
        bbsVo.setUnitCode(unitCode);
        bbsVo.setCreateUserId(createUserId);
        bbsVo.setCreateDatetime(new Date());
        String pkId = System.currentTimeMillis() + MathUtil.generateRandomCode(19);
        bbsVo.setPkId(pkId);
        ledenBbsService.addLendenBbs(bbsVo);

        LedenBbsAttachment[] attachments = new LedenBbsAttachment[3];
        if (file.length > 0) {
            //构建附件对象
            for (int i = 0; i < file.length; i++) {
                attachments[i] = setLedenBbsAttachment(file[i], pkId);
            }
            //进行上传
            try {
                ledenBbsAttachmentService.addBbsAttachment(attachments, file);
            } catch (FileUploadException e) {
                e.printStackTrace();
                return ResponseUtil.ntrError(e.getMessage());
            }
        }
        return ResponseUtil.getResponseInfo(true);


    }


    @ApiOperation(value = "获取公告所有的附件信息")
    @ApiImplicitParam(value = "公告id", name = "bbsId", paramType = "path")
    @RequestMapping(value = "/attachments/{bbsId}", method = RequestMethod.GET)
    public Response<List<LedenBbsAttachment>> listBbsAttachments(@PathVariable String bbsId) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("bbsId", bbsId);
        List<LedenBbsAttachment> attachmentList = ledenBbsAttachmentService.listAttachmentByConditions(conditions);
        return ResponseUtil.getResponseInfo(ReturnCode.SUCCESS, attachmentList);

    }


    @ApiOperation(value = "删除指定id的公告")
    @RequestMapping(value = "/attachments", method = RequestMethod.DELETE)
    public Response<Object> deleteAttachments(String[] attachmentIds) {
        boolean flag = ledenBbsAttachmentService.deleteAttachments(attachmentIds);
        return ResponseUtil.getResponseInfo(flag);
    }

    /**
     * 构建附件对象
     *
     * @param file
     * @param pkId
     * @return
     */
    private LedenBbsAttachment setLedenBbsAttachment(MultipartFile file, String pkId) {
        //进行文件上传
        //获取文件后缀
        String originalFileName = file.getOriginalFilename();
        String type = file.getOriginalFilename().substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length());
        String filename = "BBS_Attach" + System.currentTimeMillis() + MathUtil.generateRandomCode(5); //获取源文件名
        String url = path + File.separator + filename + "." + type;
        String size = Long.toString(file.getSize());
        String delFlag = "0";

        //构建附件对象
        LedenBbsAttachment ledenBbsAttachment = new LedenBbsAttachment(pkId,
                filename, url, size, type, delFlag);//构建出附件对象
        return ledenBbsAttachment;
    }


    public static void main(String[] args) {
        String str = UUID.randomUUID().toString();
        System.out.println(str.length());
    }

    @RequestMapping(value = "/selectBbsOne")
    public Response selectBbsByDate() {
        return ledenBbsService.selectBbsByDate();
    }


    @ApiOperation(value = "多文件下载（zip格式）")
    @RequestMapping(value = "/download_multi")
    public void
    downloadMultiAttachment(String[] path2, HttpServletResponse response) throws IOException {
        if (path2 == null || path2.length == 0) {
            throw new BusinessException(ReturnCode.ERROR_1044);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //设置导出的文件格式
        String zipFileNme = simpleDateFormat.format(new Date()) + MathUtil.generateRandomCode(8) + ".zip";


        ZipOutputStream zipOutputStream = null;
        FileInputStream fileInputStream = null;

        String completeFilePath = tempPath + File.separator + zipFileNme;
        File zipFile = new File(completeFilePath);
        File tempDir = new File(tempPath);
        if (!tempDir.exists())
            tempDir.mkdir();
        try {


            if (!zipFile.exists())
                zipFile.createNewFile();
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < path2.length; i++) {
                //循环遍历数组
                File file = new File(path2[i]);

                fileInputStream = new FileInputStream(file);
                if (!file.exists())
                    throw new BusinessException(ReturnCode.ERROR_1044);

                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(zipEntry);
                int temp = 0;
                while ((temp = fileInputStream.read()) != -1) {
                    zipOutputStream.write(temp);
                    zipOutputStream.flush();
                }
            }


            zipOutputStream.close();

            fileInputStream.close();


            //读取刚生成的文件写回
            File newZipFile = new File(completeFilePath);


            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(newZipFile));
                byte[] buffer = new byte[1024];
                int len = 0;
                response.setHeader("Content-Type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(zipFileNme.getBytes("UTF-8"), "iso-8859-1"));
                OutputStream outputStream = response.getOutputStream();
                while ((len = bufferedInputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.close();
                bufferedInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ReturnCode.ERROR_1045);
        } finally {
            //删除文件
//            if (zipFile.exists())
//                zipFile.delete();
        }

//            String zipName="fasdf.zip";
//            response.setContentType("APPLICATION/OCTET-STREAM");
//            response.setHeader("Content-Disposition","attachment; filename="+zipName);
//            ZipOutputStream out=new ZipOutputStream(response.getOutputStream());
//        try {
//            ZipUtils.doCompress("D:/upload/a.txt",out);
//                response.flushBuffer();
////            for (String s : path2) {
////
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            out.close();
//        }
    }


    @RequestMapping(value = "/test")
    public void test(HttpServletResponse response, String path) {
//        File file = new File("d:/upload/c.zip");
        System.out.println(path);
        File file = new File(path);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int len = 0;
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("aa.zip".getBytes("UTF-8"), "iso-8859-1"));
            OutputStream outputStream = response.getOutputStream();
            while ((len = bufferedInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
