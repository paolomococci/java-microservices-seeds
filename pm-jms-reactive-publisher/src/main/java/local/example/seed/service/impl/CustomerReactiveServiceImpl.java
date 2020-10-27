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

package local.example.seed.service.impl;

import local.example.seed.document.Customer;
import local.example.seed.repository.CustomerReactiveMongoRestfulRepository;
import local.example.seed.service.CustomerReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("CustomerReactiveService")
public class CustomerReactiveServiceImpl
        implements CustomerReactiveService {

    @Autowired
    CustomerReactiveMongoRestfulRepository customerReactiveMongoRestfulRepository;

    @Override
    public Mono<Customer> create(Customer customer) {
        return this.customerReactiveMongoRestfulRepository.save(customer);
    }

    @Override
    public Mono<Customer> read(String id) {
        return this.customerReactiveMongoRestfulRepository.findById(id);
    }

    @Override
    public Flux<Customer> readAll() {
        return this.customerReactiveMongoRestfulRepository.findAll();
    }

    @Override
    public Mono<Void> update(Customer customer) {
        return this.customerReactiveMongoRestfulRepository.save(customer).then();
    }

    @Override
    public Mono<Customer> updateName(String id, String name) {
        return this.customerReactiveMongoRestfulRepository.updateName(id, name);
    }

    @Override
    public Mono<Customer> updateSurname(String id, String surname) {
        return this.customerReactiveMongoRestfulRepository.updateSurname(id, surname);
    }

    @Override
    public Mono<Customer> updateEmail(String id, String email) {
        return this.customerReactiveMongoRestfulRepository.updateEmail(id, email);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.customerReactiveMongoRestfulRepository.deleteById(id);
    }
}
