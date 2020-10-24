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
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import local.example.seed.controller.InvoiceRestfulReactiveController;
import local.example.seed.field.DateField;
import local.example.seed.field.TotalField;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Invoice;
import local.example.seed.model.util.Link;
import reactor.core.publisher.Mono;

import java.util.Optional;

@PageTitle(value = "invoice reactive editor")
@Route(value = "invoice-reactive-editor", layout = MainLayout.class)
public class InvoiceReactiveEditorView
        extends Main {

    private Grid<Invoice> invoiceGrid;
    private Binder<Invoice> invoiceBinder;

    private Invoice invoice;
    private InvoiceRestfulReactiveController invoiceRestfulReactiveController;

    private TextField code;
    private DateField date;
    private TotalField total;

    private Button cancel;
    private Button update;
    private Button create;
    private Button delete;

    public InvoiceReactiveEditorView() {

        this.invoiceRestfulReactiveController = new InvoiceRestfulReactiveController();

        this.invoiceGrid = new Grid<>();
        this.invoiceGrid.setItems(
                this.invoiceRestfulReactiveController.readAll()
        );
        this.invoiceGrid.addColumn(Invoice::getCode).setHeader("code").setSortable(true).setTextAlign(ColumnTextAlign.START);
        this.invoiceGrid.addColumn(Invoice::getDate).setHeader("date").setSortable(true);
        this.invoiceGrid.addColumn(Invoice::getTotal).setHeader("total").setSortable(true);
        this.invoiceGrid.addThemeVariants(
                GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_ROW_STRIPES
        );
        this.invoiceGrid.asSingleSelect().addValueChangeListener(listener -> {
            if (listener.getValue() != null) {
                Optional<Mono<Invoice>> invoiceFromBackend = Optional.ofNullable(
                        this.invoiceRestfulReactiveController.read(
                                listener.getValue().get_links().getSelf().getHref()
                        ));
                if (invoiceFromBackend.isPresent()) {
                    this.populate(invoiceFromBackend.get().block());
                } else {
                    this.refresh();
                }
            } else {
                this.clear();
            }
        });

        this.invoiceBinder = new Binder<>(Invoice.class);
        this.invoiceBinder.bindInstanceFields(this);

        this.invoice = new Invoice();

        this.cancel = new Button("cancel");
        this.cancel.addClickListener(listener -> {
            this.clear();
            this.refresh();
        });

        this.update = new Button("update");
        this.update.addClickListener(listener -> {
            try {
                if (this.invoice != null) {
                    this.invoiceBinder.writeBean(this.invoice);
                    this.invoiceRestfulReactiveController.update(
                            this.invoice,
                            this.invoice.get_links().getSelf().getHref()
                    );
                    this.clear();
                    this.refresh();
                    this.reload();
                    Notification.show("invoice details have been updated");
                }
            } catch (ValidationException validationException) {
                Notification.show("sorry, the invoice details have not been updated");
                validationException.printStackTrace();
            }
        });

        this.create = new Button("create");
        this.create.addClickListener(listener -> {
            try {
                if (!this.code.getValue().isEmpty()) {
                    this.invoice = new Invoice(
                            this.code.getValue(),
                            this.date.getValue(),
                            this.total.getValue(),
                            "",
                            new Link()
                    );
                    this.invoiceBinder.writeBean(this.invoice);
                    this.invoiceRestfulReactiveController.create(
                            this.invoice
                    );
                    this.clear();
                    this.refresh();
                    this.reload();
                    Notification.show("new invoice's details have been created");
                }
            } catch (ValidationException validationException) {
                Notification.show("sorry, the invoice details have not been created");
                validationException.printStackTrace();
            }
        });

        this.delete = new Button("delete");
        this.delete.addClickListener(listener -> {
            try {
                if (this.invoice != null) {
                    this.invoiceBinder.writeBean(this.invoice);
                    this.invoiceRestfulReactiveController.delete(
                            this.invoice.get_links().getSelf().getHref()
                    );
                    this.clear();
                    this.refresh();
                    this.reload();
                    Notification.show("the selected invoice has been deleted");
                }
            } catch (ValidationException validationException) {
                Notification.show("sorry, the selected invoice has not been deleted");
                validationException.printStackTrace();
            }
        });

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        TextField filterCodeField = new TextField();
        filterCodeField.setPlaceholder("filter by code");
        filterCodeField.setClearButtonVisible(true);
        filterCodeField.setValueChangeMode(ValueChangeMode.LAZY);
        filterCodeField.addFocusShortcut(
                Key.KEY_F, KeyModifier.ALT
        );
        filterCodeField.addValueChangeListener(
                listener -> this.showInvoiceList(listener.getValue())
        );

        this.showInvoiceList("");

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

    private void populate(Invoice invoice) {

    }

    private void reload() {

    }

    private void showInvoiceList(String code) {
        // TODO
    }
}
