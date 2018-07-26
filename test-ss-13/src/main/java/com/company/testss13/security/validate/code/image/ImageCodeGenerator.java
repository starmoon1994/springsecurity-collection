package com.company.testss13.security.validate.code.image;

import com.company.testss13.security.CustomSecurityProperties;
import com.company.testss13.security.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author starmoon1994
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {
    // 验证码长度
    private static final int codeLength = 4;
    private static final int defaultWidth = 60;
    private static final int defaultHeight = 25;


    /**
     * 系统配置
     */
    @Autowired
    private CustomSecurityProperties securityProperties;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.company.testss13.security.core.validate.code.ValidateCodeGenerator#generate(org.
     * springframework.web.context.request.ServletWebRequest)
     */
    @Override
    public ImageCode generate(ServletWebRequest request) {

        //TODO:配置化
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
                defaultWidth);
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
                defaultHeight);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < codeLength; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(image, sRand, CustomSecurityProperties.VALIDATE_CODE_EXPIREIN);
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public CustomSecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(CustomSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


}
