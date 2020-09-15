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

import local.example.seed.assembler.ItemRepresentationModelAssembler;
import local.example.seed.document.Item;
import local.example.seed.repository.ItemReactiveCrudRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RepositoryRestController
@RequestMapping(value = "/api/reactive/items", produces = "application/hal+json")
public class ItemReactiveRestController {

    @Autowired
    ItemReactiveCrudRestRepository itemReactiveCrudRestRepository;

    @Autowired
    ItemRepresentationModelAssembler itemRepresentationModelAssembler;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Item item) {
        Mono<Item> result = this.itemReactiveCrudRestRepository.save(item);
        if (result.block() == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        EntityModel<Item> entityModelOfItem = this.itemRepresentationModelAssembler.toModel(
                result.block()
        );
        return new ResponseEntity<>(entityModelOfItem, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> read(@PathVariable String id) {
        Mono<Item> result = this.itemReactiveCrudRestRepository.findById(id);
        if (result.block() == null || result == Mono.empty().block()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EntityModel<Item> entityModelOfItem = this.itemRepresentationModelAssembler.toModel(
                result.block()
        );
        return new ResponseEntity<>(entityModelOfItem, HttpStatus.OK);
    }

    @GetMapping(path = "/code/{code}")
    public ResponseEntity<?> readByCode(@PathVariable String code) {
        Mono<Item> result = this.itemReactiveCrudRestRepository.findByCode(code);
        if (result.block() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EntityModel<Item> entityModelOfItem = this.itemRepresentationModelAssembler.toModel(
                result.block()
        );
        return new ResponseEntity<>(entityModelOfItem, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        Flux<Item> results = this.itemReactiveCrudRestRepository.findAll();
        if (results.count().block() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CollectionModel<EntityModel<Item>> collectionModelOfItems = this.itemRepresentationModelAssembler
                .toCollectionModel(
                        results.toIterable()
                );
        return new ResponseEntity<>(collectionModelOfItems, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> putUpdate(@RequestBody Item item, @PathVariable String id) {
        Mono<Item> updated = this.itemReactiveCrudRestRepository.findById(id).map(
                updatable -> {
                    updatable.setCode(item.getCode());
                    updatable.setName(item.getName());
                    updatable.setDescription(item.getDescription());
                    updatable.setPrice(item.getPrice());
                    this.itemReactiveCrudRestRepository.save(updatable);
                    return updatable;
                }
        );
        if (updated.block() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            EntityModel<Item> entityModelOfItem = this.itemRepresentationModelAssembler
                    .toModel(updated.block());
            return new ResponseEntity<>(entityModelOfItem, HttpStatus.OK);
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> patchUpdate(@RequestBody Item item, @PathVariable String id) {
        Mono<Item> updated = this.itemReactiveCrudRestRepository.findById(id).map(
                updatable -> {
                    if (item.getCode() != null) updatable.setCode(item.getCode());
                    if (item.getName() != null) updatable.setName(item.getName());
                    if (item.getDescription() != null) updatable.setDescription(item.getDescription());
                    if (item.getPrice() != null) updatable.setPrice(item.getPrice());
                    this.itemReactiveCrudRestRepository.save(updatable);
                    return updatable;
                }
        );
        if (updated.block() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            EntityModel<Item> entityModelOfItem = this.itemRepresentationModelAssembler
                    .toModel(updated.block());
            return new ResponseEntity<>(entityModelOfItem, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Mono<Item> result = this.itemReactiveCrudRestRepository.findById(id);
        if (result.block() == null || result == Mono.empty().block()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.itemReactiveCrudRestRepository.deleteById(id).subscribe();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
