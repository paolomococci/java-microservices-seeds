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

import java.util.*;
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
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        return this.restTemplate.getForObject(
                INVOICE_RESTFUL_BASE_URI,
                Invoice.class,
                param
        );
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
        List<Invoice> invoices = this.readAll();
        Collection<Invoice> collectionOfInvoice = new ArrayList<>();
        for (Invoice invoice:invoices) {
            collectionOfInvoice.add(invoice);
        }
        return collectionOfInvoice;
    }

    public Stream<Invoice> streamOfAllInvoices() {
        List<Invoice> invoices = this.readAll();
        return invoices.stream();
    }

    public Invoice findByCode(String code)
            throws RestClientException {
        // TODO
        return null;
    }

    public void update(Invoice invoice, String id)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        this.restTemplate.put(
                INVOICE_RESTFUL_BASE_URI,
                invoice,
                param
        );
    }

    public void partialUpdate(Invoice invoice, String id)
            throws RestClientException {
        // TODO
    }

    public void delete(String id)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        this.restTemplate.delete(
                INVOICE_RESTFUL_BASE_URI,
                param
        );
    }
}
