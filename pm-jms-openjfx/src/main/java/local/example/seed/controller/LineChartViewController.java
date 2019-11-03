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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class LineChartViewController {
    
    @FXML
    private LineChart<String, Number> sagrantinoLineChart;
    
    private XYChart.Series<String, Number> sagrantinoDataSeries;
    
    @FXML
    private void initialize() {}
    
    @FXML
    public void loadSagrantinoData() {
        sagrantinoLineChart.getData().clear();
        sagrantinoDataSeries = new XYChart.Series<>();
        sagrantinoDataSeries.getData().add(new XYChart.Data("1960", 8));
        sagrantinoDataSeries.getData().add(new XYChart.Data("1965", 12));
        sagrantinoDataSeries.getData().add(new XYChart.Data("1970", 18));
        sagrantinoDataSeries.getData().add(new XYChart.Data("1975", 25));
        sagrantinoDataSeries.getData().add(new XYChart.Data("1980", 30));
        sagrantinoDataSeries.getData().add(new XYChart.Data("1985", 55));
        sagrantinoDataSeries.getData().add(new XYChart.Data("1990", 95));
        sagrantinoDataSeries.getData().add(new XYChart.Data("1995", 130));
        sagrantinoDataSeries.getData().add(new XYChart.Data("2000", 155));
        sagrantinoDataSeries.getData().add(new XYChart.Data("2005", 190));
        sagrantinoDataSeries.getData().add(new XYChart.Data("2010", 205));
        sagrantinoDataSeries.getData().add(new XYChart.Data("2015", 210));
        sagrantinoDataSeries.getData().add(new XYChart.Data("2019", 215));
        sagrantinoDataSeries.setName("fantasy seasonal harvests");
        sagrantinoLineChart.getData().add(sagrantinoDataSeries);
    }
}
