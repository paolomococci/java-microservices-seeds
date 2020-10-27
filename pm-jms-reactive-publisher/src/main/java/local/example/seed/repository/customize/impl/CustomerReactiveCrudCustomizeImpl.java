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

package local.example.seed.repository.customize.impl;

import local.example.seed.document.Customer;
import local.example.seed.repository.customize.CustomerReactiveCrudCustomize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

public class CustomerReactiveCrudCustomizeImpl
        implements CustomerReactiveCrudCustomize {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public CustomerReactiveCrudCustomizeImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Customer> updateName(String id, String name) {
        return this.reactiveMongoTemplate.findAndModify(
                new Query(Criteria.where("id").is(id)),
                new Update().set("name", name),
                Customer.class
        );
    }

    @Override
    public Mono<Customer> updateSurname(String id, String surname) {
        return this.reactiveMongoTemplate.findAndModify(
                new Query(Criteria.where("id").is(id)),
                new Update().set("surname", surname),
                Customer.class
        );
    }

    @Override
    public Mono<Customer> updateEmail(String id, String email) {
        return this.reactiveMongoTemplate.findAndModify(
                new Query(Criteria.where("id").is(id)),
                new Update().set("email", email),
                Customer.class
        );
    }
}
