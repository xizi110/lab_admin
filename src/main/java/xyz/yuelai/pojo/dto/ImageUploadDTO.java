package xyz.yuelai.pojo.dto;

import lombok.Data;

/**
 * @author 李泽众
 * @date 2019/8/3-9:17
 */


@Data
public class ImageUploadDTO {
    private Integer uploaded;
    private String fileName;
    private String url;
    private String error;
}
