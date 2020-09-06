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

import local.example.seed.model.Invoice;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class InvoiceRestfulController {

    private static final String INVOICE_RESTFUL_BASE_URI = "http://127.0.0.1:8080/invoices";

    private final RestTemplate restTemplate;

    public InvoiceRestfulController() {
        this.restTemplate = new RestTemplate();
    }

    public void create(Invoice invoice)
            throws RestClientException {
        this.restTemplate.postForObject(
                INVOICE_RESTFUL_BASE_URI,
                invoice,
                Invoice.class
        );
    }

    public Invoice read(String id)
            throws RestClientException {
        ResponseEntity<Invoice> responseEntity = this.restTemplate
                .exchange(INVOICE_RESTFUL_BASE_URI +"/"+id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public List<Invoice> readAll()
            throws RestClientException {
        ResponseEntity<List<Invoice>> responseEntity = this.restTemplate
                .exchange(INVOICE_RESTFUL_BASE_URI,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public Collection<Invoice> collectionOfAllInvoices() {
        // TODO
        return null;
    }

    public Stream<Invoice> streamOfAllInvoices() {
        // TODO
        return null;
    }

    public Invoice findByCode(String code) {
        // TODO
        return null;
    }

    public void update(Invoice invoice, String id) {
        // TODO
    }

    public void partialUpdate(Invoice invoice, String id) {
        // TODO
    }

    public void delete(String id) {
        // TODO
    }
}
