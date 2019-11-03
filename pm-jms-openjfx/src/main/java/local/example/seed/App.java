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

package local.example.seed;

import java.io.IOException;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import local.example.seed.controller.RootController;
import local.example.seed.controller.SeedEditorController;
import local.example.seed.controller.SeedViewController;
import local.example.seed.model.Seed;
import local.example.seed.view.ExceptionAlertView;

/**
 * openjfx model-view-controller
 */
public class App
        extends Application {

    private static Scene scene;
    private Stage stage;
    private BorderPane borderPane;
    private final ObservableList<Seed> seedValues = FXCollections.observableArrayList();

    public App() {
    }

    @Override
    public void start(Stage stage)
            throws IOException {
        this.stage = stage;
        this.stage.setTitle("seed mvc application");
        this.initRootView();
        this.initSeedView();
    }

    public void initRootView() {
        FXMLLoader rootLoader = new FXMLLoader();
        rootLoader
                .setLocation(App.class.getResource("view/root.fxml"));
        try {
            borderPane = (BorderPane) rootLoader.load();
            scene = new Scene(borderPane, 640, 380);
            stage.setScene(scene);
            RootController rootController = rootLoader.getController();
            rootController.setApp(this);
            stage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initSeedView() {
        FXMLLoader seedViewLoader = new FXMLLoader();
        seedViewLoader
                .setLocation(App.class.getResource("view/seed-view.fxml"));
        AnchorPane seedAnchorPane;
        try {
            seedAnchorPane = (AnchorPane) seedViewLoader.load();
            borderPane.setCenter(seedAnchorPane);
            SeedViewController seedViewController = seedViewLoader.getController();
            seedViewController.setApp(this);
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public boolean initSeedEditView(Seed seed) {
        FXMLLoader seedEditorViewLoader = new FXMLLoader();
        seedEditorViewLoader.setLocation(App.class.getResource("view/seed-editor.fxml"));
        AnchorPane seedEditorAnchorPane;
        try {
            seedEditorAnchorPane = (AnchorPane) seedEditorViewLoader.load();
            Stage seedEditorStage = new Stage();
            seedEditorStage.setTitle("seed editor");
            seedEditorStage.initModality(Modality.WINDOW_MODAL);
            seedEditorStage.initOwner(this.stage);
            Scene seedEditorScene = new Scene(seedEditorAnchorPane);
            seedEditorStage.setScene(seedEditorScene);
            SeedEditorController seedEditController = seedEditorViewLoader.getController();
            seedEditController.setEditorStage(seedEditorStage);
            seedEditController.setSeed(seed);
            seedEditorStage.showAndWait();
            return seedEditController.isOnClicked();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
            return false;
        }
    }

    public void initPieChartView() {
        FXMLLoader pieChartViewLoader = new FXMLLoader();
        pieChartViewLoader.setLocation(App.class.getResource("view/pie-chart-view.fxml"));
        AnchorPane pieChartAnchorPane;
        try {
            pieChartAnchorPane = (AnchorPane) pieChartViewLoader.load();
            Stage pieChartStage = new Stage();
            pieChartStage.setTitle("red wine blend pie chart");
            pieChartStage.initModality(Modality.WINDOW_MODAL);
            pieChartStage.initOwner(this.stage);
            Scene pieChartScene = new Scene(pieChartAnchorPane);
            pieChartStage.setScene(pieChartScene);
            pieChartStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initLineChartView() {
        FXMLLoader lineChartViewLoader = new FXMLLoader();
        lineChartViewLoader.setLocation(App.class.getResource("view/line-chart-view.fxml"));
        AnchorPane lineChartAnchorPane;
        try {
            lineChartAnchorPane = (AnchorPane) lineChartViewLoader.load();
            Stage lineChartStage = new Stage();
            lineChartStage.setTitle("native vine line chart");
            lineChartStage.initModality(Modality.WINDOW_MODAL);
            lineChartStage.initOwner(this.stage);
            Scene lineChartScene = new Scene(lineChartAnchorPane);
            lineChartStage.setScene(lineChartScene);
            lineChartStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Seed> getSeedData() {
        return seedValues;
    }
}
