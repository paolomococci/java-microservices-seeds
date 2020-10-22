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
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateField
        extends CustomField<String> {

    private final DatePicker datePicker = new DatePicker();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DateField() {
        this.setLabel("date");
        this.datePicker.setMin(LocalDate.now());
        HorizontalLayout horizontalLayout = new HorizontalLayout(this.datePicker);
        this.add(horizontalLayout);
    }

    @Override
    protected String generateModelValue() {
        LocalDate localDate = this.datePicker.getValue();
        return localDate.format(this.dateTimeFormatter);
    }

    @Override
    protected void setPresentationValue(String newPresentationValue) {
        if (newPresentationValue == null) {
            this.datePicker.setValue(LocalDate.now());
        }
    }
}
