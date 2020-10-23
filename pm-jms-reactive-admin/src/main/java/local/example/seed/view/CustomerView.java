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

import java.net.URI;
import java.net.URISyntaxException;

@PageTitle(value = "customer view")
@Route(value = "customer", layout = MainLayout.class)
public class CustomerView
        extends Main {

    private static final String RESTFUL_BASE_URI = "http://127.0.0.1:8082//api/reactive";

    private final Grid<Customer> customerGrid;
    private final Button retrieveButton;

    public CustomerView() {
        super();
        this.customerGrid = new Grid<>();
        this.customerGrid.addColumn(customer -> customer.getName()).setHeader("name").setSortable(true).setTextAlign(ColumnTextAlign.START);
        this.customerGrid.addColumn(customer -> customer.getSurname()).setHeader("surname").setSortable(true);
        this.customerGrid.addColumn(customer -> customer.getEmail()).setHeader("email").setSortable(true);
        this.retrieveButton = new Button(
                "recovers all customers",
                VaadinIcon.ARROW_CIRCLE_DOWN_O.create(),
                listener -> {
                    try {
                        this.customerGrid.setItems(CustomerRestfulRetrieverService.getListOfCustomers(new URI(RESTFUL_BASE_URI)));
                    } catch (
                            ResponseStatusException | URISyntaxException exception) {
                        exception.printStackTrace();
                    }
                });
        this.retrieveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.add(this.retrieveButton, this.customerGrid);
    }
}
