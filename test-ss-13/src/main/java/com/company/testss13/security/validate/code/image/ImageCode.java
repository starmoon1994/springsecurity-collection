package com.company.testss13.security.validate.code.image;

import com.company.testss13.security.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author starmoon1994
 */
public class ImageCode extends ValidateCode implements Serializable {
    private static final long serialVersionUID = 14562445177063596L;
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
