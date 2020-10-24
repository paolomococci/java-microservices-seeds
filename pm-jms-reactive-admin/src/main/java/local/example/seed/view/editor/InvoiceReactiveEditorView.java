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
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import local.example.seed.controller.InvoiceRestfulReactiveController;
import local.example.seed.field.DateField;
import local.example.seed.field.TotalField;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Invoice;

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

        this.invoiceGrid = new Grid<>(Invoice.class);
        this.invoiceBinder = new Binder<>(Invoice.class);
        this.invoiceBinder.bindInstanceFields(this);

        this.invoice = new Invoice();

        this.invoiceRestfulReactiveController = new InvoiceRestfulReactiveController();

        this.cancel = new Button("cancel");
        this.update = new Button("update");
        this.create = new Button("create");
        this.delete = new Button("delete");

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

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

    private void populate(Invoice invoice) {

    }

    private void reload() {

    }

    private void showInvoiceList(String code) {
        // TODO
    }
}
