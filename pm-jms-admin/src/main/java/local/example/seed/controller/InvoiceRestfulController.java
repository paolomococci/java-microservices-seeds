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

import local.example.seed.embedded.InvoiceEmbedded;
import local.example.seed.model.Invoice;
import local.example.seed.response.InvoiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class InvoiceRestfulController {

    @Autowired
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String INVOICE_RESTFUL_BASE_URI = "http://127.0.0.1:8081/invoices";

    public void create(Invoice invoice)
            throws RestClientException {
        this.restTemplate.postForObject(
                INVOICE_RESTFUL_BASE_URI,
                invoice,
                Invoice.class
        );
    }

    public Invoice read(String uri)
            throws RestClientException {
        return this.restTemplate.getForObject(
                uri,
                Invoice.class
        );
    }

    public Collection<Invoice> readAll()
            throws RestClientException {
        Collection<Invoice> invoices = new ArrayList<>();
        ResponseEntity<InvoiceResponse> responseEntity = this.restTemplate.getForEntity(
                INVOICE_RESTFUL_BASE_URI,
                InvoiceResponse.class
        );
        InvoiceEmbedded embedded = Objects.requireNonNull(responseEntity.getBody()).get_embedded();
        invoices.addAll(embedded.getInvoices());
        return invoices;
    }

    public Invoice findByCode(String code)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("code", code);
        // TODO
        return new Invoice();
    }

    public void update(Invoice invoice, String uri)
            throws RestClientException {
        this.restTemplate.put(uri, invoice);
    }

    public void partialUpdate(Invoice invoice, String uri)
            throws RestClientException {
        this.restTemplate.patchForObject(uri, invoice, Invoice.class);
    }

    public void delete(String uri)
            throws RestClientException {
        this.restTemplate.delete(uri);
    }
}
