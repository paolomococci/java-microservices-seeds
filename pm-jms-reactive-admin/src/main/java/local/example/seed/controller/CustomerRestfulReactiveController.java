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

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import local.example.seed.model.Customer;
import local.example.seed.response.CustomerResponse;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CustomerRestfulReactiveController {

    private static final String CUSTOMER_REACTIVE_BASE_URI = "http://127.0.0.1:8082/api/restful/customers";

    private final WebClient webClient;

    public CustomerRestfulReactiveController() {
        TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 6000)
                .doOnConnected(
                        connection -> {
                            connection.addHandlerLast(new ReadTimeoutHandler(6000, TimeUnit.MILLISECONDS));
                            connection.addHandlerLast(new WriteTimeoutHandler(6000, TimeUnit.MILLISECONDS));
                        }
                );
        this.webClient = WebClient
                .builder()
                .baseUrl(CUSTOMER_REACTIVE_BASE_URI)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void create(Customer customer)
            throws WebClientResponseException {
        this.webClient
                .post()
                .uri(CUSTOMER_REACTIVE_BASE_URI)
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
                        httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
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

    public List<Customer> readAll()
            throws WebClientResponseException {
        List<Customer> listOfCustomers = new ArrayList<>();
        Flux<Customer> fluxOfCustomers = this.webClient.get()
                .retrieve()
                .bodyToFlux(Customer.class);
        Iterable<Customer> fluxOfCustomersIterable = fluxOfCustomers.toIterable();
        for (Customer customer:fluxOfCustomersIterable) {
            listOfCustomers.add(customer);
        }
        return listOfCustomers;
    }

    public Collection<Customer> collectionOfAllCustomers() {
        Collection<Customer> collectionOfCustomers = new ArrayList<>();
        List<Customer> customers = this.readAll();
        for (Customer customer:customers) {
            collectionOfCustomers.add(customer);
        }
        return collectionOfCustomers;
    }

    public Stream<Customer> streamOfAllCustomers() {
        return this.readAll().stream();
    }

    public Customer findByEmail(String email)
            throws WebClientResponseException {
        // TODO
        return null;
    }

    public void update(Customer customer, String id)
            throws WebClientResponseException {
        this.webClient.put()
                .uri("/"+id)
                .body(
                        Mono.justOrEmpty(customer),
                        Customer.class
                );
    }

    public void partialUpdate(Customer customer, String id)
            throws WebClientResponseException {
        this.webClient.patch()
                .uri("/"+id)
                .body(
                        Mono.justOrEmpty(customer),
                        Customer.class
                );
    }

    public void delete(String id)
            throws WebClientResponseException {
        this.webClient.delete().uri("/"+id);
    }
    
    private Flux<CustomerResponse> getResponseFlux(int size)
            throws WebClientResponseException {
        return this.webClient.get()
                .uri(CUSTOMER_REACTIVE_BASE_URI+"?page=0&size={size}", size)
                .accept(MediaTypes.HAL_JSON)
                .retrieve()
                .onStatus(
                        httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> {
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            String errorMessage = String.format(
                                    " HTTP status error: 404 --- customer not found, an error occurred during a request to the customers uri: %s ---",
                                    CUSTOMER_REACTIVE_BASE_URI.toString()
                            );
                            System.out.println(timestamp + errorMessage);
                            return Mono.empty();
                        }
                )
                .bodyToFlux(CustomerResponse.class)
                .doOnError(exception -> {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String errorMessage = String.format(
                            " ERROR: --- Connection refused, an error occurred during a request to the customers uri: %s, probably the host is down! ---",
                            CUSTOMER_REACTIVE_BASE_URI.toString()
                    );
                    System.out.println(timestamp + errorMessage);
                });
    }
}
