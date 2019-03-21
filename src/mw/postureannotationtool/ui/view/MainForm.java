package mw.postureannotationtool.ui.view;

import mw.postureannotationtool.ui.ImagePanel;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 650;

    private JPanel mainPanel;
    private JButton openImageButton;
    private JRadioButton frontRadioButton;
    private JRadioButton backRadioButton;
    private JRadioButton rightRadioButton;
    private JRadioButton leftRadioButton;
    private JButton selectPersonButton;
    private JButton selectPointsButton;
    private JButton saveLabelsButton;
    private JLabel filesCountLabel;
    private JButton nextImageButton;
    private JLabel imageNameLabel;
    private JPanel panel;
    private JLabel pointsCountLabel;
    private ImagePanel imagePanel;
    private JLabel helperImageLabel;
    private JPanel selectPersonPanel;
    private JPanel selectPointsPanel;
    private JPanel saveAnnotationsPanel;
    private JLabel pointNameLabel;

    public MainForm(){
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("PostureAnnotationTool");
        setContentPane(mainPanel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getOpenImageButton() {
        return openImageButton;
    }

    public JRadioButton getFrontRadioButton() {
        return frontRadioButton;
    }

    public JRadioButton getBackRadioButton() {
        return backRadioButton;
    }

    public JRadioButton getRightRadioButton() {
        return rightRadioButton;
    }

    public JRadioButton getLeftRadioButton() {
        return leftRadioButton;
    }

    public JButton getSelectPersonButton() {
        return selectPersonButton;
    }

    public JButton getSelectPointsButton() {
        return selectPointsButton;
    }

    public JButton getSaveLabelsButton() {
        return saveLabelsButton;
    }

    public JLabel getFilesCountLabel() {
        return filesCountLabel;
    }

    public JButton getNextImageButton() {
        return nextImageButton;
    }

    public JLabel getImageNameLabel() {
        return imageNameLabel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getPointsCountLabel() {
        return pointsCountLabel;
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public JLabel getHelperImageLabel() {
        return helperImageLabel;
    }

    public JPanel getSelectPersonPanel() {
        return selectPersonPanel;
    }

    public JPanel getSelectPointsPanel() {
        return selectPointsPanel;
    }

    public JPanel getSaveAnnotationsPanel() {
        return saveAnnotationsPanel;
    }

    public JLabel getPointNameLabel() {
        return pointNameLabel;
    }
}
