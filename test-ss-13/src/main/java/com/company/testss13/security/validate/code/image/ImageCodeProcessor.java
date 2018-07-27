package com.company.testss13.security.validate.code.image;

import com.company.testss13.security.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;

/**
 * 图片验证码处理器
 *
 * @author starmoon1994
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        BufferedImage codeImage = imageCode.getImage();
        ServletOutputStream outputStream = request.getResponse().getOutputStream();
        ImageIO.write(codeImage, "JPEG", outputStream);
    }

}
