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

package local.example.seed.field;

import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class PriceField
        extends CustomField<Double> {

    private final TextField doubleValueField = new TextField();

    public PriceField() {
        this.setLabel("price");
        this.doubleValueField.setPattern("[0-9]{0,3}\\.[0-9]{0,2}");
        this.doubleValueField.setPreventInvalidInput(true);
        this.doubleValueField.setMaxLength(6);
        this.doubleValueField.setPlaceholder("000.00");
        HorizontalLayout horizontalLayout = new HorizontalLayout(this.doubleValueField);
        this.add(horizontalLayout);
    }

    @Override
    protected Double generateModelValue() {
        return Double.parseDouble(this.doubleValueField.getValue());
    }

    @Override
    protected void setPresentationValue(Double newPresentationValue) {
        if (newPresentationValue == null) {
            this.doubleValueField.setValue("0.0");
        }
    }
}
