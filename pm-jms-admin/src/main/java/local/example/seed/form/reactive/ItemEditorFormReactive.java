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

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import local.example.seed.controller.reactive.ItemRestfulReactiveController;
import local.example.seed.handler.ItemChangeHandler;
import local.example.seed.model.Item;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class ItemEditorFormReactive
        extends FormLayout {

    private final ItemRestfulReactiveController itemRestfulReactiveController;
    private Item item;
    private Binder<Item> itemBinder;
    private ItemChangeHandler itemChangeHandler;

    @Autowired
    public ItemEditorFormReactive(
            ItemRestfulReactiveController itemRestfulReactiveController
    ) {
        super();
        this.itemRestfulReactiveController = itemRestfulReactiveController;
    }
}
