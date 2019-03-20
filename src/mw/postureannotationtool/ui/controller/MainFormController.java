package mw.postureannotationtool.ui.controller;

import mw.postureannotationtool.ui.ImagePanel;
import mw.postureannotationtool.ui.model.Posture;
import mw.postureannotationtool.ui.view.MainForm;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainFormController {



    private MainForm mainForm;

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

    private File directory;
    private List<File> files;

    public MainFormController() {
        initComponents();
        initListeners();

        directory = null;
        files = null;
    }

    public void showMainFormWindow(){
        mainForm.setVisible(true);
    }

    private void initComponents() {
        mainForm = new MainForm();

        mainPanel = mainForm.getMainPanel();
        openImageButton = mainForm.getOpenImageButton();
        frontRadioButton = mainForm.getFrontRadioButton();
        backRadioButton = mainForm.getBackRadioButton();
        rightRadioButton = mainForm.getRightRadioButton();
        leftRadioButton = mainForm.getLeftRadioButton();
        selectPersonButton = mainForm.getSelectPersonButton();
        selectPointsButton = mainForm.getSelectPointsButton();
        saveLabelsButton = mainForm.getSaveLabelsButton();
        filesCountLabel = mainForm.getFilesCountLabel();
        nextImageButton = mainForm.getNextImageButton();
        imageNameLabel = mainForm.getImageNameLabel();
        panel = mainForm.getPanel();
        pointsCountLabel = mainForm.getPointsCountLabel();
        imagePanel = mainForm.getImagePanel();
        helperImageLabel = mainForm.getHelperImageLabel();
    }

    private void initListeners(){
        openImageButton.addActionListener(e -> openImages());
        saveLabelsButton.addActionListener(e -> {
            saveAnnotations();
            loadNextImage();
        });
        nextImageButton.addActionListener(e -> loadNextImage());
        selectPersonButton.addActionListener(e -> selectPerson());
        selectPointsButton.addActionListener(e -> selectPoints());
    }

    private void loadNextImage() {
        if (files.size() == 1) {
            files.remove(0);
            displayImage(null);
            filesCountLabel.setText("Number of files left: " + (files.size()));
        } else if (files.size() > 1) {
            files.remove(0);
            displayImage(files.get(0));
            filesCountLabel.setText("Number of files left: " + (files.size()));
        }
    }

    private void saveAnnotations() {

    }

    private void openImages() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Select directory with images to annotate");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        if (fileChooser.showOpenDialog(mainForm) == JFileChooser.APPROVE_OPTION) {
            directory = fileChooser.getSelectedFile();
            files = getImageFiles(directory.listFiles());
            if (files != null && !files.isEmpty()) {
                filesCountLabel.setText("Number of files left: " + files.size());
                displayImage(files.get(0));
            }
        }
    }

    private void displayImage(File file) {
        if (file == null) {
            imageNameLabel.setText("Image name:");
            imagePanel.setImage(null);
            imagePanel.stopDrawingPoints();
        } else if (file.isFile()) {
            imageNameLabel.setText("Image name: " + file.getName());
            imagePanel.setImage(file);
            imagePanel.stopDrawingPoints();
        }
    }

    private LinkedList<File> getImageFiles(File[] files) {
        LinkedList<File> imageFiles = new LinkedList<>();
        Arrays.stream(files)
                .filter(File::isFile)
                .filter(f -> f.getName().matches(".+\\.(jpg|jpeg|png)"))
                .forEach(imageFiles::add);
        return imageFiles;
    }

    private void selectPerson(){
        imagePanel.startDrawingRectangle();
    }

    private void selectPoints(){
        if (frontRadioButton.isSelected() || backRadioButton.isSelected() || rightRadioButton.isSelected() || leftRadioButton.isSelected()) {
            ImageIcon icon = new ImageIcon();
            if (frontRadioButton.isSelected()) {
                icon = new ImageIcon(Posture.FRONT_POINTS_IMAGES.get("RIGHT_EYE"));
            }
            if (backRadioButton.isSelected()) {
                icon = new ImageIcon(Posture.BACK_POINTS_IMAGES.get("C7"));
            }
            if (rightRadioButton.isSelected()) {
                icon = new ImageIcon(Posture.SIDE_POINTS_IMAGES.get("EAR"));
            }
            if (leftRadioButton.isSelected()) {
                icon = new ImageIcon(Posture.SIDE_POINTS_IMAGES.get("EAR"));
            }
            helperImageLabel.setIcon(icon);
            imagePanel.startDrawingPoints();
        } else {
            JOptionPane.showMessageDialog(mainForm,"Select posture type");
        }
    }

}
