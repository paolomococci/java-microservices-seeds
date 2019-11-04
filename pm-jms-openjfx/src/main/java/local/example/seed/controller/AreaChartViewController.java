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
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;

public class AreaChartViewController {
    
    @FXML
    private AreaChart<String, Number> pseudoRandomAreaChart;
    
    private XYChart.Series<String, Number> pseudoRandomDataSeries;
    
    private final Random random = new Random();

    @FXML
    private void initialize() {}
    
    @FXML
    public void loadPseudoRandomData() {
        pseudoRandomAreaChart.getData().clear();
        pseudoRandomDataSeries = new XYChart.Series<>();
        pseudoRandomDataSeries.setName("pseudo-random series");
        for (int i = 1945; i < 2020; i++) {
            pseudoRandomDataSeries.getData().add(
                    new XYChart.Data<>(Integer.toString(i), random.nextDouble())
            );
        }
        pseudoRandomAreaChart.setTitle("1945-2019 pseudo random data");
        pseudoRandomAreaChart.getData().add(pseudoRandomDataSeries);
    }
}
