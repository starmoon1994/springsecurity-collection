//package com.company.testss13.security.validate.code.simple;
//
//import com.company.testss13.security.validate.code.ValidateCode;
//import com.company.testss13.security.validate.code.image.ImageCode;
//import com.company.testss13.security.validate.code.image.ImageCodeGenerator;
//import org.springframework.social.connect.web.HttpSessionSessionStrategy;
//import org.springframework.social.connect.web.SessionStrategy;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.ServletWebRequest;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.image.BufferedImage;
//
///**
// * 简单版本的图片验证码功能实现
// * @author starmoon1994
// */
//@RestController
//public class SimpleValidateCodeController {
//
//    /**
//     * 创建验证码
//     */
//    @GetMapping("/code/imgvalidate")
//    public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
//
//        // 1 生成验证码  生成图片
//
//        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
//        ImageCode imageCode = imageCodeGenerator.generate(servletWebRequest);
//
//        // 2 保存 将验证码放到session中
//
//        ValidateCode validateCodeCopy = new ValidateCode(imageCode.getCode(), imageCode.getExpireTime());
//        SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//        sessionStrategy.setAttribute(servletWebRequest, "SESSION_KEY_FOR_CODE_IMAGE", validateCodeCopy);
//
//        //  3 发送
//
//        BufferedImage codeImage = imageCode.getImage();
//        ServletOutputStream outputStream = servletWebRequest.getResponse().getOutputStream();
//        ImageIO.write(codeImage, "JPEG", outputStream);
//
//    }
//
//}
