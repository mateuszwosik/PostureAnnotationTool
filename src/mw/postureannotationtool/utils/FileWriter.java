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
    private static final String TRASH_DIRECTORY = "trash";

    public void saveToCSVAndMoveFile(File directory, File file, List<Person> personList) {
        String postureType = personList.get(0).getPostureType().getName();
        String parentPath = directory.getPath() + "/" + postureType;
        File parentDirectory = new File(parentPath + "/" + LABELS_DIRECTORY);
        if (!parentDirectory.exists()) {
            //noinspection ResultOfMethodCallIgnored
            parentDirectory.mkdirs();
        }
        String annotationFileName = file.getName().split("\\.")[0].concat(".csv");
        try (PrintWriter fileWriter = new PrintWriter(parentDirectory.getPath() +
                 "/" + annotationFileName)) {

            String sb = file.getName() +
                    "\n" +
                    formatPersonListToString(personList);

            fileWriter.append(sb);
            fileWriter.flush();

            moveImage(file, parentPath, IMAGES_DIRECTORY);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void moveImageToTrash(File file, File directory) {
        moveImage(file, directory.getPath(), TRASH_DIRECTORY);
    }

    private void moveImage(File file, String targetParentDirectoryPath, String directoryName) {
        File parentDirectory = new File(targetParentDirectoryPath + "/" + directoryName);
        if (!parentDirectory.exists()) {
            //noinspection ResultOfMethodCallIgnored
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
        return person.getId() +
                ";" +
                person.getX1() +
                ";" +
                person.getY1() +
                ";" +
                person.getX2() +
                ";" +
                person.getY2() +
                "\n" +
                person.getPostureType().getName() +
                "\n" +
                person.getPoints().size() +
                "\n" +
                formatPointsToString(person.getPoints());
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
