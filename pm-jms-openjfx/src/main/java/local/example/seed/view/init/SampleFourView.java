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

package local.example.seed.view.init;

import java.io.IOException;
import java.util.Arrays;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import local.example.seed.App;
import local.example.seed.view.alert.ExceptionAlertView;

public class SampleFourView {
    
    public static void init(App app) {
        var uri = "view/sample-four-view.fxml";
        var title = "sample four 3D";
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(App.class.getResource(uri));
        AnchorPane anchorPane;
        try {
            anchorPane = (AnchorPane) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(app.getStage());
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            ExceptionAlertView alertDialogView = new ExceptionAlertView(
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace())
            );
            alertDialogView.showErrorMessage();
        }
    }
}
