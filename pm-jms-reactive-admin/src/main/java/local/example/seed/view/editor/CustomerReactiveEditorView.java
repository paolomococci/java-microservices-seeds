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
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
import reactor.core.publisher.Mono;

import java.util.Optional;

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

        this.customerRestfulReactiveController = new CustomerRestfulReactiveController();

        this.customerGrid = new Grid<>();
        this.customerGrid.setItems(
                this.customerRestfulReactiveController.readAll()
        );
        this.customerGrid.addColumn(Customer::getName).setHeader("name").setSortable(true).setTextAlign(ColumnTextAlign.START);
        this.customerGrid.addColumn(Customer::getSurname).setHeader("surname").setSortable(true);
        this.customerGrid.addColumn(Customer::getEmail).setHeader("email").setSortable(true);
        this.customerGrid.addThemeVariants(
                GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_ROW_STRIPES
        );
        this.customerGrid.asSingleSelect().addValueChangeListener(listener -> {
            if (listener.getValue() != null) {
                Optional<Mono<Customer>> customerFromBackend = Optional.ofNullable(
                        this.customerRestfulReactiveController.read(
                                listener.getValue().get_links().getSelf().getHref()
                        ));
                if (customerFromBackend.isPresent()) {
                    this.populate(customerFromBackend.get().block());
                } else {
                    this.refresh();
                }
            } else {
                this.clear();
            }
        });

        this.customerBinder = new Binder<>(Customer.class);
        this.customerBinder.bindInstanceFields(this);

        this.customer = new Customer();

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

        this.showCustomerList("");

        this.createGridLayout(splitLayout);
        this.createEditorLayout(splitLayout);
        this.add(splitLayout);
    }

    private void createEditorLayout(
            SplitLayout splitLayout
    ) {
        Div divEditorLayout = new Div();
        Div divEditor = new Div();
        divEditorLayout.add(divEditor);
        FormLayout formLayout = new FormLayout();
        this.name.setAutofocus(true);
        this.email.setClearButtonVisible(true);
        addFormItem(divEditor, formLayout, this.name, "name");
        addFormItem(divEditor, formLayout, this.surname, "surname");
        addFormItem(divEditor, formLayout, this.email, "email");
        createButtonLayout(divEditorLayout);
        splitLayout.addToSecondary(divEditorLayout);
    }

    private void createButtonLayout(
            Div divEditorLayout
    ) {
        HorizontalLayout buttonHorizontalLayout = new HorizontalLayout();
        buttonHorizontalLayout.setWidthFull();
        buttonHorizontalLayout.setSpacing(true);
        this.cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        this.update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.create.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        this.delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonHorizontalLayout.add(
                this.cancel, this.update, this.create, this.delete
        );
        buttonHorizontalLayout.setSpacing(true);
        buttonHorizontalLayout.setMargin(true);
        divEditorLayout.add(buttonHorizontalLayout);
    }

    private void createGridLayout(
            SplitLayout splitLayout
    ) {
        Div divWrapper = new Div();
        divWrapper.setWidthFull();
        splitLayout.addToPrimary(divWrapper);
        divWrapper.add(this.customerGrid);
    }

    private void addFormItem(
            Div divWrapper,
            FormLayout formLayout,
            AbstractField abstractField,
            String fieldName
    ) {
        formLayout.addFormItem(abstractField, fieldName);
        divWrapper.add(formLayout);
    }

    private void refresh() {
        this.customerGrid.select(null);
        this.customerGrid.getDataProvider().refreshAll();
    }

    private void clear() {
        this.populate(null);
    }

    private void populate(Customer customer) {
        this.customer = customer;
        this.customerBinder.readBean(this.customer);
    }

    private void reload() {
        this.customerGrid.setItems(customerRestfulReactiveController.readAll());
    }

    private void showCustomerList(String email) {
        // TODO
    }
}
