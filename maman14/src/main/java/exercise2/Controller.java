package exercise2;

import java.io.File;
import java.util.Iterator;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class Controller {

    @FXML
    private TextField termField;
    @FXML
    private TextArea meaningField;
    @FXML
    private TextField searchField;
    @FXML
    private VBox termsList;

    private Dictionary dict;

    /**
     * initialize screen properties
     */
    @FXML
    public void initialize() {
        dict = new Dictionary();
    }

    /**
     * a method that displays all the terms and meaning in the dictionary to the
     * screen.
     * 
     * @param filterTerm: a search input to filter the terms.
     */
    private void renderDictionary(String filterTerm) {
        termsList.getChildren().clear();
        Iterator<Entry<String, String>> i = dict.iterator();

        while (i.hasNext()) {
            Entry<String, String> entry = i.next();
            if (entry.getKey().contains(filterTerm)) {
                Text text = new Text(entry.getKey() + ": " + entry.getValue());
                // fill selected term details in the text fields
                text.setOnMouseClicked(e -> {
                    termField.setText(entry.getKey());
                    meaningField.setText(entry.getValue());
                });
                // set style on hover
                text.setCursor(Cursor.HAND);
                text.setOnMouseEntered(e -> {
                    text.setUnderline(true);
                });
                text.setOnMouseExited(e -> {
                    text.setUnderline(false);
                });
                // add to screen
                termsList.getChildren().add(text);
            }
        }
    }

    /**
     * Delete button handler - delete selected term
     * 
     * @param event
     */
    @FXML
    void handleDelete(ActionEvent event) {
        String term = termField.getText();
        if (term != null) {
            dict.delete(term);
            termField.clear();
            meaningField.clear();
            renderDictionary(searchField.getText());
        }
    }

    /**
     * Save button handler - saves a new or existing term from the input fields
     * 
     * @param event
     */
    @FXML
    void handleSave(ActionEvent event) {
        String term = termField.getText();
        String meaning = meaningField.getText();
        if (term != null && meaning != null) {
            dict.save(term, meaning);
            termField.clear();
            meaningField.clear();
            renderDictionary(searchField.getText());
        }
    }

    /**
     * Search text field handler - filter and display terms according to the input
     * 
     * @param event
     */
    @FXML
    void handleSearch(KeyEvent event) {
        renderDictionary(searchField.getText());
    }

    /**
     * Export button handler - export dictionary content to file you choose
     * 
     * @param event
     */
    @FXML
    void handleExport(ActionEvent event) {
        dict.exportDict(getFile());
    }

    /**
     * Import button handler - import dictionary contet from file you choose
     * 
     * @param event
     */
    @FXML
    void handleImport(ActionEvent event) {
        dict.importDict(getFile());
        renderDictionary(searchField.getText());
    }

    /**
     * prompts a dialog that asks for a file path
     * 
     * @return
     */
    private File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select a file");
        fileChooser.setInitialDirectory(new File("."));
        return fileChooser.showOpenDialog(null);
    }

}
