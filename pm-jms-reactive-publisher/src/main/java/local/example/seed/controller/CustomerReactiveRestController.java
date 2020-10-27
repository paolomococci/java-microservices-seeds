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

import local.example.seed.document.Customer;
import local.example.seed.repository.CustomerReactiveMongoRestfulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/customers")
public class CustomerReactiveRestController {

    @Autowired
    private CustomerReactiveMongoRestfulRepository customerReactiveMongoRestfulRepository;

    @PostMapping
    public Mono<Customer> save(@RequestBody Customer customer) {
        return this.customerReactiveMongoRestfulRepository.save(customer);
    }

    @GetMapping(path = "/{id}")
    public Mono<Customer> read(@PathVariable String id) {
        return this.customerReactiveMongoRestfulRepository.findById(id);
    }

    @GetMapping
    public Flux<Customer> readAll() {
        return this.customerReactiveMongoRestfulRepository.findAll();
    }
}
