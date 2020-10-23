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

public class TotalField
        extends CustomField<Double> {

    private final TextField doubleValueField = new TextField();

    public TotalField() {
        this.doubleValueField.setLabel("accepted format: nnn.nn");
        this.doubleValueField.setPattern("^([0-9]{0,3}([.][0-9]{0,2})?|[.][0-9]{0,2})$");
        this.doubleValueField.setPreventInvalidInput(true);
        this.doubleValueField.setMaxLength(6);
        HorizontalLayout horizontalLayout = new HorizontalLayout(this.doubleValueField);
        horizontalLayout.setSpacing(false);
        this.add(horizontalLayout);
    }

    @Override
    public void setValue(Double value) {
        this.doubleValueField.setValue(value.toString());
    }

    @Override
    protected Double generateModelValue() {
        return Double.parseDouble(this.doubleValueField.getValue());
    }

    @Override
    protected void setPresentationValue(Double newPresentationValue) {
        if (newPresentationValue.isNaN()) {
            this.doubleValueField.setValue("");
        }
    }
}
