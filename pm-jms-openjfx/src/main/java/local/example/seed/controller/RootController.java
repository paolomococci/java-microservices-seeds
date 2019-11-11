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
import local.example.seed.view.alert.ExceptionAlertView;
import local.example.seed.view.init.InitView;

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
        InitView.init(
                this, 
                "view/pie-chart-view.fxml", 
                "red wine blend pie chart");
    }

    public void initLineChartView() {
        InitView.init(
                this, 
                "view/line-chart-view.fxml", 
                "native vine line chart");
    }

    public void initAreaChartView() {
        InitView.init(
                this, 
                "view/area-chart-view.fxml", 
                "pseudo-random native vine area chart");
    }

    public void initBubbleChartView() {
        InitView.init(
                this, 
                "view/bubble-chart-view.fxml", 
                "pseudo-random native vine bubble chart");
    }

    public void initScatterChartView() {
        InitView.init(
                this, 
                "view/scatter-chart-view.fxml", 
                "pseudo-random native vine scatter chart");
    }

    public void initBarChartView() {
        InitView.init(
                this, 
                "view/bar-chart-view.fxml", 
                "pseudo-random native vine bar chart"
        );
    }

    public void initStackedBarChartView() {
        InitView.init(
                this, 
                "view/stacked-bar-chart-view.fxml", 
                "pseudo-random native vine stacked bar chart");
    }

    public void initStackedAreaChartView() {
        InitView.init(
                this, 
                "view/stacked-area-chart-view.fxml", 
                "pseudo-random native vine stacked area chart");
    }

    public void initTemplate3DView() {
        InitView.init(
                this, 
                "view/template-3d-view.fxml", 
                "template3D");
    }

    public void initSampleOneView() {
        InitView.init(
                this, 
                "view/sample-one-view.fxml", 
                "sample one 3D");
    }

    public void initSampleTwoView() {
        InitView.init(
                this, 
                "view/sample-two-view.fxml", 
                "sample two 3D");
    }

    public void initSampleThreeView() {
        InitView.init(
                this, 
                "view/sample-three-view.fxml", 
                "sample three 3D");
    }
    
    public void initSampleFourView() {
        InitView.init(
                this, 
                "view/sample-four-view.fxml", 
                "sample four 3D");
    }
    
    public void initSampleFiveView() {
        InitView.init(
                this, 
                "view/sample-five-view.fxml", 
                "sample five 3D");
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
