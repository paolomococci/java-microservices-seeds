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

import local.example.seed.model.Customer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerRestfulRetrieverService {

    private static final URI RESTFUL_BASE_URI = URI.create("http://127.0.0.1:8082/api/reactive");

    public static List<Customer> getListOfCustomers() {
        Traverson traverson = new Traverson(RESTFUL_BASE_URI, MediaTypes.HAL_JSON);
        Traverson.TraversalBuilder traversalBuilder = traverson.follow("customers");
        ParameterizedTypeReference<CollectionModel<Customer>> parameterizedTypeReference;
        parameterizedTypeReference = new ParameterizedTypeReference<>() {};
        CollectionModel<Customer> collectionModelOfCustomers;
        collectionModelOfCustomers = traversalBuilder.toObject(parameterizedTypeReference);
        Collection<Customer> collectionOfCustomers = Objects.requireNonNull(collectionModelOfCustomers).getContent();
        return new ArrayList<>(collectionOfCustomers);
    }
}
