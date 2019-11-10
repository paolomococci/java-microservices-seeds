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

package local.example.seed.view.util;

import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PointLight;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;

public class WhiteScene {
    
    public static SubScene createWhiteScene(
            Node node, 
            double width,
            double height,
            Paint fillPaint, 
            Camera camera, 
            boolean antialiasing
    ) {
        Group root = new Group();
        PointLight lightOne = new PointLight(Color.WHITE);
        PointLight lightTwo = new PointLight(Color.color(1.0, 0.98, 0.77));
        AmbientLight ambientLight = new AmbientLight(Color.color(0.0, 0.02, 0.3));
        lightOne.setTranslateX(width/12);
        lightOne.setTranslateY(-width/2);
        lightOne.setTranslateZ(-height);
        lightTwo.setTranslateX(width*0.66);
        lightTwo.setTranslateY(0.0);
        lightTwo.setTranslateZ(-width*0.66);
        node.setRotationAxis(Rotate.Z_AXIS);
        node.setTranslateX(150.0);
        node.setTranslateY(180.0);
        node.setTranslateZ(200.0);
        root.getChildren().addAll(
                ambientLight, 
                lightOne, 
                lightTwo, 
                node
        );
        SubScene whiteScene;
        whiteScene = new SubScene(
                root, 
                width, 
                height, 
                true, 
                antialiasing ? SceneAntialiasing.BALANCED : 
                        SceneAntialiasing.DISABLED
        );
        whiteScene.setFill(fillPaint);
        whiteScene.setCamera(camera);
        return whiteScene;
    }
}
