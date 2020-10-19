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

import local.example.seed.embedded.CustomerEmbedded;
import local.example.seed.model.Customer;
import local.example.seed.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerRestfulController {

    @Autowired
    private final RestTemplate restTemplate = new RestTemplate();

    private static final URI CUSTOMER_RESTFUL_BASE_URI = URI.create("http://127.0.0.1:8081/customers");

    public void create(Customer customer)
            throws RestClientException {
        this.restTemplate.postForObject(
                CUSTOMER_RESTFUL_BASE_URI,
                customer,
                Customer.class
        );
    }

    public Customer read(String uri)
            throws RestClientException {
        return this.restTemplate.getForObject(
                uri,
                Customer.class
        );
    }

    public Collection<Customer> readAll()
            throws RestClientException {
        Collection<Customer> customers = new ArrayList<>();
        ResponseEntity<CustomerResponse> responseEntity = this.restTemplate.getForEntity(
                CUSTOMER_RESTFUL_BASE_URI,
                CustomerResponse.class
        );
        CustomerEmbedded embedded = responseEntity.getBody().get_embedded();
        for (Customer customer: embedded.getCustomers()) {
            customers.add(customer);
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp + " --- INFO --- number of customers: " + customers.size());
        customers.forEach(System.out::println);
        return customers;
    }

    public Customer findByEmail(String email)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("email", email);
        // TODO
        return new Customer();
    }

    public void update(Customer customer, String uri)
            throws RestClientException {
        this.restTemplate.put(uri, customer);
    }

    public void partialUpdate(Customer customer, String uri)
            throws RestClientException {
        this.restTemplate.patchForObject(uri, customer, Customer.class);
    }

    public void delete(String uri)
            throws RestClientException {
        this.restTemplate.delete(uri);
    }
}
