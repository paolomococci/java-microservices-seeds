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

package local.example.seed.handler;

import local.example.seed.document.Customer;
import local.example.seed.service.CustomerReactiveService;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CustomerReactiveHandler {

    private final MediaType mediaType = MediaTypes.HAL_JSON;
    private final CustomerReactiveService customerReactiveService;

    public CustomerReactiveHandler(CustomerReactiveService customerReactiveService) {
        this.customerReactiveService = customerReactiveService;
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
        return ServerResponse.ok()
                .contentType(this.mediaType)
                .body(customerMono.flatMap(this.customerReactiveService::create), Customer.class);
    }

    public Mono<ServerResponse> read(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(this.mediaType)
                .body(
                        this.customerReactiveService.read(serverRequest.pathVariable("id")),
                        Customer.class
                );
    }

    public Mono<ServerResponse> readAll(ServerRequest serverRequest) {
        // TODO
        return Mono.empty();
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
        return ServerResponse.ok()
                .contentType(this.mediaType)
                .body(customerMono.flatMap(this.customerReactiveService::update), Void.class);
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(this.mediaType)
                .body(
                        this.customerReactiveService.delete(serverRequest.pathVariable("id")),
                        Void.class
                );
    }
}
