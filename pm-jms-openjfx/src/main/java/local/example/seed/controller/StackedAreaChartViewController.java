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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;

public class StackedAreaChartViewController {
    
    @FXML
    private StackedAreaChart<Number, Number> pseudoRandomStackedAreaChart;
    
    @FXML
    private NumberAxis abscissa;
    
    @FXML
    private NumberAxis ordinate;
    
    private final Random random = new Random();

    @FXML
    private void initialize() {}
    
    @FXML
    public void loadPseudoRandomData() {
        pseudoRandomStackedAreaChart.getData().clear();
        ordinate.setAutoRanging(false);
        ordinate.setLowerBound(0.0);
        ordinate.setUpperBound(45.0);
        ordinate.setTickUnit(2.5);
        ordinate.setLabel("quantity");
        abscissa.setAutoRanging(false);
        abscissa.setLowerBound(1980);
        abscissa.setUpperBound(2020);
        abscissa.setTickUnit(5.0);
        abscissa.setTickLabelRotation(30.0);
        abscissa.setLabel("seasonal harvests");
        abscissa.setTickLabelFormatter(stringConverterNumber());
        pseudoRandomStackedAreaChart.getData().addAll(makePseudoRandomNumbersSeries());
        pseudoRandomStackedAreaChart.setTitle("demo data series");
    }
    
    private ObservableList<XYChart.Series<Number, Number>> makePseudoRandomNumbersSeries() {
        ObservableList<XYChart.Series<Number, Number>> observableListSeries;
        observableListSeries = FXCollections.observableArrayList();
        XYChart.Series<Number, Number> sagrantinoSeries = new XYChart.Series<>();
        XYChart.Series<Number, Number> sangioveseSeries = new XYChart.Series<>();
        XYChart.Series<Number, Number> colorinoSeries = new XYChart.Series<>();
        XYChart.Series<Number, Number> cigliegioloSeries = new XYChart.Series<>();
        XYChart.Series<Number, Number> canaioloSeries = new XYChart.Series<>();
        sagrantinoSeries.setName("sagrantino");
        sangioveseSeries.setName("sangiovese");
        colorinoSeries.setName("colorino");
        cigliegioloSeries.setName("cigliegiolo");
        canaioloSeries.setName("canaiolo");
        for (int i = 1985; i < 2020; i+=5) {
            sagrantinoSeries.getData().add(new XYChart.Data<>(
                            i, 
                            random.nextDouble()*10.0));
            sangioveseSeries.getData().add(new XYChart.Data<>(
                            i, 
                            random.nextDouble()*10.0));
            colorinoSeries.getData().add(new XYChart.Data<>(
                            i, 
                            random.nextDouble()*10.0));
            cigliegioloSeries.getData().add(new XYChart.Data<>(
                            i, 
                            random.nextDouble()*10.0));
            canaioloSeries.getData().add(new XYChart.Data<>(
                            i, 
                            random.nextDouble()*10.0));
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
