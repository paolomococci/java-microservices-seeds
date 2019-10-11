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
    public EntityModel<Seed> create(@RequestBody Seed seed) 
            throws URISyntaxException {
        EntityModel<Seed> entityModelOfSeed = seedRepresentationModelAssembler
                .toModel(seedRepository.save(seed));
        return entityModelOfSeed;
    }
    
    @GetMapping(path = "/{id}")
    public EntityModel<Seed> read(@PathVariable Long id) {
        Seed seed = seedRepository.findById(id).orElseThrow(
                () -> new SeedNotFoundException(id));
        EntityModel<Seed> entityModelOfSeed = seedRepresentationModelAssembler
                .toModel(seed);
        return entityModelOfSeed;
    }
    
    @GetMapping
    public CollectionModel<EntityModel<Seed>> readAll() {
        Iterable<Seed> seeds = seedRepository.findAll();
        CollectionModel<EntityModel<Seed>> collectionModelOfSeeds;
        collectionModelOfSeeds = seedRepresentationModelAssembler
                .toCollectionModel(seeds);
        return collectionModelOfSeeds;
    }
    
    @PutMapping(path = "/{id}")
    public EntityModel<Seed> update(@RequestBody Seed updated, @PathVariable Long id) 
            throws URISyntaxException {
        Seed temp = seedRepository.findById(id)
                .map(seed -> {
                    seed.setName(updated.getName());
                    seed.setSeedDoubleValue(updated.getSeedDoubleValue());
                    seed.setCreated(updated.getCreated());
                    return seedRepository.save(seed);
                })
                .orElseGet(() -> {
                    updated.setId(id);
                    return seedRepository.save(updated);
                });
        EntityModel<Seed> entityModelOfSeed = seedRepresentationModelAssembler
                .toModel(temp);
        return entityModelOfSeed;
    }
    
    @PatchMapping(path = "/{id}")
    public EntityModel<Seed> partialUpdate(@RequestBody Seed updated, @PathVariable Long id) 
            throws URISyntaxException {
        Seed temp = seedRepository.findById(id)
                .map(seed -> {
                    if (updated.getName() != null) seed
                            .setName(updated.getName());
                    if (this.isValidDoubleValue(
                            String.valueOf(updated.getSeedDoubleValue()))) {
                        seed.setSeedDoubleValue(updated.getSeedDoubleValue());
                    }
                    if (updated.getCreated() != null) seed
                            .setCreated(updated.getCreated());
                    return seedRepository.save(seed);
                })
                .orElseGet(() -> {
                    updated.setId(id);
                    return seedRepository.save(updated);
                });
        EntityModel<Seed> entityModelOfSeed = seedRepresentationModelAssembler
                .toModel(temp);
        return entityModelOfSeed;
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) 
            throws URISyntaxException {
        seedRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    private boolean isValidDoubleValue(String string) {
        return Double.isNaN(Double.parseDouble(string));
    }
}
