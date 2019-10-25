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

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import local.example.seed.controller.SeedViewController;
import local.example.seed.model.Seed;

/**
 * openjfx model-view-controller
 */
public class App
        extends Application {

    private static Scene scene;
    private Stage stage;
    private BorderPane borderPane;
    private final ObservableList<Seed> 
            seedValues = FXCollections.observableArrayList();

    public App() {
        seedValues.add(new Seed(1L, "seedOne", 0.25));
        seedValues.add(new Seed(2L, "seedTwo", 0.15));
        seedValues.add(new Seed(3L, "seedThree", 0.30));
        seedValues.add(new Seed(4L, "seedFour", 0.10));
        seedValues.add(new Seed(5L, "seedFive", 0.45));
        seedValues.add(new Seed(6L, "seedSix", 0.40));
        seedValues.add(new Seed(7L, "seedSeven", 0.75));
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
            stage.show();
        } catch (IOException e) {
            System.err.println(e.getMessage());
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
            SeedViewController 
                    seedViewController = seedViewLoader.getController();
            //seedViewController.setApp(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Stage getPrimaryStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Seed> getSeedData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
