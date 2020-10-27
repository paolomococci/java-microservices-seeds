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

package local.example.seed.client.impl;

import local.example.seed.client.KanbanClient;
import local.example.seed.model.Kanban;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component("KanbanClient")
public class KanbanClientImpl
        implements KanbanClient {

    private WebClient webClient;

    public KanbanClientImpl() {
        this.webClient = WebClient.create("http://127.0.0.1:8092/kanbans");
    }

    @Override
    public Mono<Kanban> create(Kanban kanban) {
        return this.webClient.post()
                .body(Mono.just(kanban), Kanban.class)
                .retrieve()
                .bodyToMono(Kanban.class);
    }

    @Override
    public Mono<Kanban> read(String id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Kanban.class);
    }

    @Override
    public Flux<Kanban> readAll() {
        return this.webClient.get()
                .retrieve()
                .bodyToFlux(Kanban.class);
    }

    @Override
    public Mono<Kanban> update(Kanban kanban, String id) {
        return this.webClient.put()
                .uri("/{id}", id)
                .body(Mono.just(kanban), Kanban.class)
                .retrieve()
                .bodyToMono(Kanban.class);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
