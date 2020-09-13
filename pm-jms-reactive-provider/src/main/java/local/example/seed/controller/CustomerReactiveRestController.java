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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

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
        EntityModel<Customer> entityModelOfCustomer = this.customerRepresentationModelAssembler.toModel(
                Objects.requireNonNull(result.block())
        );
        return new ResponseEntity<>(entityModelOfCustomer, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> read(@PathVariable String id) {
        Mono<Customer> result = this.customerReactiveCrudRestRepository.findById(id);
        if (result == null || result == Mono.empty().block()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EntityModel<Customer> entityModelOfCustomer = this.customerRepresentationModelAssembler.toModel(
                result.block()
        );
        return new ResponseEntity<>(entityModelOfCustomer, HttpStatus.OK);
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<?> readByCode(@PathVariable String email) {
        Flux<Customer> results = this.customerReactiveCrudRestRepository.findAllByEmail(email);
        if (results == null || null == results.empty().blockFirst()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CollectionModel<EntityModel<Customer>> collectionModelOfCustomers = this.customerRepresentationModelAssembler
                .toCollectionModel(results.toIterable());
        return new ResponseEntity<>(collectionModelOfCustomers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        Flux<Customer> results = this.customerReactiveCrudRestRepository.findAll();
        if (results == null || null == results.empty().blockFirst()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CollectionModel<EntityModel<Customer>> collectionModelOfCustomers = this.customerRepresentationModelAssembler
                .toCollectionModel(results.toIterable());
        return new ResponseEntity<>(collectionModelOfCustomers, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> putUpdate(@RequestBody Customer customer, @PathVariable String id) {
        Mono<Customer> result = this.customerReactiveCrudRestRepository.findById(id);
        if (result == null || result == Mono.empty().block()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer updatable = result.block();
        updatable.setName(customer.getName());
        updatable.setSurname(customer.getSurname());
        updatable.setEmail(customer.getEmail());
        Mono<Customer> updated = this.customerReactiveCrudRestRepository.save(updatable);
        EntityModel<Customer> entityModelOfCustomer = this.customerRepresentationModelAssembler.toModel(
                updated.block()
        );
        return new ResponseEntity<>(entityModelOfCustomer, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> patchUpdate(@RequestBody Customer customer, @PathVariable String id) {
        Mono<Customer> result = this.customerReactiveCrudRestRepository.findById(id);
        if (result == null || result == Mono.empty().block()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Customer updatable = result.block();
        if (customer.getName() != null) updatable.setName(customer.getName());
        if (customer.getSurname() != null) updatable.setSurname(customer.getSurname());
        if (customer.getEmail() != null) updatable.setEmail(customer.getEmail());
        Mono<Customer> updated = this.customerReactiveCrudRestRepository.save(updatable);
        EntityModel<Customer> entityModelOfCustomer = this.customerRepresentationModelAssembler.toModel(
                updated.block()
        );
        return new ResponseEntity<>(entityModelOfCustomer, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Mono<Customer> result = this.customerReactiveCrudRestRepository.findById(id);
        if (result == null || result == Mono.empty().block()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.customerReactiveCrudRestRepository.deleteById(id).subscribe();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
