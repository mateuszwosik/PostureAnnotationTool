package mw.postureannotationtool.ui.controller;

import mw.postureannotationtool.ui.ImagePanel;
import mw.postureannotationtool.ui.model.Posture;
import mw.postureannotationtool.ui.view.MainForm;
import mw.postureannotationtool.utils.FileWriter;

import javax.swing.*;
import java.io.File;
import java.util.*;

public class MainFormController {



    private MainForm mainForm;

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
    private ImagePanel imagePanel;
    private JPanel selectPersonPanel;
    private JPanel selectPointsPanel;
    private JPanel saveAnnotationsPanel;

    private File directory;
    private List<File> files;

    private FileWriter fileWriter;

    private int personCounter;

    public MainFormController() {
        initComponents();
        initListeners();

        directory = null;
        files = null;

        fileWriter = new FileWriter();
    }

    public void showMainFormWindow(){
        mainForm.setVisible(true);
    }

    private void hideMenuPanel() {
        selectPersonPanel.setVisible(false);
        selectPointsPanel.setVisible(false);
        saveAnnotationsPanel.setVisible(false);
    }

    private void initComponents() {
        mainForm = new MainForm();

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
        JLabel pointsCountLabel = mainForm.getPointsCountLabel();
        imagePanel = mainForm.getImagePanel();
        JLabel helperImageLabel = mainForm.getHelperImageLabel();
        selectPersonPanel = mainForm.getSelectPersonPanel();
        selectPointsPanel = mainForm.getSelectPointsPanel();
        saveAnnotationsPanel = mainForm.getSaveAnnotationsPanel();
        JLabel pointNameLabel = mainForm.getPointNameLabel();

        imagePanel.setPointsComponents(pointNameLabel, pointsCountLabel, helperImageLabel);
        imagePanel.setSaveAnnotationPanel(saveAnnotationsPanel);

        hideMenuPanel();
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
        personCounter = 0;
        selectPointsPanel.setVisible(false);
        saveAnnotationsPanel.setVisible(false);
        if (files.size() == 1) {
            files.remove(0);
            displayImage(null);
            filesCountLabel.setText("Number of files left: " + (files.size()));
            selectPersonPanel.setVisible(true);
        } else if (files.size() > 1) {
            files.remove(0);
            displayImage(files.get(0));
            filesCountLabel.setText("Number of files left: " + (files.size()));
            selectPersonPanel.setVisible(true);
        }
    }

    private void saveAnnotations() {
        selectPointsPanel.setVisible(false);
        saveAnnotationsPanel.setVisible(false);
        fileWriter.saveToCSVAndMoveFile(directory, files.get(0), imagePanel.getPersonList());
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
                personCounter = 0;
                displayImage(files.get(0));
                selectPersonPanel.setVisible(true);
            }
        }
    }

    private void displayImage(File file) {
        if (file == null) {
            imageNameLabel.setText("Image name:");
            imagePanel.setImage(null);
            imagePanel.stopDrawingRectangle();
            imagePanel.stopDrawingPoints();
        } else if (file.isFile()) {
            imageNameLabel.setText("Image name: " + file.getName());
            imagePanel.setImage(file);
            imagePanel.stopDrawingRectangle();
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
        imagePanel.startDrawingRectangle(personCounter++);
        selectPointsPanel.setVisible(true);
    }

    private void selectPoints(){
        if (frontRadioButton.isSelected() || backRadioButton.isSelected() || rightRadioButton.isSelected() || leftRadioButton.isSelected()) {
            Posture.PostureType postureType = null;
            if (frontRadioButton.isSelected()) {
                postureType = Posture.PostureType.FRONT;
            }
            if (backRadioButton.isSelected()) {
                postureType = Posture.PostureType.BACK;
            }
            if (rightRadioButton.isSelected()) {
                postureType = Posture.PostureType.RIGHT;
            }
            if (leftRadioButton.isSelected()) {
                postureType = Posture.PostureType.LEFT;
            }
            imagePanel.startDrawingPoints(postureType);
        } else {
            JOptionPane.showMessageDialog(mainForm,"Select posture type");
        }
    }

}
