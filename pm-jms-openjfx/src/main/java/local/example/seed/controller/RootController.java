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
import local.example.seed.App;
import local.example.seed.view.alert.MenuItemAboutView;

public class RootController {

    private App app;

    public void setApp(App app) {
        this.app = app;
    }
    
    @FXML
    private void menuItemQuit() {
        System.exit(0);
    }
    
    @FXML
    private void menuItemPieChart() {
        app.initPieChartView();
    }
    
    @FXML
    private void menuItemBubbleChart() {
        app.initBubbleChartView();
    }
    
    @FXML
    private void menuItemScatterChart() {
        app.initScatterChartView();
    }
    
    @FXML
    private void menuItemBarChart() {
        app.initBarChartView();
    }
    
    @FXML
    private void menuItemStackedBarChart() {
        app.initStackedBarChartView();
    }
    
    @FXML
    private void menuItemLineChart() {
        app.initLineChartView();
    }
    
    @FXML
    private void menuItemAreaChart() {
        app.initAreaChartView();
    }
    
    @FXML
    private void menuItemStackedAreaChart() {
        app.initStackedAreaChartView();
    }
    
    @FXML
    private void menuItemTemplate3D() {
        app.initTemplate3DView();
    }
    
    @FXML
    private void menuItemAbout() {
        MenuItemAboutView.showAlert();
    }
}
