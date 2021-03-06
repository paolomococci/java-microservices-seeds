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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import local.example.seed.controller.InvoiceRestfulController;
import local.example.seed.field.DateField;
import local.example.seed.field.TotalField;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Invoice;
import local.example.seed.model.util.Link;

import java.util.Optional;

@PageTitle(value = "invoice editor")
@Route(value = "invoice-editor", layout = MainLayout.class)
public class InvoiceEditorView
        extends Main {

    private final InvoiceRestfulController invoiceRestfulController;

    private final Grid<Invoice> invoiceGrid;
    private final Binder<Invoice> invoiceBinder;
    private Invoice invoice;

    private TextField code;
    private DateField date;
    private TotalField total;

    private final Button cancel;
    private final Button update;
    private final Button create;
    private final Button delete;

    public InvoiceEditorView() {
        this.invoiceRestfulController = new InvoiceRestfulController();
        this.invoice = new Invoice();

        this.invoiceBinder = new Binder<>(Invoice.class);
        this.invoiceBinder.bindInstanceFields(this);

        this.invoiceGrid = new Grid<>();
        this.invoiceGrid.setItems(
                this.invoiceRestfulController.readAll()
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
                Optional<Invoice> invoiceFromBackend = Optional.ofNullable(
                        this.invoiceRestfulController.read(
                                listener.getValue().get_links().getSelf().getHref()
                        ));
                if (invoiceFromBackend.isPresent()) {
                    this.populate(invoiceFromBackend.get());
                } else {
                    this.refresh();
                }
            } else {
                this.clear();
            }
        });

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
                    this.invoiceRestfulController.update(
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
                    this.invoiceRestfulController.create(
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
                    this.invoiceRestfulController.delete(
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

    private void createGridLayout(
            SplitLayout splitLayout
    ) {
        Div divWrapper = new Div();
        divWrapper.setWidthFull();
        splitLayout.addToPrimary(divWrapper);
        divWrapper.add(this.invoiceGrid);
    }

    private void createEditorLayout(
            SplitLayout splitLayout
    ) {
        Div divEditorLayout = new Div();
        Div divEditor = new Div();
        divEditorLayout.add(divEditor);
        FormLayout formLayout = new FormLayout();
        this.code.setAutofocus(true);
        addFormItem(divEditor, formLayout, this.code, "code");
        addFormItem(divEditor, formLayout, this.date, "date");
        addFormItem(divEditor, formLayout, this.total, "total");
        createButtonLayout(divEditorLayout);
        splitLayout.addToSecondary(divEditorLayout);
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

    private void refresh() {
        this.invoiceGrid.select(null);
        this.invoiceGrid.getDataProvider().refreshAll();
    }

    private void clear() {
        this.populate(new Invoice("", "", Double.NaN, "", null));
    }

    private void populate(Invoice invoice) {
        this.invoice = invoice;
        this.invoiceBinder.readBean(this.invoice);
        this.date.setValue(this.invoice.getDate());
        this.total.setValue(this.invoice.getTotal());
    }

    private void reload() {
        this.invoiceGrid.setItems(invoiceRestfulController.readAll());
    }

    private void showInvoiceList(String code) {
        // TODO
    }
}
