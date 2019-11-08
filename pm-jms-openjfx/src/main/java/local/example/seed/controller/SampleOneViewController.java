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
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Sphere;

public class SampleOneViewController {

    @FXML
    private AnchorPane mainAnchorPane;
    
    @FXML
    private GridPane gridPaneOne;
    
    @FXML
    private Group groupOne;
    
    @FXML
    private ButtonBar mainButtonBar;
    
    @FXML
    private Button buttonOne;

    @FXML
    private void initialize() {
        
    }
    
    public void sample() {
        Sphere sphereOne = new Sphere(40.0);
        groupOne.getChildren().add(sphereOne);
    }
}
