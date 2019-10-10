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
import java.util.List;
import java.util.Optional;
import local.example.seed.model.Seed;
import local.example.seed.repository.SeedRepository;
import local.example.seed.resource.SeedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/seeds", produces = "application/hal+json")
public class SeedRestController {
    
    @Autowired
    SeedRepository seedRepository;
    
    @PostMapping
    ResponseEntity<EntityModel<Seed>> create() 
            throws URISyntaxException {
        EntityModel<Seed> seedEntityModel;
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @GetMapping(path = "/{id}")
    public EntityModel<Seed> read(@PathVariable Long id) {
        Optional<Seed> seed = seedRepository.findById(id);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @GetMapping
    public ResponseEntity<CollectionModel<SeedModel>> readAll() {
        final List<EntityModel<Seed>> seedResources;
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<Seed> update(@RequestBody Seed update, @PathVariable Long id) 
            throws URISyntaxException {
        Optional<Seed> seed = seedRepository.findById(id);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @PatchMapping(path = "/{id}")
    public ResponseEntity<Seed> partialUpdate(@RequestBody Seed update, @PathVariable Long id) 
            throws URISyntaxException {
        Optional<Seed> seed = seedRepository.findById(id);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Seed> delete(@PathVariable Long id) {
        if (id != null) seedRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
