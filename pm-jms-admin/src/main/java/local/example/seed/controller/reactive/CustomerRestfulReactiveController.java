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

package local.example.seed.controller.reactive;

import local.example.seed.model.Customer;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.stream.Stream;

public class CustomerRestfulReactiveController {

    private static final String CUSTOMER_REACTIVE_BASE_URI = "http://127.0.0.1:8081/api/restful/customers";
    private final WebClient webClient;

    public CustomerRestfulReactiveController() {
        this.webClient = WebClient.create();
    }

    public void create(Customer customer) {
        // TODO
    }

    public Customer read(String id) {
        // TODO
        return null;
    }

    public void readAll() {
        // TODO
    }

    public Collection<Customer> collectionOfAllCustomers() {
        // TODO
        return null;
    }

    public Stream<Customer> streamOfAllCustomers() {
        // TODO
        return null;
    }

    public Customer findByEmail(String email) {
        // TODO
        return null;
    }

    public void update(Customer customer, String id) {
        // TODO
    }

    public void partialUpdate(Customer customer, String id) {
        // TODO
    }

    public void delete(String id) {
        // TODO
    }
}
