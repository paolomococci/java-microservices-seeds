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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class BarChartViewController {
    
    @FXML
    private BarChart<String, Double> pseudoRandomBarChart;
    
    private ObservableList<XYChart.Series<String, Double>> observableList;
    
    private final Random random = new Random();

    @FXML
    private void initialize() {}
    
    @FXML
    public void loadPseudoRandomData() {
        pseudoRandomBarChart.getData().clear();
        observableList = FXCollections.observableArrayList();
        Series<String, Double> sagrantinoSeries = new Series<>();
        Series<String, Double> sangioveseSeries = new Series<>();
        Series<String, Double> colorinoSeries = new Series<>();
        Series<String, Double> cigliegioloSeries = new Series<>();
        Series<String, Double> canaioloSeries = new Series<>();
        sagrantinoSeries.setName("sagrantino");
        sangioveseSeries.setName("sangiovese");
        colorinoSeries.setName("colorino");
        cigliegioloSeries.setName("cigliegiolo");
        canaioloSeries.setName("canaiolo");
        for (int i = 1960; i < 2020; i += 5) {
            sagrantinoSeries.getData().add(
                            new XYChart.Data<>(
                                    Integer.toString(i), random.nextDouble()
                            ));
            sangioveseSeries.getData().add(
                            new XYChart.Data<>(
                                    Integer.toString(i), random.nextDouble()
                            ));
            colorinoSeries.getData().add(
                            new XYChart.Data<>(
                                    Integer.toString(i), random.nextDouble()
                            ));
            cigliegioloSeries.getData().add(
                            new XYChart.Data<>(
                                    Integer.toString(i), random.nextDouble()
                            ));
            canaioloSeries.getData().add(
                            new XYChart.Data<>(
                                    Integer.toString(i), random.nextDouble()
                            ));
        }
        observableList.addAll(
                sagrantinoSeries, 
                sangioveseSeries, 
                colorinoSeries, 
                cigliegioloSeries, 
                canaioloSeries
        );
        pseudoRandomBarChart.setTitle("pseudo-random wine values");
        pseudoRandomBarChart.getData().addAll(observableList);
    }
}
