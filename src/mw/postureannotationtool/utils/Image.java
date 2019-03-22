package mw.postureannotationtool.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Image {

    private final BufferedImage bufferedImage;
    private final int width;
    private final int height;
    private final float widthAspectRatio;
    private final float heightAspectRatio;

    public Image(BufferedImage bufferedImage, float widthAspectRatio, float heightAspectRatio) {
        this.bufferedImage = bufferedImage;
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();
        this.widthAspectRatio = widthAspectRatio;
        this.heightAspectRatio = heightAspectRatio;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getWidthAspectRatio() {
        return widthAspectRatio;
    }

    public float getHeightAspectRatio() {
        return heightAspectRatio;
    }

    public static Image scaleImage(BufferedImage image, int imageType, int newWidth, int newHeight) {
        // Make sure the aspect ratio is maintained, so the image is not distorted
        double thumbRatio = (double) newWidth / (double) newHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double aspectRatio = (double) imageWidth / (double) imageHeight;

        if (thumbRatio < aspectRatio) {
            newHeight = (int) (newWidth / aspectRatio);
        } else {
            newWidth = (int) (newHeight * aspectRatio);
        }

        float widthAspectRatio = (float)imageWidth / (float)newWidth;
        float heightAspectRatio = (float)imageHeight / (float)newHeight;

        // Draw the scaled image
        BufferedImage newImage = new BufferedImage(newWidth, newHeight,
                imageType);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);

        return new Image(newImage, widthAspectRatio, heightAspectRatio);
    }

}
