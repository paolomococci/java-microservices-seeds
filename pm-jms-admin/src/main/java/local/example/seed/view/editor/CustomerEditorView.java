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

package local.example.seed.view.editor;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import local.example.seed.controller.CustomerRestfulController;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Customer;

@PageTitle(value = "customer editor")
@Route(value = "customer-editor", layout = MainLayout.class)
public class CustomerEditorView
        extends Main {

    private final CustomerRestfulController customerRestfulController;

    private final Grid<Customer> customerGrid;
    private final TextField filterEmailField;
    private final Button addCustomer;
    private final HorizontalLayout tools;

    public CustomerEditorView() {
        customerRestfulController = new CustomerRestfulController();

        this.customerGrid = new Grid<>();
        this.customerGrid.setItems(this.customerRestfulController.readAll());
        this.customerGrid.addColumn(Customer::getName).setHeader("name").setSortable(true).setTextAlign(ColumnTextAlign.START);
        this.customerGrid.addColumn(Customer::getSurname).setHeader("surname").setSortable(true);
        this.customerGrid.addColumn(Customer::getEmail).setHeader("email").setSortable(true);
        this.customerGrid.addThemeVariants(
                GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_ROW_STRIPES
        );

        this.customerGrid.asSingleSelect().addValueChangeListener(
                listener -> {
                    // TODO
                }
        );

        this.filterEmailField = new TextField();
        this.filterEmailField.setPlaceholder("filter by email");
        this.filterEmailField.setClearButtonVisible(true);
        this.filterEmailField.setValueChangeMode(ValueChangeMode.LAZY);
        this.filterEmailField.addFocusShortcut(
                Key.KEY_F, KeyModifier.ALT
        );
        this.filterEmailField.addValueChangeListener(
                listener -> {
                    this.showCustomerList(listener.getValue());
                }
        );

        this.addCustomer = new Button("add customer", VaadinIcon.PLUS_CIRCLE_O.create());
        this.addCustomer.addClickListener(
                listener -> {
                    // TODO
                }
        );
        this.addCustomer.addClickShortcut(Key.NUMPAD_ADD, KeyModifier.CONTROL);

        this.tools = new HorizontalLayout(this.addCustomer);

        this.add(
                this.customerGrid,
                this.tools
        );

        this.showCustomerList("");
    }

    private void showCustomerList(String email) {
        // TODO
    }
}
