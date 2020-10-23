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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import local.example.seed.controller.CustomerRestfulReactiveController;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Customer;

@PageTitle(value = "customer reactive editor")
@Route(value = "customer-reactive-editor", layout = MainLayout.class)
public class CustomerReactiveEditorView
        extends Main {

    private Grid<Customer> customerGrid;
    private Binder<Customer> customerBinder;

    private Customer customer;
    private CustomerRestfulReactiveController customerRestfulReactiveController;

    private TextField name;
    private TextField surname;
    private EmailField email;

    private Button cancel;
    private Button update;
    private Button create;
    private Button delete;

    public CustomerReactiveEditorView() {
    }

    private void showCustomerList(String email) {
        // TODO
    }
}
