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

import java.net.URISyntaxException;
import local.example.seed.exception.SeedNotFoundException;
import local.example.seed.model.Seed;
import local.example.seed.repository.SeedRepository;
import local.example.seed.assembler.SeedRepresentationModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/seeds", produces = "application/hal+json")
public class SeedRestController {

    @Autowired
    SeedRepository seedRepository;

    @Autowired
    SeedRepresentationModelAssembler seedRepresentationModelAssembler;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Seed seed) {
        EntityModel<Seed> entityModelOfSeed = seedRepresentationModelAssembler
                .toModel(seedRepository.save(seed));
        return new ResponseEntity<>(entityModelOfSeed, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Seed seed = seedRepository.findById(id).orElseThrow(
                () -> new SeedNotFoundException(id));
        EntityModel<Seed> entityModelOfSeed = seedRepresentationModelAssembler
                .toModel(seed);
        return new ResponseEntity<>(entityModelOfSeed, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        Iterable<Seed> seeds = seedRepository.findAll();
        CollectionModel<EntityModel<Seed>> collectionModelOfSeeds;
        collectionModelOfSeeds = seedRepresentationModelAssembler
                .toCollectionModel(seeds);
        return new ResponseEntity<>(collectionModelOfSeeds, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@RequestBody Seed updated, @PathVariable Long id)
            throws URISyntaxException {
        Seed temp = seedRepository.findById(id)
                .map(seed -> {
                    seed.setName(updated.getName());
                    seed.setPercentage(updated.getPercentage());
                    return seedRepository.save(seed);
                })
                .orElseGet(() -> {
                    updated.setId(id);
                    return seedRepository.save(updated);
                });
        EntityModel<Seed> entityModelOfSeed = seedRepresentationModelAssembler
                .toModel(temp);
        return new ResponseEntity<>(entityModelOfSeed, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}/name")
    public ResponseEntity<?> nameUpdate(@RequestBody Seed updated, @PathVariable Long id) {
        Seed temp = seedRepository.findById(id)
                .orElseGet(() -> {
                    updated.setId(id);
                    return seedRepository.save(updated);
                });
        if (updated.getName() != null) {
            temp.setName(updated.getName());
            seedRepository.save(temp);
        }
        EntityModel<Seed> entityModelOfSeed = seedRepresentationModelAssembler
                .toModel(temp);
        return new ResponseEntity<>(entityModelOfSeed, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}/percentage")
    public ResponseEntity<?> percentageUpdate(@RequestBody Seed updated, @PathVariable Long id) {
        Seed temp = seedRepository.findById(id)
                .orElseGet(() -> {
                    updated.setId(id);
                    return seedRepository.save(updated);
                });
        if (Double.isFinite(updated.getPercentage())) {
            temp.setPercentage(updated.getPercentage());
            seedRepository.save(temp);
        }
        EntityModel<Seed> entityModelOfSeed = seedRepresentationModelAssembler
                .toModel(temp);
        return new ResponseEntity<>(entityModelOfSeed, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        seedRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
