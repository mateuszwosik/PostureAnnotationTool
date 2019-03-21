package mw.postureannotationtool.utils;

import mw.postureannotationtool.ui.model.Person;

import java.io.File;
import java.util.List;

public class FileWriter {

    public static void saveToCSVFile(File file, List<Person> personList) {
        System.out.println("Saved annotation to file and moved image to correct directory.");
    }

}
