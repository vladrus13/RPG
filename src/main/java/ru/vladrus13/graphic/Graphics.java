package ru.vladrus13.graphic;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Graphics {

    void setColor(Color color);

    void setFont(Font font);

    void drawString(String text, long x, long y);

    void fillRect(long x, long y, long x1, long y1);

    void drawImage(BufferedImage image, long x, long y, long x1, long y1);
}
