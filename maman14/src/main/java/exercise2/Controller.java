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

    private void renderDictionary(String filterTerm) {
        termsList.getChildren().clear();
        Iterator<Entry<String, String>> i = dict.iterator();

        while (i.hasNext()) {
            Entry<String, String> entry = i.next();
            if (entry.getKey().contains(filterTerm)) {
                Text text = new Text(entry.getKey() + ": " + entry.getValue());
                text.setOnMouseClicked(e -> {
                    termField.setText(entry.getKey());
                    meaningField.setText(entry.getValue());
                });
                text.setCursor(Cursor.HAND);
                text.setOnMouseEntered(e -> {
                    text.setUnderline(true);
                });
                text.setOnMouseExited(e -> {
                    text.setUnderline(false);
                });
                termsList.getChildren().add(text);
            }
        }
    }

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

    @FXML
    void handleSearch(KeyEvent event) {
        renderDictionary(searchField.getText());
    }

    @FXML
    void handleExport(ActionEvent event) {
        dict.exportDict(getFile());
    }

    @FXML
    void handleImport(ActionEvent event) {
        dict.importDict(getFile());
        renderDictionary(searchField.getText());
    }

    private File getFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select a file");
        fileChooser.setInitialDirectory(new File("."));
        return fileChooser.showOpenDialog(null);
    }

}
