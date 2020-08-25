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

import local.example.seed.assembler.CustomerRepresentationModelAssembler;
import local.example.seed.document.Customer;
import local.example.seed.repository.CustomerReactiveCrudRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

@RepositoryRestController
@RequestMapping(value = "/api/reactive/customers", produces = "application/hal+json")
public class CustomerReactiveRestController {

    @Autowired
    CustomerReactiveCrudRestRepository customerReactiveCrudRestRepository;

    @Autowired
    CustomerRepresentationModelAssembler customerRepresentationModelAssembler;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer customer) {
        Mono<Customer> result = this.customerReactiveCrudRestRepository.save(customer);
        EntityModel<Customer> entityModelOfCustomer = this.customerRepresentationModelAssembler.toModel(result.block());
        return new ResponseEntity<>(entityModelOfCustomer, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> read(@PathVariable String id)
            throws URISyntaxException {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> putUpdate(@RequestBody Customer updated, @PathVariable String id)
            throws URISyntaxException {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> patchUpdate(@RequestBody Customer customer, @PathVariable String id)
            throws URISyntaxException {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id)
            throws URISyntaxException {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
