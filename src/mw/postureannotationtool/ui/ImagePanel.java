package mw.postureannotationtool.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    ArrayList<Shape> shapes;

    Point startDrag, endDrag;

    private MouseAdapter mouseMoveAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            startDrag = new Point(e.getX(), e.getY());
            endDrag = startDrag;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            shapes.add(createRectangle(startDrag.x, startDrag.y, e.getX(), e.getY()));
            startDrag = null;
            endDrag = null;
            removeMouseListener(this);
            removeMouseMotionListener(mouseMotionAdapter);
            repaint();
        }

    };

    private MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            endDrag = new Point(e.getX(), e.getY());
            repaint();
        }
    };

    private MouseAdapter mouseClickedAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            shapes.add(createEllipse(e.getX(), e.getY()));
            repaint();
        }
    };

    public ImagePanel() {
        super();
        image = null;
        shapes = null;
    }

    public void setImage(File file) {
        if (file == null) {
            image = null;
        } else {
            try {
                image = scaleImage(ImageIO.read(file), BufferedImage.TYPE_INT_RGB, 250, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        shapes = new ArrayList<>();
        repaint();
    }

    public void startDrawingRectangle() {
        addMouseListener(mouseMoveAdapter);
        addMouseMotionListener(mouseMotionAdapter);
    }

    public void startDrawingPoints() {
        addMouseListener(mouseClickedAdapter);
    }

    public void stopDrawingPoints() {
        removeMouseListener(mouseClickedAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.drawImage(image, 0, 0, this);

            for (Shape shape : shapes){
                graphics2D.setColor(Color.RED);
                graphics2D.draw(shape);
                if (shape instanceof Ellipse2D)
                    graphics2D.fill(shape);
            }

            if (startDrag != null && endDrag != null) {
                graphics2D.setColor(Color.LIGHT_GRAY);
                graphics2D.draw(createRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y));
            }

            graphics2D.dispose();
        }
    }

    private BufferedImage scaleImage(BufferedImage image, int imageType, int newWidth, int newHeight) {
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

        // Draw the scaled image
        BufferedImage newImage = new BufferedImage(newWidth, newHeight,
                imageType);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);

        return newImage;
    }

    private Rectangle2D.Float createRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private Ellipse2D.Float createEllipse(int x, int y) {
        return new Ellipse2D.Float(x-2, y-2, 4, 4);
    }

}
