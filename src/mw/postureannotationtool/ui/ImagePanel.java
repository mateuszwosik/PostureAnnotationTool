package mw.postureannotationtool.ui;

import mw.postureannotationtool.utils.Image;
import mw.postureannotationtool.ui.model.Person;
import mw.postureannotationtool.ui.model.Posture;

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
import java.util.List;
import java.util.Map;

//TODO Split this class to View and Controller (REFACTORING NEEDED!!!)
public class ImagePanel extends JPanel {

    private Image image;

    private JLabel pointNameLabel;
    private JLabel pointsCountLabel;
    private JLabel helperImageLabel;
    private JPanel saveAnnotationPanel;

    private Person person;
    private List<Person> personList;
    private int pointsCounter;
    private List<String> pointsOrder;
    private Map<String, String> pointsNames;
    private Map<String, String> pointsImages;

    private ArrayList<Shape> shapes;

    private Point startDrag, endDrag;

    private MouseAdapter mouseMoveAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX() > image.getWidth() ? image.getWidth() : e.getX();
            x = x < 0 ? 0 : x;
            int y = e.getY() > image.getHeight() ? image.getHeight() : e.getY();
            y = y < 0 ? 0 : y;
            startDrag = new Point(e.getX(), e.getY());
            endDrag = startDrag;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int x = e.getX() > image.getWidth() ? image.getWidth() : e.getX();
            x = x < 0 ? 0 : x;
            int y = e.getY() > image.getHeight() ? image.getHeight() : e.getY();
            y = y < 0 ? 0 : y;
            shapes.add(createRectangle(startDrag.x, startDrag.y, x, y));
            person.setPersonRect(startDrag.x*image.getWidthAspectRatio(), startDrag.y*image.getHeightAspectRatio(),
                    x*image.getWidthAspectRatio(), y*image.getHeightAspectRatio());
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
            int x = e.getX() > image.getWidth() ? image.getWidth() : e.getX();
            x = x < 0 ? 0 : x;
            int y = e.getY() > image.getHeight() ? image.getHeight() : e.getY();
            y = y < 0 ? 0 : y;
            shapes.add(createEllipse(x, y));
            person.addPoint(new Posture.Point(pointsOrder.get(pointsCounter++),
                    x*image.getWidthAspectRatio(), y*image.getHeightAspectRatio()));
            if (pointsCounter < pointsOrder.size()) {
                setHelperImage(pointsImages.get(pointsOrder.get(pointsCounter)));
                setPointsNameLabel(pointsNames.get(pointsOrder.get(pointsCounter)));
                setPointsCountLabelText(pointsCounter+1,pointsOrder.size());
            } else {
                personList.add(person);
                stopDrawingPoints();
                setHelperImage(null);
                setPointsNameLabel("Point name: ");
                saveAnnotationPanel.setVisible(true);
            }
            repaint();
        }
    };

    public ImagePanel() {
        super();
        image = null;
        person = null;
        personList = null;
        pointsCounter = -1;
        pointsOrder = null;
        pointsNames = null;
        pointsImages = null;
        shapes = null;
    }

    public void setPointsComponents(JLabel pointNameLabel, JLabel pointsCountLabel, JLabel helperImageLabel) {
        this.pointNameLabel = pointNameLabel;
        this.pointsCountLabel = pointsCountLabel;
        this.helperImageLabel = helperImageLabel;
    }

    public void setSaveAnnotationPanel(JPanel saveAnnotationsPanel){
        this.saveAnnotationPanel = saveAnnotationsPanel;
    }

    public void setImage(File file) {
        if (file == null) {
            image = null;
        } else {
            try {

                image = Image.scaleImage(ImageIO.read(file), BufferedImage.TYPE_INT_RGB, 250, 550);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        personList = new ArrayList<>();
        shapes = new ArrayList<>();
        repaint();
    }

    public void startDrawingRectangle(int personId) {
        if (person != null && pointsOrder != null && pointsCounter == pointsOrder.size()-1) {
            personList.add(person);
            pointsCounter = -1;
        }
        person = new Person(personId);
        addMouseListener(mouseMoveAdapter);
        addMouseMotionListener(mouseMotionAdapter);
    }

    public void stopDrawingRectangle() {
        removeMouseListener(mouseMoveAdapter);
        removeMouseMotionListener(mouseMotionAdapter);
    }

    public void startDrawingPoints(Posture.PostureType postureType) {
        setPostureParameters(postureType);
        pointsCounter = 0;
        person.setPostureType(postureType);
        setHelperImage(pointsImages.get(pointsOrder.get(pointsCounter)));
        setPointsNameLabel(pointsNames.get(pointsOrder.get(pointsCounter)));
        setPointsCountLabelText(pointsCounter+1,pointsOrder.size());
        addMouseListener(mouseClickedAdapter);
    }

    public void stopDrawingPoints() {
        removeMouseListener(mouseClickedAdapter);
    }

    public List<Person> getPersonList(){
        return personList;
    }

    private void setPostureParameters(Posture.PostureType postureType) {
        switch (postureType) {
            case FRONT:
                pointsOrder = Posture.FRONT_POINTS_ORDER;
                pointsNames = Posture.FRONT_POINTS_NAMES;
                pointsImages = Posture.FRONT_POINTS_IMAGES;
                break;
            case BACK:
                pointsOrder = Posture.BACK_POINTS_ORDER;
                pointsNames = Posture.BACK_POINTS_NAMES;
                pointsImages = Posture.BACK_POINTS_IMAGES;
                break;
            case RIGHT:
                pointsOrder = Posture.SIDE_POINTS_ORDER;
                pointsNames = Posture.SIDE_POINTS_NAMES;
                pointsImages = Posture.SIDE_RIGHT_POINTS_IMAGES;
                break;
            case LEFT:
                pointsOrder = Posture.SIDE_POINTS_ORDER;
                pointsNames = Posture.SIDE_POINTS_NAMES;
                pointsImages = Posture.SIDE_LEFT_POINTS_IMAGES;
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.drawImage(image.getBufferedImage(), 0, 0, this);

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

    private Rectangle2D.Float createRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1,x2), Math.min(y1,y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private Ellipse2D.Float createEllipse(int x, int y) {
        return new Ellipse2D.Float(x-2, y-2, 4, 4);
    }

    private void setHelperImage(String imagePath){
        helperImageLabel.setIcon(new ImageIcon(imagePath));
    }

    private void setPointsNameLabel(String pointName) {
        pointNameLabel.setText(pointName);
    }

    private void setPointsCountLabelText(int currentPoint, int pointsCount) {
        pointsCountLabel.setText(currentPoint + "/" + pointsCount);
    }

}
