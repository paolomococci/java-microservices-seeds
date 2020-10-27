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

import local.example.seed.document.Invoice;
import local.example.seed.repository.InvoiceReactiveMongoRestfulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/invoices")
public class InvoiceReactiveRestController {

    @Autowired
    private InvoiceReactiveMongoRestfulRepository invoiceReactiveMongoRestfulRepository;

    @PostMapping
    public Mono<Invoice> save(@RequestBody Invoice invoice) {
        return this.invoiceReactiveMongoRestfulRepository.save(invoice);
    }

    @GetMapping(path = "/{id}")
    public Mono<Invoice> read(@PathVariable String id) {
        return this.invoiceReactiveMongoRestfulRepository.findById(id);
    }

    @GetMapping
    public Flux<Invoice> readAll() {
        return this.invoiceReactiveMongoRestfulRepository.findAll();
    }
}
