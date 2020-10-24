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
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import local.example.seed.controller.ItemRestfulReactiveController;
import local.example.seed.field.PriceField;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Item;
import reactor.core.publisher.Mono;

import java.util.Optional;

@PageTitle(value = "item reactive editor")
@Route(value = "item-reactive-editor", layout = MainLayout.class)
public class ItemReactiveEditorView
        extends Main {

    private Grid<Item> itemGrid;
    private Binder<Item> itemBinder;

    private Item item;
    private ItemRestfulReactiveController itemRestfulReactiveController;

    private TextField code;
    private TextField name;
    private TextField description;
    private PriceField price;

    private Button cancel;
    private Button update;
    private Button create;
    private Button delete;

    public ItemReactiveEditorView() {

        this.itemRestfulReactiveController = new ItemRestfulReactiveController();

        this.itemGrid = new Grid<>();
        this.itemGrid.setItems(
                this.itemRestfulReactiveController.readAll()
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

        this.itemBinder = new Binder<>(Item.class);
        this.itemBinder.bindInstanceFields(this);

        this.item = new Item();

        this.cancel = new Button("cancel");
        this.update = new Button("update");
        this.create = new Button("create");
        this.delete = new Button("delete");

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

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

    private void populate(Item item) {

    }

    private void reload() {

    }

    private void showItemList(String code) {
        // TODO
    }
}
