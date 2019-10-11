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

import com.google.common.collect.Lists;
import java.net.URISyntaxException;
import java.util.List;
import local.example.seed.exception.SeedNotFoundException;
import local.example.seed.model.Seed;
import local.example.seed.repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    Seed create(@RequestBody Seed seed) 
            throws URISyntaxException {
        return seedRepository.save(seed);
    }
    
    @GetMapping(path = "/{id}")
    public Seed read(@PathVariable Long id) {
        Seed seed = seedRepository.findById(id).orElseThrow(
                () -> new SeedNotFoundException(id));
        return seed;
    }
    
    @GetMapping
    public List<Seed> readAll() {
        List<Seed> seeds = Lists.newArrayList(seedRepository.findAll());
        return seeds;
    }
    
    @PutMapping(path = "/{id}")
    public Seed update(@RequestBody Seed updated, @PathVariable Long id) 
            throws URISyntaxException {
        return seedRepository.findById(id)
                .map(seed -> {
                    seed.setName(updated.getName());
                    seed.setSeedDoubleValue(updated.getSeedDoubleValue());
                    return seedRepository.save(seed);
                })
                .orElseGet(() -> {
                    updated.setId(id);
                    return seedRepository.save(updated);
                });
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Seed> delete(@PathVariable Long id) {
        if (id != null) seedRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
