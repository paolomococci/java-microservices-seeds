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
import local.example.seed.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class CustomerRestfulReactiveController {

    @Autowired
    private final WebClient webClient = WebClient.create();

    private static final String HTTP_127_0_0_1_8082_API_RESTFUL_CUSTOMERS = "http://127.0.0.1:8082/api/restful/customers";

    public void create(Customer customer)
            throws WebClientResponseException {
        this.webClient
                .post()
                .uri(HTTP_127_0_0_1_8082_API_RESTFUL_CUSTOMERS)
                .body(Mono.just(customer), Customer.class)
                .accept(MediaTypes.HAL_JSON)
                .retrieve()
                .onStatus(
                        httpStatus -> !HttpStatus.CREATED.equals(httpStatus),
                        clientResponse -> Mono.empty()
                )
                .bodyToMono(Void.class)
                .doOnError(exception -> {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    System.out.println(timestamp +
                            " ERROR: --- Connection refused occurred during a request create customer, probably the host is down! ---\n" +
                            customer.toString());
                })
                .block();
    }

    public Mono<Customer> read(String uri)
            throws WebClientResponseException {
        return this.webClient
                .get()
                .uri(uri)
                .accept(MediaTypes.HAL_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        clientResponse -> {
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            String errorMessage = String.format(
                                    " HTTP status error: 404 --- customer not found, an error occurred during a request to the customer's uri: %s ---",
                                    uri
                            );
                            System.out.println(timestamp + errorMessage);
                            return Mono.empty();
                        }
                )
                .bodyToMono(Customer.class)
                .doOnError(exception -> {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String errorMessage = String.format(
                            " ERROR: --- Connection refused, an error occurred during a request to the customer's uri: %s, probably the host is down! ---",
                            uri
                    );
                    System.out.println(timestamp + errorMessage);
                })
                .onErrorResume(exception -> Mono.empty());
    }

    public List<Customer> readAll() {
        return this.getResponseFlux().blockFirst().get_embedded().getCustomers();
    }

    public Collection<Customer> collectionOfAllCustomers() {
        return new ArrayList<>(this.readAll());
    }

    public Stream<Customer> streamOfAllCustomers() {
        return this.readAll().stream();
    }

    public void update(Customer customer, String uri)
            throws WebClientResponseException {
        this.webClient
                .put()
                .uri(uri)
                .body(Mono.just(customer), Customer.class)
                .accept(MediaTypes.HAL_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        clientResponse -> {
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            String errorMessage = String.format(
                                    " HTTP status error: 404 --- customer not found, an error occurred during a request to the customer's uri: %s ---",
                                    uri
                            );
                            System.out.println(timestamp + errorMessage);
                            return Mono.empty();
                        }
                )
                .bodyToMono(Void.class)
                .doOnError(exception -> {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String errorMessage = String.format(
                            " ERROR: --- Connection refused, an error occurred during a request to the customer's uri: %s, probably the host is down! ---",
                            uri
                    );
                    System.out.println(timestamp + errorMessage);
                })
                .block();
    }

    public void delete(String uri)
            throws WebClientResponseException {
        this.webClient
                .delete()
                .uri(uri)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        clientResponse -> {
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            String errorMessage = String.format(
                                    " HTTP status error: 404 --- customer not found, an error occurred during a request to the customer's uri: %s ---",
                                    uri
                            );
                            System.out.println(timestamp + errorMessage);
                            return Mono.empty();
                        }
                )
                .bodyToMono(Void.class)
                .doOnError(exception -> {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String errorMessage = String.format(
                            " ERROR: --- Connection refused, an error occurred during a request to the customer's uri: %s, probably the host is down! ---",
                            uri
                    );
                    System.out.println(timestamp + errorMessage);
                })
                .block();
    }

    public Mono<Customer> findByEmail(String email)
            throws WebClientResponseException {
        return this.webClient
                .get()
                .uri("http://127.0.0.1:8082/api/restful/customers/search/findByEmail?email={email}", email)
                .accept(MediaTypes.HAL_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        clientResponse -> {
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            String errorMessage = String.format(
                                    " HTTP status error: 404 --- customer not found, an error occurred during a search request for the customer's email: %s ---",
                                    email
                            );
                            System.out.println(timestamp + errorMessage);
                            return Mono.empty();
                        }
                )
                .bodyToMono(Customer.class)
                .doOnError(exception -> {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String errorMessage = String.format(
                            " ERROR: --- Connection refused, an error occurred during a search request for the customer's email: %s, probably the host is down! ---",
                            email
                    );
                    System.out.println(timestamp + errorMessage);
                })
                .onErrorResume(exception -> Mono.empty());
    }

    private Flux<CustomerResponse> getResponseFlux()
            throws WebClientResponseException {
        return this.webClient.get()
                .uri(HTTP_127_0_0_1_8082_API_RESTFUL_CUSTOMERS)
                .accept(MediaTypes.HAL_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        clientResponse -> {
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            String errorMessage = String.format(
                                    " HTTP status error: 404 --- customer not found, an error occurred during a request to the customers url: %s ---",
                                    HTTP_127_0_0_1_8082_API_RESTFUL_CUSTOMERS
                            );
                            System.out.println(timestamp + errorMessage);
                            return Mono.empty();
                        }
                )
                .bodyToFlux(CustomerResponse.class)
                .doOnError(exception -> {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String errorMessage = String.format(
                            " ERROR: --- Connection refused, an error occurred during a request to the customers url: %s, probably the host is down! ---",
                            HTTP_127_0_0_1_8082_API_RESTFUL_CUSTOMERS
                    );
                    System.out.println(timestamp + errorMessage);
                });
    }
}
