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

import local.example.seed.model.Item;
import local.example.seed.response.ItemResponse;
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

public class ItemRestfulReactiveController {

    @Autowired
    private final WebClient webClient = WebClient.create();

    private static final String HTTP_127_0_0_1_8082_API_RESTFUL_ITEMS = "http://127.0.0.1:8082/api/restful/items";

    public void create(Item item)
            throws WebClientResponseException {
        this.webClient
                .post()
                .uri(HTTP_127_0_0_1_8082_API_RESTFUL_ITEMS)
                .body(Mono.just(item), Item.class)
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
                            " ERROR: --- Connection refused occurred during a request create item, probably the host is down! ---\n" +
                            item.toString());
                })
                .block();
    }

    public Mono<Item> read(String uri)
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
                                    " HTTP status error: 404 --- item not found, an error occurred during a request to the item's uri: %s ---",
                                    uri
                            );
                            System.out.println(timestamp + errorMessage);
                            return Mono.empty();
                        }
                )
                .bodyToMono(Item.class)
                .doOnError(exception -> {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String errorMessage = String.format(
                            " ERROR: --- Connection refused, an error occurred during a request to the item's uri: %s, probably the host is down! ---",
                            uri
                    );
                    System.out.println(timestamp + errorMessage);
                })
                .onErrorResume(exception -> Mono.empty());
    }

    public List<Item> readAll() {
        return this.getResponseFlux().blockFirst().get_embedded().getItems();
    }

    public Collection<Item> collectionOfAllItems() {
        return new ArrayList<>(this.readAll());
    }

    public Stream<Item> streamOfAllItems() {
        return this.readAll().stream();
    }

    public void update(Item item, String uri)
            throws WebClientResponseException {
        this.webClient
                .put()
                .uri(uri)
                .body(Mono.just(item), Item.class)
                .accept(MediaTypes.HAL_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        clientResponse -> {
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            String errorMessage = String.format(
                                    " HTTP status error: 404 --- item not found, an error occurred during a request to the item's uri: %s ---",
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
                            " ERROR: --- Connection refused, an error occurred during a request to the item's uri: %s, probably the host is down! ---",
                            uri
                    );
                    System.out.println(timestamp + errorMessage);
                })
                .block();
    }

    public void partialUpdate(Item item, String id)
            throws WebClientResponseException {
        this.webClient.patch()
                .uri("/"+id)
                .body(
                        Mono.justOrEmpty(item),
                        Item.class
                );
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
                                    " HTTP status error: 404 --- item not found, an error occurred during a request to the item's uri: %s ---",
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
                            " ERROR: --- Connection refused, an error occurred during a request to the item's uri: %s, probably the host is down! ---",
                            uri
                    );
                    System.out.println(timestamp + errorMessage);
                })
                .block();
    }

    public Mono<Item> findByCode(String code)
            throws WebClientResponseException {
        return this.webClient
                .get()
                .uri("http://127.0.0.1:8082/api/restful/items/search/findByCode?code={code}", code)
                .accept(MediaTypes.HAL_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        clientResponse -> {
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            String errorMessage = String.format(
                                    " HTTP status error: 404 --- item not found, an error occurred during a search request for the item's code: %s ---",
                                    code
                            );
                            System.out.println(timestamp + errorMessage);
                            return Mono.empty();
                        }
                )
                .bodyToMono(Item.class)
                .doOnError(exception -> {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String errorMessage = String.format(
                            " ERROR: --- Connection refused, an error occurred during a search request for the item's code: %s, probably the host is down! ---",
                            code
                    );
                    System.out.println(timestamp + errorMessage);
                })
                .onErrorResume(exception -> Mono.empty());
    }

    private Flux<ItemResponse> getResponseFlux()
            throws WebClientResponseException {
        return this.webClient.get()
                .uri(HTTP_127_0_0_1_8082_API_RESTFUL_ITEMS)
                .accept(MediaTypes.HAL_JSON)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        clientResponse -> {
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            String errorMessage = String.format(
                                    " HTTP status error: 404 --- items not found, an error occurred during a request to the items url: %s ---",
                                    HTTP_127_0_0_1_8082_API_RESTFUL_ITEMS
                            );
                            System.out.println(timestamp + errorMessage);
                            return Mono.empty();
                        }
                )
                .bodyToFlux(ItemResponse.class)
                .doOnError(exception -> {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    String errorMessage = String.format(
                            " ERROR: --- Connection refused, an error occurred during a request to the items url: %s, probably the host is down! ---",
                            HTTP_127_0_0_1_8082_API_RESTFUL_ITEMS
                    );
                    System.out.println(timestamp + errorMessage);
                });
    }
}
