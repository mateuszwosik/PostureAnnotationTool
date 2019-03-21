package mw.postureannotationtool.utils;

import mw.postureannotationtool.ui.model.Person;
import mw.postureannotationtool.ui.model.Posture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWriter {

    private static final String LABELS_DIRECTORY = "labels";
    private static final String IMAGES_DIRECTORY = "images";

    public void saveToCSVFile(File directory, File file, List<Person> personList) {
        String postureType = personList.get(0).getPostureType().getName();
        String parentPath = directory.getPath() + "/" + postureType;
        File parentDirectory = new File(parentPath + "/" + LABELS_DIRECTORY);
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
        String annotationFileName = file.getName().split("\\.")[0].concat(".csv");
        try (PrintWriter fileWriter = new PrintWriter(parentDirectory.getPath() +
                 "/" + annotationFileName)) {
            StringBuilder sb = new StringBuilder();

            sb.append(file.getName());
            sb.append("\n");

            sb.append(formatPersonListToString(personList));

            fileWriter.append(sb.toString());
            fileWriter.flush();

            moveImage(file, parentPath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void moveImage(File file, String targetParentDirectoryPath) {
        File parentDirectory = new File(targetParentDirectoryPath + "/" + IMAGES_DIRECTORY);
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
        try {
            Files.move(file.toPath(), Paths.get(parentDirectory.getPath() + "/" + file.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatPersonListToString(List<Person> personList) {
        StringBuilder sb = new StringBuilder();
        for (Person person : personList) {
            sb.append(formatPersonToString(person));
            sb.append("\n");
        }
        return sb.toString();
    }

    private String formatPersonToString(Person person) {
        StringBuilder sb = new StringBuilder();

        sb.append(person.getId());
        sb.append(";");
        sb.append(person.getX1());
        sb.append(";");
        sb.append(person.getY1());
        sb.append(";");
        sb.append(person.getX2());
        sb.append(";");
        sb.append(person.getY2());
        sb.append("\n");

        sb.append(person.getPostureType().getName());
        sb.append("\n");

        sb.append(person.getPoints().size());
        sb.append("\n");

        sb.append(formatPointsToString(person.getPoints()));

        return sb.toString();
    }

    private String formatPointsToString(List<Posture.Point> points) {
        StringBuilder sb = new StringBuilder();
        for (Posture.Point point : points) {
            sb.append(point.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}
