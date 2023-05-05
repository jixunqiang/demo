package basic.controller;

import basic.services.AliyunOssService;
import basic.services.ResultResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload/")
public class UploadController extends BaseController {
    @Resource
    private AliyunOssService aliyunOssService;

    @PostMapping("/image")
    public ResultResponse image(@RequestPart MultipartFile file) throws IOException {
        if(file.isEmpty()) {
            return ResultResponse.fail("请选择上传文件");
        }

        //后缀
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        //判断是否为图片
        if(!ext.startsWith("image/")) {
            return ResultResponse.fail("格式错误");
        }

        System.out.println(file.getName());

        String fileName = "img_" + aliyunOssService.randomFilePath(ext); //文件名
        String filePath = "D:/" + fileName;
        File localFile = new File(filePath);
        file.transferTo(localFile);

        try {
            if (!aliyunOssService.uploadFile(fileName, filePath)) {
                return ResultResponse.fail("上传文件失败");
            }
        } catch (Exception e) {
            return ResultResponse.fail(e.getMessage());
        } finally {
            if (localFile.exists()) {
                localFile.delete();
            }
        }

        JSONObject data = new JSONObject();
        data.put("full_url", aliyunOssService.getOssFile(fileName));
        data.put("file", fileName);
        return ResultResponse.success(data);
    }

    @PostMapping("/file")
    public ResultResponse file(@RequestPart MultipartFile file) throws IOException {
        if(file.isEmpty()) {
            return ResultResponse.fail("请选择上传文件");
        }

        //后缀
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        System.out.println(file.getName());

        String fileName = "file_" + aliyunOssService.randomFilePath(ext); //文件名
        String filePath = "D:/" + fileName;
        File localFile = new File(filePath);
        file.transferTo(localFile);

        try {
            if (!aliyunOssService.uploadFile(fileName, filePath)) {
                return ResultResponse.fail("上传文件失败");
            }
        } catch (Exception e) {
            return ResultResponse.fail(e.getMessage());
        } finally {
            if (localFile.exists()) {
                localFile.delete();
            }
        }
        JSONObject data = new JSONObject();
        data.put("full_url", aliyunOssService.getOssFile(fileName));
        data.put("file", fileName);
        return ResultResponse.success(data);
    }

    @PostMapping("/excel")
    public ResultResponse excel(@RequestPart MultipartFile file) throws IOException {
        if(file.isEmpty()) {
            return ResultResponse.fail("请选择上传文件");
        }

        //后缀
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        //判断是否为excel
        if(!ext.endsWith(".xls") && !ext.endsWith(".xlsx")) {
            return ResultResponse.fail("格式错误，只支持excel文件");
        }

        System.out.println(file.getName());

        String fileName = aliyunOssService.randomFilePath(ext); //文件名
        String filePath = "D:/" + fileName;
        File localFile = new File(filePath);
        file.transferTo(localFile);

        try {
            if (!aliyunOssService.uploadFile("excel/" + fileName, filePath)) {
                return ResultResponse.fail("上传文件失败");
            }
        } catch (Exception e) {
            return ResultResponse.fail(e.getMessage());
        } finally {
            if (localFile.exists()) {
                localFile.delete();
            }
        }

        JSONObject data = new JSONObject();
        data.put("full_url", aliyunOssService.getOssFile("excel/" + fileName));
        data.put("file", fileName);
        return ResultResponse.success(data);
    }
}
