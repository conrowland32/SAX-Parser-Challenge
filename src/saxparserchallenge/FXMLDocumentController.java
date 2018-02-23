/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparserchallenge;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

/**
 *
 * @author Connor Rowland
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea textArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        if (file != null) {
            try {
                DOMObject dom = DOMObjectXMLLoader.load(file, textArea);
                System.out.println("DOM Loaded.");
                HashMap<Integer, DomElement> elementMap = dom.getList();
                for(int i = 0; i < elementMap.size(); i++) {
                    this.printElement(elementMap.get(i));
                }
            } catch (Exception ex) {
                displayExceptionAlert("Exception parsing XML file.", ex);
            }
        }
    }

    private void printElement(DomElement element) {
        for(int i = 0; i < element.getLevel(); i++) {
            textArea.appendText("\t");
        }
        textArea.appendText("<" + element.getName());
        HashMap<String, String> attributes = element.getAttributes();
        for(String name : attributes.keySet()) {
            textArea.appendText(" " + name + "=\"" + attributes.get(name) + "\"");
        }
        textArea.appendText("> " + element.getValue() + "\n");
    }
    
    @FXML
    private void displayAboutAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("XML SAX Parser");
        alert.setContentText("This application was developed by Connor Rowland for the SAX Parser Challenge for CS4330 at the University of Missouri.");
        
        alert.showAndWait();
    }
    
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(message);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea alertText = new TextArea(exceptionText);
        alertText.setEditable(false);
        alertText.setWrapText(true);

        alertText.setMaxWidth(Double.MAX_VALUE);
        alertText.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(alertText, Priority.ALWAYS);
        GridPane.setHgrow(alertText, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(alertText, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
    
}
