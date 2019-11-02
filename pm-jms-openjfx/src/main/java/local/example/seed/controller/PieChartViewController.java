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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class PieChartViewController {
    
    private ObservableList<PieChart.Data> 
            pieChartObservableList;

    public PieChartViewController() {
        this.pieChartObservableList = FXCollections.observableArrayList(
                new PieChart.Data("sagrantino", 15),
                new PieChart.Data("colorino", 10),
                new PieChart.Data("cigliegiolo", 15),
                new PieChart.Data("sangiovese", 25),
                new PieChart.Data("trebbiano", 35)
        );
    }
    
    @FXML
    private final PieChart pieChart = new PieChart(pieChartObservableList);

    public PieChart getPieChart() {
        return pieChart;
    }

    public ObservableList<PieChart.Data> getPieChartObservableList() {
        return pieChartObservableList;
    }
}
