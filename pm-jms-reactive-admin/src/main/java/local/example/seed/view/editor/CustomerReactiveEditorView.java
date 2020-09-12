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
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import local.example.seed.controller.CustomerRestfulReactiveController;
import local.example.seed.form.CustomerEditorReactiveForm;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle(value = "customer reactive editor")
@Route(value = "customer-reactive-editor", layout = MainLayout.class)
public class CustomerReactiveEditorView
        extends Main {

    private final Grid<Customer> customerGrid;
    private final CustomerEditorReactiveForm customerEditorReactiveForm;
    private final CustomerRestfulReactiveController customerRestfulReactiveController;
    private final TextField filterEmailField;
    private final Button addCustomer;
    private final HorizontalLayout tools;

    @Autowired
    public CustomerReactiveEditorView(
            CustomerEditorReactiveForm customerEditorReactiveForm,
            CustomerRestfulReactiveController customerRestfulReactiveController
    ) {
        super();

        this.customerEditorReactiveForm = customerEditorReactiveForm;
        this.customerRestfulReactiveController = customerRestfulReactiveController;

        this.customerGrid = new Grid<>();
        this.customerGrid.addColumn(customer -> customer.getName()).setHeader("name").setSortable(true).setTextAlign(ColumnTextAlign.START);
        this.customerGrid.addColumn(customer -> customer.getSurname()).setHeader("surname").setSortable(true);
        this.customerGrid.addColumn(customer -> customer.getEmail()).setHeader("email").setSortable(true);

        this.customerGrid.asSingleSelect().addValueChangeListener(
                listener -> {
                    this.customerEditorReactiveForm.editCustomer(listener.getValue());
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
                    this.customerGrid.asSingleSelect().clear();
                    this.customerEditorReactiveForm.editCustomer(new Customer());
                }
        );
        this.addCustomer.addClickShortcut(Key.NUMPAD_ADD, KeyModifier.CONTROL);

        this.tools = new HorizontalLayout(this.addCustomer);

        this.add(this.customerGrid, this.tools, this.customerEditorReactiveForm);

        this.customerEditorReactiveForm.setCustomerChangeHandler(
                () -> {
                    this.customerEditorReactiveForm.setVisible(false);
                }
        );

        this.showCustomerList("");
    }

    private void showCustomerList(String email) {
        if (email.isEmpty() || email.isBlank()) {
            this.customerGrid.setItems(
                    this.customerRestfulReactiveController.collectionOfAllCustomers()
            );
        } else {
            this.customerGrid.setItems(
                    this.customerRestfulReactiveController.findByEmail(email)
            );
        }
    }
}