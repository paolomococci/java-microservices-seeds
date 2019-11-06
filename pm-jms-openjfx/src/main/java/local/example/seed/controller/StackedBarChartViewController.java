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
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;

public class StackedBarChartViewController {
    
    @FXML
    private StackedBarChart<String, Double> pseudoRandomStackedBarChart;
    
    @FXML
    private CategoryAxis x;
    
    @FXML
    private NumberAxis y;
    
    private final Random random = new Random();
    
    @FXML
    private void initialize() {}
    
    @FXML
    public void boundPseudoRandomSeries() {
        pseudoRandomStackedBarChart.getData().clear();
        y.setAutoRanging(false);
        y.setLowerBound(0.0);
        y.setUpperBound(110.0);
        y.setLabel("quantity");
        x.setAutoRanging(false);
        x.setLabel("seasonal harvests");
        x.setTickLabelRotation(90.0);
        x.setCategories(this.makeStringObservableList());
        pseudoRandomStackedBarChart.setTitle("1945-2019 pseudo random data");
        pseudoRandomStackedBarChart.setData(makePseudoRandomNumbersSeries());
    }
    
    private ObservableList<Series<String, Double>> makePseudoRandomNumbersSeries() {
        ObservableList<Series<String, Double>> observableListSeries;
        observableListSeries = FXCollections.observableArrayList();
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
        for (int i = 1945; i < 2020; i+=5) {
            sagrantinoSeries.getData().add(new Data<>(
                            Integer.toString(i), 
                            random.nextDouble()*20.0));
            sangioveseSeries.getData().add(new Data<>(
                            Integer.toString(i), 
                            random.nextDouble()*20.0));
            colorinoSeries.getData().add(new Data<>(
                            Integer.toString(i), 
                            random.nextDouble()*20.0));
            cigliegioloSeries.getData().add(new Data<>(
                            Integer.toString(i), 
                            random.nextDouble()*20.0));
            canaioloSeries.getData().add(new Data<>(
                            Integer.toString(i), 
                            random.nextDouble()*20.0));
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
        for (int i = 1940; i < 2025; i+=5) {
            temp.add(Integer.toString(i));
        }
        return temp;
    }
}
