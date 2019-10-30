package com.zhouyi.business.controller;

import com.zhouyi.business.core.model.LedenCollectVoiceprint;
import com.zhouyi.business.core.model.Response;
import com.zhouyi.business.core.service.LedenCollectVoiceprintService;
import org.apache.http.util.ByteArrayBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author 杜承旭
 * @ClassNmae: LedenCollectVoicePrintController
 * @Description: TODO
 * @date 2019/8/27 11:36
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/api/voiceprint")
public class LedenCollectVoicePrintController {

    @Autowired
    private LedenCollectVoiceprintService ledenCollectVoiceprintService;

    @RequestMapping(value = "/selectvoiceprint/{id}")
    public Response<LedenCollectVoiceprint> selectVoicePrintByPersonCode(@PathVariable String id) {
        return ledenCollectVoiceprintService.selectVoicePrintByPersonCode(id);
    }

    /**
     * 获取音频文件
     * */
    @RequestMapping(value = "/selectvoiceprintblob/{id}")
    public void selectVoicePrintYpBlobByPersonCode(@PathVariable String id, HttpServletResponse response) throws IOException {
        InputStream inputStream = ledenCollectVoiceprintService.selectVoicePrintByPersonCodeBlob(id);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.setHeader("Content-Type", "audio/mp3");
            if (inputStream != null){
                byte[] bytes = new byte[4096];
                int len;
                while ((len = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes,0,len);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream != null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取声纹文件
     * */
    @RequestMapping(value = "/selectvoiceprintblobswsj/{id}")
    public void selectVoicePrintSwBlobByPersonCode(@PathVariable String id, HttpServletResponse response) throws IOException {
        InputStream inputStream = ledenCollectVoiceprintService.selectVoicePrintByPersonCodeBlobSWSJ(id);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.setHeader("Content-Type", "audio/mp3");
            if (inputStream != null){
                byte[] bytes = new byte[4096];
                int len;
                while ((len = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes,0,len);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream != null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
