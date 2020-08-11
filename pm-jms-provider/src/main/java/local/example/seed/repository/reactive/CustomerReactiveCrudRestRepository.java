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

package local.example.seed.repository.reactive;

import local.example.seed.model.Customer;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RepositoryRestResource
public interface CustomerReactiveCrudRestRepository
        extends ReactiveCrudRepository<Customer, String> {

    Mono<Customer> findByEmail(@Param("email") String email);
    Flux<Customer> findAllByName(@Param("name") String name);
    Flux<Customer> findAllBySurname(@Param("surname") String surname);
}
