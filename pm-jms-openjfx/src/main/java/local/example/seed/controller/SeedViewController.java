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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import local.example.seed.App;
import local.example.seed.model.Seed;
import local.example.seed.view.alert.NoSelectionAlertView;

public class SeedViewController {
    
    private App app;
    
    @FXML
    private TableView<Seed> seedTableView;
    
    @FXML
    private TableColumn<Seed, Long> idColumn;
    
    @FXML
    private TableColumn<Seed, String> nameColumn;
    
    @FXML
    private TableColumn<Seed, Double> percentageColumn;
    
    @FXML
    private Label idLabel;
    
    @FXML
    private Label nameLabel;
    
    @FXML
    private Label percentageLabel;
    
    @FXML
    private Label createdLabel;

    public SeedViewController() {}
    
    @FXML
    private void initialize() {
        this.idColumn.setCellValueFactory(cellValue -> {
            return cellValue.getValue().getId().asObject();
        });
        this.nameColumn.setCellValueFactory(cellValue -> {
            return cellValue.getValue().getName();
        });
        this.percentageColumn.setCellValueFactory(cellValue -> {
            return cellValue.getValue().getPercentage().asObject();
        });
        this.showSeedDetails(null);
        this.seedTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, old, updated) -> 
                        this.showSeedDetails(updated));
    }
    
    private void showSeedDetails(Seed seed) {
        if (seed != null) {
            this.idLabel.setText(seed.getIdValue());
            this.nameLabel.setText(seed.getNameValue());
            this.percentageLabel.setText(seed.getPercentageValue());
        } else {
            this.idLabel.setText("");
            this.nameLabel.setText("");
            this.percentageLabel.setText("");
        }
    }
    
    @FXML
    public void createNewSeed() {
        Seed newSeed = new Seed();
        boolean ok = app.initSeedEditView(newSeed);
        if(ok) {
            app.getSeedData().add(newSeed);
        }
    }
    
    @FXML
    public void editSelectedSeed() {
        Seed selectedSeed = this.seedTableView.getSelectionModel().getSelectedItem();
        if (selectedSeed != null) {
            boolean ok = app.initSeedEditView(selectedSeed);
            if (ok) {
                this.showSeedDetails(selectedSeed);
                this.seedTableView.refresh();
            }
        } else {
            NoSelectionAlertView.showAlert(app);
        }
    }
    
    @FXML
    public void deleteSelectedSeed() {
        int selectedSeedIndex = this.seedTableView.getSelectionModel().getSelectedIndex();
        if (selectedSeedIndex != -1) {
            this.seedTableView.getItems().remove(selectedSeedIndex);
        } else {
            NoSelectionAlertView.showAlert(app);
        }
    }
    
    public void setApp(App app) {
        this.app = app;
        this.seedTableView.setItems(app.getSeedData());
    }
}
