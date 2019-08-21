package xyz.yuelai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.yuelai.pojo.dto.ImageUploadDTO;
import xyz.yuelai.util.Constant;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author 李泽众
 * @date 2019/8/2-16:19
 */
@Api(tags = "文件上传api")
@RestController
public class FileUploadController {

    @ApiOperation(value = "图片上传")
    @PostMapping(value = "/upload/img", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public ImageUploadDTO uploadImg(MultipartFile file) {
        LocalDateTime now = LocalDateTime.now();
        String filePath = Constant.IMAGE_PATH.replace("{yyyy}", String.valueOf(now.getYear()))
                .replace("{MM}", String.format("%02d", now.getMonthValue()))
                .replace("{dd}", String.format("%02d", now.getDayOfMonth()))
                .replace("{currentTimeMillis}", String.valueOf(System.currentTimeMillis()));

        File file1 = new File(filePath);
        ImageUploadDTO imageUploadDTO = new ImageUploadDTO();
        try {
            if(!file1.exists()){
                file1.mkdirs();
                file1.createNewFile();
            }
            file.transferTo(file1);
            imageUploadDTO.setUploaded(1);
            imageUploadDTO.setFileName(file1.getName());
            imageUploadDTO.setUrl(filePath.replace("/www/data", Constant.IMG_SERVER));
        } catch (IOException e) {
            e.printStackTrace();
            imageUploadDTO.setUploaded(0);
            imageUploadDTO.setError("图片上传失败！");
        }
        return imageUploadDTO;
    }
}
