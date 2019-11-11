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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.ArcType;

public class CanvasOneViewController {
    
    @FXML
    private AnchorPane mainAnchorPane;
    
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    
    @FXML
    private void initialize() {
        canvas = new Canvas(400.0, 400.0);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.strokeText("sample text on canvas", 30.0, 30.0);
        graphicsContext.setLineWidth(1.0);
        graphicsContext.strokeRect(50.0, 50.0, 50.0, 50.0);
        graphicsContext.strokeRoundRect(150.0, 50.0, 50.0, 50.0, 10.0, 10.0);
        graphicsContext.strokeOval(50.0, 150.0, 50.0, 80.0);
        graphicsContext.strokeLine(150.0, 150.0, 300.0, 150.0);
        graphicsContext.strokeArc(50.0, 250.0, 50.0, 50.0, 50.0, 100.0, ArcType.OPEN);
        graphicsContext.strokeArc(150.0, 250.0, 50.0, 50.0, 50.0, 100.0, ArcType.CHORD);
        graphicsContext.strokeArc(250.0, 250.0, 50.0, 50.0, 50.0, 100.0, ArcType.ROUND);
        mainAnchorPane.getChildren().add(canvas);
    }
}
