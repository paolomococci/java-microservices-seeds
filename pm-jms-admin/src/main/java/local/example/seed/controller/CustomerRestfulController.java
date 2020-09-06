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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Stream;

public class CustomerRestfulController {

    private static final String CUSTOMER_RESTFUL_BASE_URI = "http://127.0.0.1:8080/customers";

    private final RestTemplate restTemplate;

    public CustomerRestfulController() {
        this.restTemplate = new RestTemplate();
    }

    public void create(Customer customer)
            throws RestClientException {
        this.restTemplate.postForObject(
                CUSTOMER_RESTFUL_BASE_URI,
                customer,
                Customer.class
        );
    }

    public Customer read(String id)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        return this.restTemplate.getForObject(
                CUSTOMER_RESTFUL_BASE_URI,
                Customer.class,
                param
        );
    }

    public List<Customer> readAll()
            throws RestClientException {
        ResponseEntity<List<Customer>> responseEntity = this.restTemplate.exchange(
                CUSTOMER_RESTFUL_BASE_URI,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public Collection<Customer> collectionOfAllCustomers() {
        List<Customer> customers = this.readAll();
        Collection<Customer> collectionOfCustomer = new ArrayList<>();
        for (Customer customer:customers) {
            collectionOfCustomer.add(customer);
        }
        return collectionOfCustomer;
    }

    public Stream<Customer> streamOfAllCustomers() {
        List<Customer> customers = this.readAll();
        return customers.stream();
    }

    public Customer findByEmail(String email)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("email", email);
        // TODO
        return null;
    }

    public void update(Customer customer, String id)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        this.restTemplate.put(
                CUSTOMER_RESTFUL_BASE_URI,
                customer,
                param
        );
    }

    public void partialUpdate(Customer customer, String id)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        // TODO
    }

    public void delete(String id)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        this.restTemplate.delete(
                CUSTOMER_RESTFUL_BASE_URI,
                param
        );
    }
}
