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

package local.example.seed.service;

import local.example.seed.document.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InvoiceReactiveMongoDBService {

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<Invoice> findById(String id) {
        return reactiveMongoTemplate.findById(id, Invoice.class);
    }

    public Flux<Invoice> findAll() {
        return reactiveMongoTemplate.findAll(Invoice.class);
    }

    public Mono<Invoice> save(Mono<Invoice> invoiceMono) {
        return reactiveMongoTemplate.save(invoiceMono);
    }
}
