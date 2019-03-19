package mw.postureannotationtool;

import mw.postureannotationtool.ui.controller.MainFormController;

import javax.swing.*;

public class Program {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainFormController mainFormController = new MainFormController();
            mainFormController.showMainFormWindow();
        });

    }

}
