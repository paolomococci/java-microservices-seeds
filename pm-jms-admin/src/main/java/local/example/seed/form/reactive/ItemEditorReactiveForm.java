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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import local.example.seed.controller.reactive.ItemRestfulReactiveController;
import local.example.seed.handler.ItemChangeHandler;
import local.example.seed.model.Item;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class ItemEditorReactiveForm
        extends FormLayout {

    private final ItemRestfulReactiveController itemRestfulReactiveController;
    private Item item;
    private Binder<Item> itemBinder;
    private ItemChangeHandler itemChangeHandler;

    private final TextField code;
    private final TextField name;
    private final TextArea description;
    private final TextField price;
    private final VerticalLayout fields;

    private final Button save;
    private final Button delete;
    private final Button cancel;
    private final HorizontalLayout buttons;

    private final VerticalLayout form;

    @Autowired
    public ItemEditorReactiveForm(
            ItemRestfulReactiveController itemRestfulReactiveController
    ) {
        super();
        this.itemRestfulReactiveController = itemRestfulReactiveController;

        this.code = new TextField("code");
        this.name = new TextField("name");
        this.description = new TextArea("description");
        this.price = new TextField("price");
        this.fields = new VerticalLayout();

        this.save = new Button(
                "save",
                VaadinIcon.PLUS_CIRCLE_O.create()
        );
        this.delete = new Button(
                "delete",
                VaadinIcon.TRASH.create()
        );
        this.cancel = new Button(
                "cancel",
                VaadinIcon.ASTERISK.create()
        );
        this.buttons = new HorizontalLayout();

        this.form = new VerticalLayout();
    }

    private void save() {
        if (this.item != null) {
            this.itemRestfulReactiveController.create(this.item);
            this.itemChangeHandler.onChange();
        }
    }

    private void delete() {
        if (this.item.getId() != null) {
            this.itemRestfulReactiveController.delete(this.item.getId());
            this.itemChangeHandler.onChange();
        }
    }

    private void edit(Item temp) {
        String id = temp.getId();
        final boolean alreadyExisting = temp.getId() != null;
        if (alreadyExisting) {
            // TODO
        } else {
            // TODO
        }
        // TODO
    }

    public void setItemChangeHandler(ItemChangeHandler itemChangeHandler) {
        this.itemChangeHandler = itemChangeHandler;
    }

    public final void editItem(Item temp) {
        if (temp != null) {
            this.edit(temp);
        } else {
            this.setVisible(false);
        }
    }
}
