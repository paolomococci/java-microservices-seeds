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
import local.example.seed.service.ItemReactiveMongoDBService;
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
@RequestMapping(value = "/api/reactive/items", produces = "application/hal+json")
public class ItemReactiveRestController { // TODO: totally to be reviewed

    @Autowired
    ItemReactiveCrudRestRepository itemReactiveCrudRestRepository;

    @Autowired
    ItemReactiveMongoDBService itemReactiveMongoDBService;

    @Autowired
    ItemRepresentationModelAssembler itemRepresentationModelAssembler;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Item item) {
        Mono<Item> result = this.itemReactiveCrudRestRepository.save(item);
        if (result != null) {
            EntityModel<Item> entityModelOfItem = this.itemRepresentationModelAssembler.toModel(
                    Objects.requireNonNull(result.block())
            );
            return new ResponseEntity<>(entityModelOfItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> read(@PathVariable String id) {
        Mono<Item> result = this.itemReactiveCrudRestRepository.findById(id);
        if (result != null) {
            EntityModel<Item> entityModelOfItem = this.itemRepresentationModelAssembler.toModel(
                    Objects.requireNonNull(result.block())
            );
            return new ResponseEntity<>(entityModelOfItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/code/{code}")
    public ResponseEntity<?> readByCode(@PathVariable String code) {
        Mono<Item> result = this.itemReactiveCrudRestRepository.findByCode(code);
        if (result != null) {
            EntityModel<Item> entityModelOfItem = this.itemRepresentationModelAssembler.toModel(
                    Objects.requireNonNull(result.block())
            );
            return new ResponseEntity<>(entityModelOfItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        Flux<Item> results = this.itemReactiveCrudRestRepository.findAll();
        if (results != null) {
            CollectionModel<EntityModel<Item>> collectionModelOfItems = this.itemRepresentationModelAssembler
                    .toCollectionModel((Iterable<? extends Item>) results);
            return new ResponseEntity<>(collectionModelOfItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> putUpdate(@RequestBody Item updated, @PathVariable String id) {
        // TODO
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> patchUpdate(@RequestBody Item item, @PathVariable String id) {
        // TODO
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Mono<Item> result = this.itemReactiveCrudRestRepository.findById(id);
        if (result != null) {
            this.itemReactiveCrudRestRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
