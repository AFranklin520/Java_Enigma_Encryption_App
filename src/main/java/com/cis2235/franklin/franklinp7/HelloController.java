//Anthony Franklin afranklin18@cnm.edu
//FranklinP8
//04/07/2022
//HelloController.java

package com.cis2235.franklin.franklinp7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController {

    Enigma[] enig = new Enigma[3];
    GEnigma ge = new GEnigma();
    Primes pe = new Primes();
    HighlanderEnigma he = new HighlanderEnigma();
    private int key;
    private int index;
    private String enigType;
    private String message = "";
    private String codedMessage = "";


    @FXML
    private MenuItem about;

    @FXML
    private Button btnClear;

    @FXML
    private MenuButton btnEType;

    @FXML
    private ToggleGroup grpEType;

    @FXML
    private Button btnDecode;

    @FXML
    private Button btnEncode;

    @FXML
    private ToggleGroup grpKey;

    @FXML
    private MenuItem menuFile;

    @FXML
    private MenuBar menuFileMenu;

    @FXML
    private Menu menuHelp;

    @FXML
    private MenuItem openFile;

    @FXML
    private RadioButton radManual;

    @FXML
    private RadioButton radRand;

    @FXML
    private RadioMenuItem rdbGEnig;

    @FXML
    private RadioMenuItem rdbPEnig;

    @FXML
    private RadioMenuItem rdbHEnig;

    @FXML
    private TextField txbCodedMessage;

    @FXML
    private TextField txbKey;

    @FXML
    private TextField txbMessage;

    @FXML
    private TextField txbSummaryKey;

    @FXML
    public void initialize() {
        enig[0] = ge;
        enig[1] = pe;
        enig[2] = he;
    }

    @FXML
    void onAboutClick(ActionEvent event) throws IOException {
        try {
            File file = new File("help.txt");
            java.awt.Desktop.getDesktop().edit(file);
        } catch (IOException ex) {

        }
    }

    @FXML
    void onClear(ActionEvent event) {
        txbSummaryKey.clear();
        txbMessage.clear();
        txbCodedMessage.clear();
        txbKey.clear();

    }

    @FXML
    void onDecode(ActionEvent event) {

        if (!(btnEType.getText() == "Primes Enigma" || btnEType.getText() == "GEnigma" || btnEType.getText() == "Highlander Enigma")) {
            if (rdbGEnig.isSelected()) {
                    index = 0;
                    enigType = "GEnigma";
                } else if (rdbPEnig.isSelected()) {
                    index = 1;
                    enigType = "Primes";
                } else {
                    index = 2;
                    enigType = "HighlanderEnigma";
                }
        }
        if (rdbGEnig.isSelected()) {
            index = 0;
            enigType = "GEnigma";
        } else if (rdbPEnig.isSelected()) {
            index = 1;
            enigType = "Primes";
        } else {
            index = 2;
            enigType = "HighlanderEnigma";
        }
        key = Integer.parseInt(txbSummaryKey.getText());
        codedMessage = txbCodedMessage.getText();
        enig[index].setCodedMessage(codedMessage, key);
        txbMessage.setText(enig[index].getMessage());
    }


    @FXML
    void onEncode(ActionEvent event) {
        if(rdbGEnig.isSelected() || rdbPEnig.isSelected() || rdbHEnig.isSelected())
        {
            if (rdbGEnig.isSelected()) {
                index = 0;
                enigType = "GEnigma";
            } else if (rdbPEnig.isSelected()) {
                index = 1;
                enigType = "Primes Enigma";
            } else {
                index = 2;
                enigType = "HighlanderEnigma";
            }
            if (radRand.isSelected()) {
                message = txbMessage.getText();
                enig[index].setMessage(message);
            } else {
                key = Integer.parseInt(txbKey.getText());
                message = txbMessage.getText();
                enig[index].setMessage(message, key);
            }
            txbCodedMessage.setText(enig[index].getCodedMessage());
            txbSummaryKey.setText(String.valueOf(enig[index].getKey()));
        }
        else JOptionPane.showMessageDialog(null, "[ERROR] Please Select an Enigma Type from the Menu Button Above Before Proceeding!");
    }


    @FXML
    void onFileClick(ActionEvent event) {
        //Create FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save a Coded Message in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show the Save File Dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            PrintWriter outputFile = null;
            try {
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                outputFile = new PrintWriter(filename);
                outputFile.println("\n" + enig[index].getCodedMessage());
                outputFile.println("\n" + enig[index].getKey());
                outputFile.println("\n" + index);
                outputFile.flush();
                outputFile.close();

            } catch (IOException ex) {
                Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    @FXML
    void onManualKey(ActionEvent event) {

        txbKey.setEditable(true);
    }

    @FXML
    void onRandKey(ActionEvent event) {
        txbKey.clear();
        txbKey.setEditable(false);
    }
    @FXML
    void onGEnigSelect(ActionEvent event) {
        enigType = "GEnig";
        index = 0;
        btnEType.setText(enigType);
    }

    @FXML
    void onHighSelect(ActionEvent event) {
        enigType = "Highlander Enigma";
        index = 1;
        btnEType.setText(enigType);
    }

    @FXML
    void onPrimesSelect(ActionEvent event) {
        enigType = "Primes Enigma";
        index = 0;
        btnEType.setText(enigType);
    }

    public void onFileOpenClick(ActionEvent actionEvent) {
        //Create FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open a File to Decode.");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show the Open File Dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                Scanner inputFile = new Scanner(myFile);
                txbCodedMessage.setText(inputFile.nextLine());
                txbSummaryKey.setText(String.valueOf(inputFile.nextInt()));
                index = Integer.parseInt(String.valueOf(inputFile.nextInt()));
                switch (index) {
                    case 0:
                        enigType = "GEnigma";
                        rdbGEnig.setSelected(true);
                        break;
                    case 1:
                        enigType = "Primes Enigma";
                        rdbPEnig.setSelected(true);
                        break;
                    default:
                        enigType = "Highlander Enigma";
                        rdbHEnig.setSelected(true);
                        break;
                }

            } catch (IOException ex) {
                Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            btnEType.setText(enigType);
        }
    }
}
