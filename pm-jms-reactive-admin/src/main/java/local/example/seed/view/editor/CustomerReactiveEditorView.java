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

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import local.example.seed.controller.CustomerRestfulReactiveController;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Customer;
import local.example.seed.model.util.Link;

@PageTitle(value = "customer reactive editor")
@Route(value = "customer-reactive-editor", layout = MainLayout.class)
public class CustomerReactiveEditorView
        extends Main {

    private Grid<Customer> customerGrid;
    private Binder<Customer> customerBinder;

    private Customer customer;
    private CustomerRestfulReactiveController customerRestfulReactiveController;

    private TextField name;
    private TextField surname;
    private EmailField email;

    private Button cancel;
    private Button update;
    private Button create;
    private Button delete;

    public CustomerReactiveEditorView() {

        this.customerGrid = new Grid<>(Customer.class);
        this.customerBinder = new Binder<>(Customer.class);
        this.customerBinder.bindInstanceFields(this);

        this.customer = new Customer();

        this.customerRestfulReactiveController = new CustomerRestfulReactiveController();

        this.cancel = new Button("cancel");
        this.cancel.addClickListener(listener -> {
            this.clear();
            this.refresh();
        });

        this.update = new Button("update");
        this.update.addClickListener(listener -> {
            try {
                if (this.customer != null) {
                    this.customerBinder.writeBean(this.customer);
                    this.customerRestfulReactiveController.update(
                            this.customer,
                            this.customer.get_links().getSelf().getHref()
                    );
                    this.clear();
                    this.refresh();
                    this.reload();
                    Notification.show("customer details have been updated");
                }
            } catch (ValidationException validationException) {
                Notification.show("sorry, the customer details have not been updated");
                validationException.printStackTrace();
            }
        });

        this.create = new Button("create");
        this.create.addClickListener(listener -> {
            try {
                if (
                        !this.name.getValue().isEmpty() &
                                !this.surname.getValue().isEmpty() &
                                !this.email.getValue().isEmpty()
                ) {
                    this.customer = new Customer(
                            this.name.getValue(),
                            this.surname.getValue(),
                            this.email.getValue(),
                            new Link()
                    );
                    this.customerBinder.writeBean(this.customer);
                    this.customerRestfulReactiveController.create(
                            this.customer
                    );
                    this.clear();
                    this.refresh();
                    this.reload();
                    Notification.show("new customer's details have been created");
                }
            } catch (ValidationException validationException) {
                Notification.show("sorry, the customer details have not been created");
                validationException.printStackTrace();
            }
        });

        this.delete = new Button("delete");
        this.delete.addClickListener(listener -> {
            try {
                if (this.customer != null) {
                    this.customerBinder.writeBean(this.customer);
                    this.customerRestfulReactiveController.delete(
                            this.customer.get_links().getSelf().getHref()
                    );
                    this.clear();
                    this.refresh();
                    this.reload();
                    Notification.show("the selected customer has been deleted");
                }
            } catch (ValidationException validationException) {
                Notification.show("sorry, the selected customer has not been deleted");
                validationException.printStackTrace();
            }
        });

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        TextField filterEmailField = new TextField();
        filterEmailField.setPlaceholder("filter by email");
        filterEmailField.setClearButtonVisible(true);
        filterEmailField.setValueChangeMode(ValueChangeMode.LAZY);
        filterEmailField.addFocusShortcut(
                Key.KEY_F, KeyModifier.ALT
        );
        filterEmailField.addValueChangeListener(
                listener -> this.showCustomerList(listener.getValue())
        );

        // TODO grid

        this.createGridLayout(splitLayout);
        this.createEditorLayout(splitLayout);
        this.add(splitLayout);
    }

    private void createEditorLayout(
            SplitLayout splitLayout
    ) {

    }

    private void createButtonLayout(
            Div divEditorLayout
    ) {

    }

    private void createGridLayout(
            SplitLayout splitLayout
    ) {

    }

    private void addFormItem(
            Div divWrapper,
            FormLayout formLayout,
            AbstractField abstractField,
            String fieldName
    ) {

    }

    private void refresh() {

    }

    private void clear() {

    }

    private void populate(Customer customer) {

    }

    private void reload() {

    }

    private void showCustomerList(String email) {
        // TODO
    }
}
