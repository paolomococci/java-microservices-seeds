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

import local.example.seed.model.Invoice;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collection;
import java.util.stream.Stream;

public class InvoiceRestfulReactiveController {

    private static final String INVOICE_REACTIVE_BASE_URI = "http://127.0.0.1:8080/api/restful/invoices";
    private final WebClient webClient;

    public InvoiceRestfulReactiveController() {
        this.webClient = WebClient.create();
    }

    public void create(Invoice invoice)
            throws WebClientResponseException {
        // TODO
    }

    public Invoice read(String id)
            throws WebClientResponseException {
        // TODO
        return null;
    }

    public void readAll()
            throws WebClientResponseException {
        // TODO
    }

    public Collection<Invoice> collectionOfAllInvoices() {
        // TODO
        return null;
    }

    public Stream<Invoice> streamOfAllInvoices() {
        // TODO
        return null;
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
