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

import local.example.seed.document.Item;
import local.example.seed.repository.ItemReactiveMongoRestfulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/items")
public class ItemReactiveRestController {

    @Autowired
    private ItemReactiveMongoRestfulRepository itemReactiveMongoRestfulRepository;

    @PostMapping
    public Mono<Item> save(@RequestBody Item item) {
        return this.itemReactiveMongoRestfulRepository.save(item);
    }

    @GetMapping(path = "/{id}")
    public Mono<Item> read(@PathVariable String id) {
        return this.itemReactiveMongoRestfulRepository.findById(id);
    }

    @GetMapping
    public Flux<Item> readAll() {
        return this.itemReactiveMongoRestfulRepository.findAll();
    }
}
