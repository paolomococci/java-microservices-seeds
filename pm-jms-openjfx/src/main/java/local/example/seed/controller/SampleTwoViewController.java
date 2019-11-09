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

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

public class SampleTwoViewController {
    
    @FXML
    private AnchorPane mainAnchorPane;
    
    @FXML
    private GridPane gridPane;
    
    @FXML
    private Label labelHead;
    
    @FXML
    private Pane mainPane;
    
    @FXML
    private ButtonBar buttonBar;
    
    @FXML
    private Button goButton;
    
    @FXML
    private Button stopButton;
    
    private final Random random = new Random();
    private Timeline timeline;
    
    @FXML
    private void initialize() {
        PhongMaterial phongMaterial;
        phongMaterial = new PhongMaterial(Color.CORAL);
        Sphere sphere = new Sphere(8.0);
        sphere.setCullFace(CullFace.BACK);
        sphere.setMaterial(phongMaterial);
        sphere.relocate(
                350.0*random.nextDouble(), 
                450.0*random.nextDouble()
        );
        mainPane.getChildren().add(sphere);
    }

    public void go() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(50), 
                        new EventHandler<ActionEvent>() {
                            double deltaX = 8.0;
                            double deltaY = 4.0;
            @Override
            public void handle(ActionEvent actionEvent) {
                Node node = mainPane.getChildren().get(0);
                node.setLayoutX(node
                        .getLayoutX() + deltaX*random.nextDouble());
                node.setLayoutY(node
                        .getLayoutY() + deltaY*random.nextDouble());
                double x = node.getLayoutX();
                if ((x <= 40.0) || x >= 350.0) {
                    deltaX*=-1;
                }
                double y = node.getLayoutY();
                if (y <= 60.0 || y >= 450) {
                    deltaY*=-1;
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    public void stop() {
        if (timeline != null) {
            Node node = mainPane.getChildren().get(0);
            for (int i = 0; i < 1000; i++) {
                node.setOpacity(1-i/1000);
            }
            timeline.stop();
            node.relocate(
                    350.0*random.nextDouble(), 
                    450.0*random.nextDouble()
            );
        }
    }
}
