/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed following in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.seed.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import local.example.seed.model.Seed;
import local.example.seed.view.alert.InvalidFormAlertView;

public class SeedEditorController {

    private final Pattern idPattern;
    private final Pattern namePattern;
    private final Pattern percentagePattern;

    private Stage editorStage;
    private Seed editedSeed;
    private boolean onClicked = false;
    private String eventualityErrorMessage = "";

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField percentageField;

    @FXML
    private TextField createdField;

    public SeedEditorController() {
        this.idPattern = Pattern.compile("[\\d]+");
        this.namePattern = Pattern.compile("[\\w]");
        this.percentagePattern = Pattern.compile("0.[\\d]");
    }

    @FXML
    private void initialize() {}

    private boolean isValidForm() {
        return this.isValidId()
                && this.isValidName()
                && this.isValidPercentage();
    }

    private boolean isValidId() {
        try {
            String temp = this.idField.getText();
            Matcher matcher = this.idPattern.matcher(temp);
            if (matcher.find() && matcher.group().equals(temp)) {
                return true;
            }
            this.eventualityErrorMessage += "id field have not valid entry\n";
            return false;
        } catch (RuntimeException e) {
            this.eventualityErrorMessage += "id field have mistaken value, runtime error\n";
            return false;
        } catch (Exception e) {
            this.eventualityErrorMessage += "id field have mistaken value\n";
            return false;
        }
    }

    private boolean isValidName() {
        try {
            String temp = this.nameField.getText();
            Matcher matcher = this.namePattern.matcher(temp);
            if (matcher.find()) {
                return true;
            }
            this.eventualityErrorMessage += "name field have not valid entry\n";
            return false;
        } catch (Exception e) {
            this.eventualityErrorMessage += "name field have mistaken entry\n";
            return false;
        }
    }

    private boolean isValidPercentage() {
        try {
            String temp = this.percentageField.getText();
            Matcher matcher = this.percentagePattern.matcher(temp);
            if (matcher.find()) {
                return true;
            }
            this.eventualityErrorMessage += "percentage field have not valid entry\n";
            return false;
        } catch (RuntimeException e) {
            this.eventualityErrorMessage += "percentage field have mistaken value, runtime error\n";
            return false;
        } catch (Exception e) {
            this.eventualityErrorMessage += "percentage field have mistaken value\n";
            return false;
        }
    }

    @FXML
    public void ok() {
        if (this.isValidForm()) {
            this.editedSeed.setIdValue(this.idField.getText());
            this.editedSeed.setNameValue(this.nameField.getText());
            this.editedSeed.setPercentageValue(this.percentageField.getText());
            this.onClicked = true;
            this.editorStage.close();
        } else {
            InvalidFormAlertView.showAlert(this.editorStage, this.eventualityErrorMessage);
        }
    }

    @FXML
    public void cancel() {
        this.editorStage.close();
    }

    public void setEditorStage(Stage stage) {
        this.editorStage = stage;
    }

    public void setSeed(Seed seed) {
        this.editedSeed = seed;
        this.idField.setText(seed.getIdValue());
        this.idField.setPromptText("only integer digits");
        this.nameField.setText(seed.getNameValue());
        this.nameField.setPromptText("only alphabetical characters");
        this.percentageField.setText(seed.getPercentageValue());
        this.percentageField.setPromptText("0 < n < 1");
    }

    public boolean isOnClicked() {
        return this.onClicked;
    }
}
