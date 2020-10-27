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

import local.example.seed.document.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface CustomerReactiveService {

    Mono<Customer> create(Customer customer);
    Mono<Customer> read(String id);
    Flux<Customer> readAll();
    Mono<Void> update(Customer customer);
    Mono<Customer> updateName(String id, String name);
    Mono<Customer> updateSurname(String id, String surname);
    Mono<Customer> updateEmail(String id, String email);
    Mono<Void> delete(String id);
}
