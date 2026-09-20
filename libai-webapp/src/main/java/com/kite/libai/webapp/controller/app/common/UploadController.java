package com.kite.libai.webapp.controller.app.common;

import com.kite.libai.core.result.Result;
import com.kite.libai.core.utils.DigestUtils;
import com.kite.libai.core.utils.Exceptions;
import com.kite.libai.provider.tripartite.service.AliOssService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/app/v1/files")
public class UploadController {

    private final AliOssService aliOssService;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return Result.fail("请选择上传文件");
        }
        try {
            // 获取上传文件的路径
            String uploadFilePath = file.getOriginalFilename();
            // 截取上传文件的后缀
            assert uploadFilePath != null;
            String fileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.'));
            // 改为md5形式 首次会多出md5得解析时间,再次上传相同图片到oss,时间会明显减少+
            byte[] data;
            data = file.getBytes();
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            BufferedImage read = ImageIO.read(in);
            int width = read.getWidth();
            int height = read.getHeight();
            String key = DigestUtils.md5Hex(data) + "_" + width + "x" + height + fileSuffix;
            String link = aliOssService.upload(data, key);
            file.getInputStream().close();
            return Result.success(link);
        } catch (IOException e) {
            log.error(Exceptions.getStackTraceAsString(e));
            return Result.fail("上传失败");
        }
    }
}
