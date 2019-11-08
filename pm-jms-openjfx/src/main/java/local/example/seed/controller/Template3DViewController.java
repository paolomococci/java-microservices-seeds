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
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Sphere;

public class Template3DViewController {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Group mainGroup;

    @FXML
    private AmbientLight ambientLight;

    @FXML
    private ParallelCamera parallelCamera;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private Button moveButton;

    private Sphere sphere;
    private Button restoreButton;

    @FXML
    public void initialize() {
        sphere = new Sphere(50.0);
        sphere.setTranslateX(200.0);
        restoreButton = new Button("restore");
        mainGroup.getChildren().add(sphere);
    }

    public void move() {
        sphere.setTranslateY(-200.0);
        moveButton.setDisable(true);
        restoreButton.setOnAction((var actionEvent) -> {
            try {
                sphere.setTranslateY(0.0);
                moveButton.setDisable(false);
                restoreButton.setDisable(true);
                buttonBar.getButtons().remove(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        buttonBar.getButtons().add(restoreButton);
        restoreButton.setDisable(false);
    }
}
