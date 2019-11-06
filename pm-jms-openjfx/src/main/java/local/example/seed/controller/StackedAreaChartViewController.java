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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
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
        ordinate.setUpperBound(110.0);
        ordinate.setLabel("quantity");
        abscissa.setAutoRanging(false);
        abscissa.setLabel("seasonal harvests");
        abscissa.setTickLabelFormatter(stringConverterNumber());
        
        pseudoRandomStackedAreaChart.setTitle("fantasy data series");
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
