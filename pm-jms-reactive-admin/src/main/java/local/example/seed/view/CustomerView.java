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

package local.example.seed.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import local.example.seed.layout.MainLayout;
import local.example.seed.model.Customer;
import local.example.seed.service.CustomerRestfulRetrieverService;
import org.springframework.web.server.ResponseStatusException;

@PageTitle(value = "customer view")
@Route(value = "customer", layout = MainLayout.class)
public class CustomerView
        extends Main {

    private final Grid<Customer> customerGrid;

    public CustomerView() {
        super();
        this.customerGrid = new Grid<>();
        this.customerGrid.addColumn(Customer::getName).setHeader("name").setSortable(true).setTextAlign(ColumnTextAlign.START);
        this.customerGrid.addColumn(Customer::getSurname).setHeader("surname").setSortable(true);
        this.customerGrid.addColumn(Customer::getEmail).setHeader("email").setSortable(true);
        Button retrieveButton = new Button(
                "recovers all customers",
                VaadinIcon.ARROW_CIRCLE_DOWN_O.create(),
                listener -> {
                    try {
                        this.customerGrid.setItems(CustomerRestfulRetrieverService.getListOfCustomers());
                    } catch (
                            ResponseStatusException exception) {
                        exception.printStackTrace();
                    }
                });
        retrieveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.add(retrieveButton, this.customerGrid);
    }
}
