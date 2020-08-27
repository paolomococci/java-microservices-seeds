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

package local.example.seed.controller;

import local.example.seed.model.Customer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;

public class CustomerRestfulController {

    private static final String CUSTOMERS_URI = "http://127.0.0.1:8080/customers";
    private final RestTemplate restTemplate;

    public CustomerRestfulController() {
        this.restTemplate = new RestTemplate();
    }

    public void create(Customer customer) {
        // TODO
    }

    public Customer read(String id)
            throws URISyntaxException {
        ResponseEntity<Customer> responseEntity = this.restTemplate
                .exchange(CUSTOMERS_URI +"/"+id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public List<Customer> readAll()
            throws URISyntaxException {
        ResponseEntity<List<Customer>> responseEntity = this.restTemplate
                .exchange(CUSTOMERS_URI,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
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