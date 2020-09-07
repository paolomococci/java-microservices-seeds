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
import local.example.seed.model.Invoice;
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

public class InvoiceRestfulReactiveController {

    private static final String INVOICE_REACTIVE_BASE_URI = "http://127.0.0.1:8080/api/restful/invoices";
    private final WebClient webClient;

    public InvoiceRestfulReactiveController() {
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
                .baseUrl(INVOICE_REACTIVE_BASE_URI)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void create(Invoice invoice)
            throws WebClientResponseException {
        this.webClient.post().body(
                Mono.justOrEmpty(invoice),
                Invoice.class
        );
    }

    public Invoice read(String id)
            throws WebClientResponseException {
        return this.webClient.get()
                .uri("/"+id)
                .retrieve()
                .onStatus(
                        httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty()
                )
                .bodyToMono(Invoice.class)
                .block();
    }

    public List<Invoice> readAll()
            throws WebClientResponseException {
        List<Invoice> listOfInvoices = new ArrayList<>();
        Flux<Invoice> fluxOfInvoices = this.webClient.get()
                .retrieve()
                .bodyToFlux(Invoice.class);
        Iterable<Invoice> fluxOfInvoicesIterable = fluxOfInvoices.toIterable();
        for (Invoice invoice:fluxOfInvoicesIterable) {
            listOfInvoices.add(invoice);
        }
        return listOfInvoices;
    }

    public Collection<Invoice> collectionOfAllInvoices() {
        Collection<Invoice> collectionOfInvoices = new ArrayList<>();
        List<Invoice> invoices = this.readAll();
        for (Invoice invoice:invoices) {
            collectionOfInvoices.add(invoice);
        }
        return collectionOfInvoices;
    }

    public Stream<Invoice> streamOfAllInvoices() {
        return this.readAll().stream();
    }

    public Invoice findByCode(String code)
            throws WebClientResponseException {
        // TODO
        return null;
    }

    public void update(Invoice invoice, String id)
            throws WebClientResponseException {
        // TODO
    }

    public void partialUpdate(Invoice invoice, String id)
            throws WebClientResponseException {
        // TODO
    }

    public void delete(String id)
            throws WebClientResponseException {
        // TODO
    }
}
