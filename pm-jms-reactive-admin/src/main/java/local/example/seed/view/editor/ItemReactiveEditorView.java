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
import local.example.seed.controller.ItemRestfulReactiveController;
import local.example.seed.form.ItemEditorReactiveForm;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Item;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle(value = "item reactive editor")
@Route(value = "item-reactive-editor", layout = MainLayout.class)
public class ItemReactiveEditorView
        extends Main {

    private final Grid<Item> itemGrid;
    private final ItemEditorReactiveForm itemEditorReactiveForm;
    private final ItemRestfulReactiveController itemRestfulReactiveController;
    private final TextField filterCodeField;
    private final Button addItem;
    private final HorizontalLayout tools;

    @Autowired
    public ItemReactiveEditorView(
            ItemEditorReactiveForm itemEditorReactiveForm,
            ItemRestfulReactiveController itemRestfulReactiveController
    ) {
        super();

        this.itemEditorReactiveForm = itemEditorReactiveForm;
        this.itemRestfulReactiveController = itemRestfulReactiveController;

        this.itemGrid = new Grid<>();
        this.itemGrid.addColumn(item -> item.getCode()).setHeader("code").setSortable(true).setTextAlign(ColumnTextAlign.START);
        this.itemGrid.addColumn(item -> item.getName()).setHeader("name").setSortable(true);
        this.itemGrid.addColumn(item -> item.getDescription()).setHeader("description").setSortable(false);

        this.itemGrid.asSingleSelect().addValueChangeListener(
                listener -> {
                    this.itemEditorReactiveForm.editItem(listener.getValue());
                }
        );

        this.filterCodeField = new TextField();
        this.filterCodeField.setPlaceholder("filter by code");
        this.filterCodeField.setClearButtonVisible(true);
        this.filterCodeField.setValueChangeMode(ValueChangeMode.LAZY);
        this.filterCodeField.addFocusShortcut(
                Key.KEY_F, KeyModifier.ALT
        );
        this.filterCodeField.addValueChangeListener(
                listener -> {
                    this.showItemList(listener.getValue());
                }
        );

        this.addItem = new Button("add item", VaadinIcon.PLUS_CIRCLE_O.create());
        this.addItem.addClickListener(
                listener -> {
                    this.itemGrid.asSingleSelect().clear();
                    this.itemEditorReactiveForm.editItem(new Item());
                }
        );
        this.addItem.addClickShortcut(Key.NUMPAD_ADD, KeyModifier.CONTROL);

        this.tools = new HorizontalLayout(this.addItem);

        this.add(this.itemGrid, this.tools, this.itemEditorReactiveForm);

        this.itemEditorReactiveForm.setItemChangeHandler(
                () -> {
                    this.itemEditorReactiveForm.setVisible(false);
                }
        );

        this.showItemList("");
    }

    private void showItemList(String code) {
        if (code.isEmpty() || code.isBlank()) {
            this.itemGrid.setItems(
                    this.itemRestfulReactiveController.collectionOfAllItems()
            );
        } else {
            this.itemGrid.setItems(
                    this.itemRestfulReactiveController.findByCode(code)
            );
        }
    }
}