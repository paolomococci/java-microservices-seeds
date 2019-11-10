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

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class SampleFourViewController {
    
    @FXML
    private AnchorPane mainAnchorPane;
    
    private Box box;
    private PhongMaterial phongMaterial;
    private Camera camera;
    private RotateTransition rotateTransition;
    private Group group, rootGroup;
    private SubScene subScene;
    
    @FXML
    private void initialize() {
        group = new Group();
        rootGroup = new Group();
        camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                new Rotate(30, Rotate.X_AXIS),
                new Rotate(45, Rotate.Y_AXIS),
                new Rotate(60, Rotate.Z_AXIS),
                new Translate(0, 0, -60.0)
        );
        phongMaterial = new PhongMaterial(Color.CYAN);
        phongMaterial.setDiffuseColor(Color.AQUA);
        box = new Box(15.0, 15.0, 15.0);
        box.setMaterial(phongMaterial);
        box.setDrawMode(DrawMode.FILL);
        rotateTransition = new RotateTransition(
                Duration.millis(5000), 
                box);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360.0);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        group.getChildren().add(camera);
        group.getChildren().add(box);
        subScene = new SubScene(group, 400.0, 400.0);
        subScene.setFill(Color.TRANSPARENT);
        subScene.setCamera(camera);
        rootGroup.getChildren().add(subScene);
        mainAnchorPane.getChildren().add(rootGroup);
        rotateTransition.play();
    }
}
