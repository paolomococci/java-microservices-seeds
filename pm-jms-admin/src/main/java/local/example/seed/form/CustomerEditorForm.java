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

package local.example.seed.form;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import local.example.seed.controller.CustomerRestfulController;
import local.example.seed.handler.CustomerChangeHandler;
import local.example.seed.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;

@UIScope
@SpringComponent
public class CustomerEditorForm
        extends FormLayout {

    private final CustomerRestfulController customerRestfulController;
    private Customer customer;
    private Binder<Customer> customerBinder;
    private CustomerChangeHandler customerChangeHandler;

    private final TextField name;
    private final TextField surname;
    private final TextField email;
    private final VerticalLayout fields;

    private final Button save;
    private final Button delete;
    private final Button cancel;
    private final HorizontalLayout buttons;

    private final VerticalLayout form;

    @Autowired
    public CustomerEditorForm(
            CustomerRestfulController customerRestfulController
    ) {
        super();
        this.customerRestfulController = customerRestfulController;

        this.name = new TextField("name");
        this.surname = new TextField("surname");
        this.email = new TextField("email");
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
                    this.editItem(this.customer);
                    this.setVisible(false);
                }
        );
        this.cancel.addClickShortcut(Key.ESCAPE);

        this.buttons = new HorizontalLayout();

        this.form = new VerticalLayout();
    }

    private void save() {
        if (this.customer != null) {
            this.customerRestfulController.create(this.customer);
            this.customerChangeHandler.onChange();
        }
    }

    private void delete() {
        if (this.customer.getId() != null) {
            this.customerRestfulController.delete(this.customer.getId());
            this.customerChangeHandler.onChange();
        }
    }

    private void edit(Customer temp) {
        String id = temp.getId();
        final boolean alreadyExisting = temp.getId() != null;
        if (alreadyExisting) {
            try {
                this.customer = this.customerRestfulController.read(id);
            } catch (URISyntaxException uriSyntaxException) {
                uriSyntaxException.printStackTrace();
            }
        } else {
            this.customer = temp;
        }
        this.cancel.setVisible(alreadyExisting);
        this.customerBinder.setBean(this.customer);
        this.setVisible(true);
        this.name.focus();
    }

    public void setCustomerChangeHandler(CustomerChangeHandler customerChangeHandler) {
        this.customerChangeHandler = customerChangeHandler;
    }

    public final void editItem(Customer temp) {
        if (temp != null) {
            this.edit(temp);
        } else {
            this.setVisible(false);
        }
    }
}
