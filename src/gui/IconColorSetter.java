package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IconColorSetter extends ImageIcon {
    private String iconPath, color;

    public IconColorSetter(){}

    public IconColorSetter(String iconPath, String color){
        this.iconPath = iconPath;
        this.color = color;
    }

    public BufferedImage setIconColor(String iconPath, String color) throws IOException {
        BufferedImage img = ImageIO.read(new File(iconPath));
        BufferedImage coloredImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Color targetColor = Color.decode(color);

        Graphics2D g2d = coloredImg.createGraphics();
        g2d.setColor(targetColor);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                Color c = new Color(rgb, true);
                if (c.getAlpha() > 0) {
                    float alpha = c.getAlpha() / 255.0f;
                    g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }
        g2d.dispose();
        return coloredImg;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
