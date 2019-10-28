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

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import local.example.seed.model.Seed;
import local.example.seed.view.InvalidFormAlertView;

public class SeedEditorController {
    
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
    
    @FXML
    private void initialize() {}
    
    private boolean isValidForm() {
        return this.isValidId() && 
                this.isValidName() && 
                this.isValidPercentage();
    }
    
    private boolean isValidId() { // TODO use regular expression
        String temp = this.idField.getText();
        if (temp != null && !"".equals(temp)) {
            return true;
        }
        this.eventualityErrorMessage+= "id field is empty\n";
        return false;
    }
    
    private boolean isValidName() { // TODO use regular expression
        String temp = this.nameField.getText();
        if (temp != null && !"".equals(temp)) {
            return true;
        }
        this.eventualityErrorMessage+= "name field is empty\n";
        return false;
    }
    
    private boolean isValidPercentage() { // TODO use regular expression
        String temp = this.percentageField.getText();
        if (temp != null && !"".equals(temp)) {
            return true;
        }
        this.eventualityErrorMessage+= "percentage field is empty\n";
        return false;
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
        this.nameField.setText(seed.getNameValue());
        this.percentageField.setText(seed.getPercentageValue());
        this.percentageField.setPromptText("0 <= n <= 1");
    }
    
    public boolean isOnClicked() {
        return this.onClicked;
    }
}
