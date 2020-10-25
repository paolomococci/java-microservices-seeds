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
import local.example.seed.controller.ItemRestfulReactiveController;
import local.example.seed.field.PriceField;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Item;
import local.example.seed.model.util.Link;
import local.example.seed.service.ItemRestfulRetrieverService;
import reactor.core.publisher.Mono;

import java.util.Optional;

@PageTitle(value = "item reactive editor")
@Route(value = "item-reactive-editor", layout = MainLayout.class)
public class ItemReactiveEditorView
        extends Main {

    private final Grid<Item> itemGrid;
    private final Binder<Item> itemBinder;

    private Item item;
    private final ItemRestfulReactiveController itemRestfulReactiveController;

    private TextField code;
    private TextField name;
    private TextField description;
    private PriceField price;

    private final Button cancel;
    private final Button update;
    private final Button create;
    private final Button delete;

    public ItemReactiveEditorView() {

        this.itemRestfulReactiveController = new ItemRestfulReactiveController();

        this.itemBinder = new Binder<>(Item.class);
        this.itemBinder.bindInstanceFields(this);

        this.item = new Item();

        this.itemGrid = new Grid<>();
        this.itemGrid.setItems(
                ItemRestfulRetrieverService.getListOfItems()
        );
        this.itemGrid.addColumn(Item::getCode).setHeader("code").setSortable(true).setTextAlign(ColumnTextAlign.START);
        this.itemGrid.addColumn(Item::getName).setHeader("name").setSortable(true);
        this.itemGrid.addColumn(Item::getDescription).setHeader("description").setSortable(false);
        this.itemGrid.addColumn(Item::getPrice).setHeader("price").setSortable(true);
        this.itemGrid.addThemeVariants(
                GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_ROW_STRIPES
        );
        this.itemGrid.asSingleSelect().addValueChangeListener(listener -> {
            if (listener.getValue() != null) {
                Optional<Mono<Item>> itemFromBackend = Optional.ofNullable(
                        this.itemRestfulReactiveController.read(
                                listener.getValue().get_links().getSelf().getHref()
                        ));
                if (itemFromBackend.isPresent()) {
                    this.populate(itemFromBackend.get().block());
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
                if (this.item != null) {
                    this.itemBinder.writeBean(this.item);
                    this.itemRestfulReactiveController.update(
                            this.item,
                            this.item.get_links().getSelf().getHref()
                    );
                    this.clear();
                    this.refresh();
                    this.reload();
                    Notification.show("item details have been updated");
                }
            } catch (ValidationException validationException) {
                Notification.show("sorry, the item details have not been updated");
                validationException.printStackTrace();
            }
        });

        this.create = new Button("create");
        this.create.addClickListener(listener -> {
            try {
                if (!this.code.getValue().isEmpty()) {
                    this.item = new Item(
                            this.code.getValue(),
                            this.name.getValue(),
                            this.description.getValue(),
                            this.price.getValue(),
                            new Link()
                    );
                    this.itemBinder.writeBean(this.item);
                    this.itemRestfulReactiveController.create(
                            this.item
                    );
                    this.clear();
                    this.refresh();
                    this.reload();
                    Notification.show("new item's details have been created");
                }
            } catch (ValidationException validationException) {
                Notification.show("sorry, the item details have not been created");
                validationException.printStackTrace();
            }
        });

        this.delete = new Button("delete");
        this.delete.addClickListener(listener -> {
            try {
                if (this.item != null) {
                    this.itemBinder.writeBean(this.item);
                    this.itemRestfulReactiveController.delete(
                            this.item.get_links().getSelf().getHref()
                    );
                    this.clear();
                    this.refresh();
                    this.reload();
                    Notification.show("the selected item has been deleted");
                }
            } catch (ValidationException validationException) {
                Notification.show("sorry, the selected item has not been deleted");
                validationException.printStackTrace();
            }
        });

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        TextField  filterCodeField = new TextField();
        filterCodeField.setPlaceholder("filter by code");
        filterCodeField.setClearButtonVisible(true);
        filterCodeField.setValueChangeMode(ValueChangeMode.LAZY);
        filterCodeField.addFocusShortcut(
                Key.KEY_F, KeyModifier.ALT
        );
        filterCodeField.addValueChangeListener(
                listener -> this.showItemList(listener.getValue())
        );

        this.showItemList("");

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
        this.code.setAutofocus(true);
        addFormItem(divEditor, formLayout, this.code, "code");
        addFormItem(divEditor, formLayout, this.name, "name");
        addFormItem(divEditor, formLayout, this.description, "description");
        addFormItem(divEditor, formLayout, this.price, "price");
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
        divWrapper.add(this.itemGrid);
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
        this.itemGrid.select(null);
        this.itemGrid.getDataProvider().refreshAll();
    }

    private void clear() {
        this.populate(
                new Item("", "", "", Double.NaN, null)
        );
    }

    private void populate(Item item) {
        this.item = item;
        this.itemBinder.readBean(this.item);
        this.price.setValue(this.item.getPrice());
    }

    private void reload() {
        this.itemGrid.setItems(
                ItemRestfulRetrieverService.getListOfItems()
        );
    }

    private void showItemList(String code) {
        // TODO
    }
}
