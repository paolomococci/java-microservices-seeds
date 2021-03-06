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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class ScatterChartViewController {
    
    @FXML
    private ScatterChart<String, Double> pseudoRandomScatterChart;
    
    @FXML
    private CategoryAxis x;
    
    @FXML
    private NumberAxis y;
    
    private final Random random = new Random();
    
    @FXML
    private void initialize() {}

    @FXML
    public void loadPseudoRandomData() {
        pseudoRandomScatterChart.getData().clear();
        y.setAutoRanging(false);
        y.setLowerBound(0.0);
        y.setUpperBound(110.0);
        y.setLabel("quantity");
        x.setAutoRanging(true);
        x.setLabel("seasonal harvests");
        x.setTickLabelRotation(90.0);
        x.setCategories(makeStringObservableList());
        pseudoRandomScatterChart.setTitle("pseudo-random wine values");
        pseudoRandomScatterChart.setData(makeSeries());
    }
    
    private ObservableList<XYChart.Series<String, Double>> makeSeries() {
        ObservableList<XYChart.Series<String, Double>> observableListSeries;
        observableListSeries = FXCollections.observableArrayList();
        XYChart.Series<String, Double> sagrantinoSeries = new XYChart.Series<>();
        XYChart.Series<String, Double> sangioveseSeries = new XYChart.Series<>();
        XYChart.Series<String, Double> colorinoSeries = new XYChart.Series<>();
        XYChart.Series<String, Double> cigliegioloSeries = new XYChart.Series<>();
        XYChart.Series<String, Double> canaioloSeries = new XYChart.Series<>();
        sagrantinoSeries.setName("sagrantino");
        sangioveseSeries.setName("sangiovese");
        colorinoSeries.setName("colorino");
        cigliegioloSeries.setName("cigliegiolo");
        canaioloSeries.setName("canaiolo");
        for (int i = 1960; i < 2020; i += 5) {
            sagrantinoSeries.getData().add(
                            new XYChart.Data<>(
                                    Integer.toString(i), 
                                    random.nextDouble()*100.0
                            ));
            sangioveseSeries.getData().add(
                            new XYChart.Data<>(
                                    Integer.toString(i), 
                                    random.nextDouble()*100.0
                            ));
            colorinoSeries.getData().add(
                            new XYChart.Data<>(
                                    Integer.toString(i), 
                                    random.nextDouble()*100.0
                            ));
            cigliegioloSeries.getData().add(
                            new XYChart.Data<>(
                                    Integer.toString(i), 
                                    random.nextDouble()*100.0
                            ));
            canaioloSeries.getData().add(
                            new XYChart.Data<>(
                                    Integer.toString(i), 
                                    random.nextDouble()*100.0
                            ));
        }
        observableListSeries.addAll(
                sagrantinoSeries, 
                sangioveseSeries, 
                colorinoSeries, 
                cigliegioloSeries, 
                canaioloSeries
        );
        return observableListSeries;
    }
    
    private ObservableList<String> makeStringObservableList() {
        ObservableList<String> temp;
        temp = FXCollections.observableArrayList();
        for (int i = 1955; i < 2025; i+=5) {
            temp.add(Integer.toString(i));
        }
        return temp;
    }
}
