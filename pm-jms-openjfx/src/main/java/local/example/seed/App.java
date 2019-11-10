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
import local.example.seed.view.init.SampleFourView;
import local.example.seed.view.init.SampleThreeView;

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

    public void initAreaChartView() {
        FXMLLoader areaChartViewLoader = new FXMLLoader();
        areaChartViewLoader.setLocation(App.class.getResource("view/area-chart-view.fxml"));
        AnchorPane areaChartAnchorPane;
        try {
            areaChartAnchorPane = (AnchorPane) areaChartViewLoader.load();
            Stage areaChartStage = new Stage();
            areaChartStage.setTitle("pseudo-random native vine area chart");
            areaChartStage.initModality(Modality.WINDOW_MODAL);
            areaChartStage.initOwner(this.stage);
            Scene areaChartScene = new Scene(areaChartAnchorPane);
            areaChartStage.setScene(areaChartScene);
            areaChartStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initBubbleChartView() {
        FXMLLoader bubbleChartViewLoader = new FXMLLoader();
        bubbleChartViewLoader.setLocation(App.class.getResource("view/bubble-chart-view.fxml"));
        AnchorPane bubbleChartAnchorPane;
        try {
            bubbleChartAnchorPane = (AnchorPane) bubbleChartViewLoader.load();
            Stage bubbleChartStage = new Stage();
            bubbleChartStage.setTitle("pseudo-random native vine bubble chart");
            bubbleChartStage.initModality(Modality.WINDOW_MODAL);
            bubbleChartStage.initOwner(this.stage);
            Scene bubbleChartScene = new Scene(bubbleChartAnchorPane);
            bubbleChartStage.setScene(bubbleChartScene);
            bubbleChartStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initScatterChartView() {
        FXMLLoader scatterChartViewLoader = new FXMLLoader();
        scatterChartViewLoader.setLocation(App.class.getResource("view/scatter-chart-view.fxml"));
        AnchorPane scatterChartAnchorPane;
        try {
            scatterChartAnchorPane = (AnchorPane) scatterChartViewLoader.load();
            Stage scatterChartStage = new Stage();
            scatterChartStage.setTitle("pseudo-random native vine scatter chart");
            scatterChartStage.initModality(Modality.WINDOW_MODAL);
            scatterChartStage.initOwner(this.stage);
            Scene scatterChartScene = new Scene(scatterChartAnchorPane);
            scatterChartStage.setScene(scatterChartScene);
            scatterChartStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initBarChartView() {
        FXMLLoader barChartViewLoader = new FXMLLoader();
        barChartViewLoader.setLocation(App.class.getResource("view/bar-chart-view.fxml"));
        AnchorPane barChartAnchorPane;
        try {
            barChartAnchorPane = (AnchorPane) barChartViewLoader.load();
            Stage barChartStage = new Stage();
            barChartStage.setTitle("pseudo-random native vine bar chart");
            barChartStage.initModality(Modality.WINDOW_MODAL);
            barChartStage.initOwner(this.stage);
            Scene barChartScene = new Scene(barChartAnchorPane);
            barChartStage.setScene(barChartScene);
            barChartStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initStackedBarChartView() {
        FXMLLoader stackedBarChartViewLoader = new FXMLLoader();
        stackedBarChartViewLoader.setLocation(App.class.getResource("view/stacked-bar-chart-view.fxml"));
        AnchorPane stackedBarChartAnchorPane;
        try {
            stackedBarChartAnchorPane = (AnchorPane) stackedBarChartViewLoader.load();
            Stage stackedBarChartStage = new Stage();
            stackedBarChartStage.setTitle("pseudo-random native vine stacked bar chart");
            stackedBarChartStage.initModality(Modality.WINDOW_MODAL);
            stackedBarChartStage.initOwner(this.stage);
            Scene stackedBarChartScene = new Scene(stackedBarChartAnchorPane);
            stackedBarChartStage.setScene(stackedBarChartScene);
            stackedBarChartStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initStackedAreaChartView() {
        FXMLLoader stackedAreaChartViewLoader = new FXMLLoader();
        stackedAreaChartViewLoader.setLocation(App.class.getResource("view/stacked-area-chart-view.fxml"));
        AnchorPane stackedAreaChartAnchorPane;
        try {
            stackedAreaChartAnchorPane = (AnchorPane) stackedAreaChartViewLoader.load();
            Stage stackedAreaChartStage = new Stage();
            stackedAreaChartStage.setTitle("pseudo-random native vine stacked area chart");
            stackedAreaChartStage.initModality(Modality.WINDOW_MODAL);
            stackedAreaChartStage.initOwner(this.stage);
            Scene stackedAreaChartScene = new Scene(stackedAreaChartAnchorPane);
            stackedAreaChartStage.setScene(stackedAreaChartScene);
            stackedAreaChartStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initTemplate3DView() {
        FXMLLoader template3DViewLoader = new FXMLLoader();
        template3DViewLoader.setLocation(App.class.getResource("view/template-3d-view.fxml"));
        AnchorPane template3DAnchorPane;
        try {
            template3DAnchorPane = (AnchorPane) template3DViewLoader.load();
            Stage template3DStage = new Stage();
            template3DStage.setTitle("template3D");
            template3DStage.initModality(Modality.WINDOW_MODAL);
            template3DStage.initOwner(this.stage);
            Scene template3DScene = new Scene(template3DAnchorPane);
            template3DStage.setScene(template3DScene);
            template3DStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initSampleOneView() {
        FXMLLoader sampleOneViewLoader = new FXMLLoader();
        sampleOneViewLoader.setLocation(App.class.getResource("view/sample-one-view.fxml"));
        AnchorPane sampleOneAnchorPane;
        try {
            sampleOneAnchorPane = (AnchorPane) sampleOneViewLoader.load();
            Stage sampleOneStage = new Stage();
            sampleOneStage.setTitle("sample one 3D");
            sampleOneStage.initModality(Modality.WINDOW_MODAL);
            sampleOneStage.initOwner(this.stage);
            Scene sampleOneScene = new Scene(sampleOneAnchorPane);
            sampleOneStage.setScene(sampleOneScene);
            sampleOneStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initSampleTwoView() {
        FXMLLoader sampleTwoViewLoader = new FXMLLoader();
        sampleTwoViewLoader.setLocation(App.class.getResource("view/sample-two-view.fxml"));
        AnchorPane sampleTwoAnchorPane;
        try {
            sampleTwoAnchorPane = (AnchorPane) sampleTwoViewLoader.load();
            Stage sampleTwoStage = new Stage();
            sampleTwoStage.setTitle("sample two 3D");
            sampleTwoStage.initModality(Modality.WINDOW_MODAL);
            sampleTwoStage.initOwner(this.stage);
            Scene sampleTwoScene = new Scene(sampleTwoAnchorPane);
            sampleTwoStage.setScene(sampleTwoScene);
            sampleTwoStage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }

    public void initSampleThreeView() {
        SampleThreeView.init(this);
    }
    
    public void initSampleFourView() {
        SampleFourView.init(this);
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
