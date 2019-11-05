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
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.util.StringConverter;

public class BubbleChartViewController {
    
    @FXML
    private BubbleChart<Number, Number> pseudoRandomBubbleChart;
    
    @FXML
    private NumberAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;
    
    private final Random random = new Random();

    @FXML
    private void initialize() {}
    
    @FXML
    public void boundPseudoRandomSeries() {
        pseudoRandomBubbleChart.getData().clear();
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0.0);
        yAxis.setUpperBound(50.0);
        yAxis.setLabel("quantity");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1940);
        xAxis.setUpperBound(2025);
        xAxis.setTickUnit(5.0);
        xAxis.setTickLabelRotation(90.0);
        xAxis.setLabel("seasonal harvests");
        xAxis.setTickLabelFormatter(stringConverterNumber());
        pseudoRandomBubbleChart.getData().addAll(makePseudoRandomNumbersSeries());
        pseudoRandomBubbleChart.setTitle("1945-2019 pseudo random data");
    }
    
    private ObservableList<XYChart.Series<Number, Number>> makePseudoRandomNumbersSeries() {
        ObservableList<XYChart.Series<Number, Number>> observableListSeries;
        observableListSeries = FXCollections.observableArrayList();
        XYChart.Series<Number, Number> sagrantinoSeries = new Series<>();
        XYChart.Series<Number, Number> sangioveseSeries = new Series<>();
        XYChart.Series<Number, Number> colorinoSeries = new Series<>();
        XYChart.Series<Number, Number> cigliegioloSeries = new Series<>();
        XYChart.Series<Number, Number> canaioloSeries = new Series<>();
        sagrantinoSeries.setName("sagrantino");
        sangioveseSeries.setName("sangiovese");
        colorinoSeries.setName("colorino");
        cigliegioloSeries.setName("cigliegiolo");
        canaioloSeries.setName("canaiolo");
        for (int i = 1945; i < 2020; i++) {
            sagrantinoSeries.getData().add(new XYChart.Data<>(
                            i, 
                            random.nextDouble()*50.0, 
                            random.nextDouble()*2.0));
            sangioveseSeries.getData().add(new XYChart.Data<>(
                            i, 
                            random.nextDouble()*50.0, 
                            random.nextDouble()*2.0));
            colorinoSeries.getData().add(new XYChart.Data<>(
                            i, 
                            random.nextDouble()*50.0, 
                            random.nextDouble()*2.0));
            cigliegioloSeries.getData().add(new XYChart.Data<>(
                            i, 
                            random.nextDouble()*50.0, 
                            random.nextDouble()*2.0));
            canaioloSeries.getData().add(new XYChart.Data<>(
                            i, 
                            random.nextDouble()*50.0, 
                            random.nextDouble()*2.0));
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
    
    private StringConverter<Number> stringConverterNumber() {
        StringConverter<Number> stringConverterNumber;
        stringConverterNumber = new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.valueOf(object.intValue());
            }
            @Override
            public Number fromString(String string) {
                return Integer.valueOf(string)*10;
            }
        };
        return stringConverterNumber;
    }
}
