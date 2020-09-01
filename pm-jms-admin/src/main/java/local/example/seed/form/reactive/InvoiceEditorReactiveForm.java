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

package local.example.seed.form.reactive;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import local.example.seed.controller.reactive.InvoiceRestfulReactiveController;
import local.example.seed.handler.InvoiceChangeHandler;
import local.example.seed.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class InvoiceEditorReactiveForm
        extends FormLayout {

    private final InvoiceRestfulReactiveController invoiceRestfulReactiveController;
    private Invoice invoice;
    private Binder<Invoice> invoiceBinder;
    private InvoiceChangeHandler invoiceChangeHandler;

    private final TextField code;
    private final DatePicker date;
    private final TextField total;
    private final VerticalLayout fields;

    private final Button save;
    private final Button delete;
    private final Button cancel;
    private final HorizontalLayout buttons;

    private final VerticalLayout form;

    @Autowired
    public InvoiceEditorReactiveForm(
            InvoiceRestfulReactiveController invoiceRestfulReactiveController
    ) {
        super();
        this.invoiceRestfulReactiveController = invoiceRestfulReactiveController;

        this.code = new TextField("code");
        this.date = new DatePicker("date");
        this.total = new TextField("total");
        this.fields = new VerticalLayout();

        this.save = new Button(
                "save",
                VaadinIcon.PLUS_CIRCLE_O.create()
        );
        this.save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.save.addClickListener(
                listener -> {
                    this.save();
                }
        );
        this.save.addClickShortcut(Key.ENTER);

        this.delete = new Button(
                "delete",
                VaadinIcon.TRASH.create()
        );
        this.delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        this.delete.addClickListener(
                listener -> {
                    this.delete();
                }
        );
        this.delete.addClickShortcut(Key.DELETE, KeyModifier.SHIFT);

        this.cancel = new Button(
                "cancel",
                VaadinIcon.ASTERISK.create()
        );
        this.cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        this.cancel.addClickListener(
                listener -> {
                    this.editItem(this.invoice);
                    this.setVisible(false);
                }
        );
        this.cancel.addClickShortcut(Key.ESCAPE);

        this.buttons = new HorizontalLayout();

        this.form = new VerticalLayout();
    }

    private void save() {
        if (this.invoice != null) {
            this.invoiceRestfulReactiveController.create(this.invoice);
            this.invoiceChangeHandler.onChange();
        }
    }

    private void delete() {
        if (this.invoice.getId() != null) {
            this.invoiceRestfulReactiveController.delete(this.invoice.getId());
            this.invoiceChangeHandler.onChange();
        }
    }

    private void edit(Invoice temp) {
        String id = temp.getId();
        final boolean alreadyExisting = temp.getId() != null;
        if (alreadyExisting) {
            // TODO
        } else {
            this.invoice = temp;
        }
        this.cancel.setVisible(alreadyExisting);
        this.invoiceBinder.setBean(this.invoice);
        this.setVisible(true);
        this.code.focus();
    }

    public void setInvoiceChangeHandler(InvoiceChangeHandler invoiceChangeHandler) {
        this.invoiceChangeHandler = invoiceChangeHandler;
    }

    public final void editItem(Invoice temp) {
        if (temp != null) {
            this.edit(temp);
        } else {
            this.setVisible(false);
        }
    }
}
