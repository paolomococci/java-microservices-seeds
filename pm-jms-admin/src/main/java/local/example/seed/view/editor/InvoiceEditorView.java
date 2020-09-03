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

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import local.example.seed.form.InvoiceEditorForm;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle(value = "invoice editor")
@Route(value = "invoice-editor", layout = MainLayout.class)
public class InvoiceEditorView
        extends Main {

    private final Grid<Invoice> invoiceGrid;
    private final InvoiceEditorForm invoiceEditorForm;

    @Autowired
    public InvoiceEditorView(
            InvoiceEditorForm invoiceEditorForm
    ) {
        super();

        this.invoiceEditorForm = invoiceEditorForm;

        this.invoiceGrid = new Grid<>();
        this.invoiceGrid.addColumn(invoice -> invoice.getCode()).setHeader("code").setSortable(true).setTextAlign(ColumnTextAlign.START);
        this.invoiceGrid.addColumn(invoice -> invoice.getDate()).setHeader("date").setSortable(true);
        this.invoiceGrid.addColumn(invoice -> invoice.getTotal()).setHeader("total").setSortable(true);

        this.add(this.invoiceGrid);
    }
}
