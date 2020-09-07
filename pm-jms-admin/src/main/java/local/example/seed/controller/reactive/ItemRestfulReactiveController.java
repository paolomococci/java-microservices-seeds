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
import local.example.seed.model.Item;
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

public class ItemRestfulReactiveController {

    private static final String ITEM_REACTIVE_BASE_URI = "http://127.0.0.1:8080/api/restful/items";
    private final WebClient webClient;

    public ItemRestfulReactiveController() {
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
                .baseUrl(ITEM_REACTIVE_BASE_URI)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void create(Item item)
            throws WebClientResponseException {
        this.webClient.post()
                .body(
                        Mono.justOrEmpty(item),
                        Item.class
                );
    }

    public Item read(String id)
            throws WebClientResponseException {
        return this.webClient.get()
                .uri("/"+id)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        clientResponse -> Mono.empty()
                )
                .bodyToMono(Item.class)
                .block();
    }

    public List<Item> readAll()
            throws WebClientResponseException {
        List<Item> listOfItems = new ArrayList<>();
        Flux<Item> fluxOfItems = this.webClient.get()
                .retrieve()
                .bodyToFlux(Item.class);
        Iterable<Item> fluxOfItemsIterable = fluxOfItems.toIterable();
        for (Item item:fluxOfItemsIterable) {
            listOfItems.add(item);
        }
        return listOfItems;
    }

    public Collection<Item> collectionOfAllItems() {
        Collection<Item> collectionOfItems = new ArrayList<>();
        List<Item> items = this.readAll();
        for (Item item:items) {
            collectionOfItems.add(item);
        }
        return collectionOfItems;
    }

    public Stream<Item> streamOfAllItems() {
        return this.readAll().stream();
    }

    public Item findByCode(String code)
            throws WebClientResponseException {
        // TODO
        return null;
    }

    public void update(Item item, String id)
            throws WebClientResponseException {
        this.webClient.put()
                .uri("/"+id)
                .body(
                        Mono.justOrEmpty(item),
                        Item.class
                );
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

    public void delete(String id)
            throws WebClientResponseException {
        this.webClient.delete().uri("/"+id);
    }
}
