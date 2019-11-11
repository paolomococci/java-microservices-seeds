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
import javafx.scene.chart.PieChart;

public class PieChartViewController {
    
    @FXML
    private PieChart vineBlendPieChart;
    
    @FXML
    private void initialize() {}
    
    @FXML
    public void loadRedWineBlendData() {
        this.vineBlendPieChart.getData().clear();
        this.vineBlendPieChart.setClockwise(false);
        this.vineBlendPieChart.getData().addAll(
                new PieChart.Data("colorino", 15.0),
                new PieChart.Data("canaiolo", 15.0),
                new PieChart.Data("cigliegiolo", 70.0)
        );
        this.vineBlendPieChart.setTitle("red wine fantasy blend");
    }
}
