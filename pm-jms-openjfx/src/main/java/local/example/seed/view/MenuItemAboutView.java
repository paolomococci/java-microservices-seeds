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

package local.example.seed.view;

import javafx.scene.control.Alert;

public class MenuItemAboutView {
    
    private static final Alert ALERT = new Alert(Alert.AlertType.INFORMATION);

    public static void showAlert() {
        ALERT.setTitle("about author");
        ALERT.setHeaderText("author:");
        ALERT.setContentText("paolo mococci");
        ALERT.showAndWait();
    }
}
