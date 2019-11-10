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
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import local.example.seed.view.util.WhiteScene;

public class SampleThreeViewController {
    
    @FXML
    private AnchorPane mainAnchorPane;
    
    private Camera camera;
    private PhongMaterial phongMaterial;
    private Box box;
    private SubScene subScene;
    
    @FXML
    private void initialize() {
        camera = new PerspectiveCamera(false);
        camera.getTransforms().addAll(
                new Rotate(-20.0, Rotate.Y_AXIS),
                new Rotate(-20.0, Rotate.X_AXIS),
                new Translate(0.0, 0.0, -15.0)
        );
        phongMaterial = new PhongMaterial();
        phongMaterial.setSpecularColor(Color.CORAL);
        phongMaterial.setDiffuseColor(Color.RED);
        box = new Box(150.0, 150.0, 150.0);
        box.setMaterial(phongMaterial);
        box.setDrawMode(DrawMode.FILL);
        box.setTranslateX(300.0);
        box.setTranslateY(200.0);
        box.setTranslateZ(75.0);
        box.setRotate(0.0);
        subScene = WhiteScene.createWhiteScene(
                box, 
                mainAnchorPane.getPrefWidth(),
                mainAnchorPane.getPrefHeight(),
                Color.ALICEBLUE, 
                camera, 
                true
        );
        mainAnchorPane.getChildren().add(subScene);
        mainAnchorPane.getChildren().add(new Label("sample cube"));
    }
}
