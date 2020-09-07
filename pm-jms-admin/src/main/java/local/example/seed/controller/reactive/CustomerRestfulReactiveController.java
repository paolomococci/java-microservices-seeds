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

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import local.example.seed.model.Customer;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CustomerRestfulReactiveController {

    private static final String CUSTOMER_REACTIVE_BASE_URI = "http://127.0.0.1:8081/api/restful/customers";

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
        this.webClient.post().body(
                Mono.justOrEmpty(customer),
                Customer.class
        );
    }

    public Customer read(String id)
            throws WebClientResponseException {
        return this.webClient.get()
                .uri("/"+id)
                .retrieve()
                .onStatus(
                        httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty()
                )
                .bodyToMono(Customer.class)
                .block();
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
        // TODO
        return null;
    }

    public Stream<Customer> streamOfAllCustomers() {
        // TODO
        return null;
    }

    public Customer findByEmail(String email)
            throws WebClientResponseException {
        // TODO
        return null;
    }

    public void update(Customer customer, String id)
            throws WebClientResponseException {
        // TODO
    }

    public void partialUpdate(Customer customer, String id)
            throws WebClientResponseException {
        // TODO
    }

    public void delete(String id)
            throws WebClientResponseException {
        // TODO
    }
}
