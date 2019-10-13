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

package local.example.seed.assembler;

import local.example.seed.controller.SeedRestController;
import local.example.seed.model.Seed;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SeedRepresentationModelAssembler 
        implements RepresentationModelAssembler<Seed, EntityModel<Seed>>{

    @Override
    public EntityModel<Seed> toModel(Seed seed) {
        return new EntityModel<>(seed, 
                linkTo(methodOn(SeedRestController.class).read(seed.getId())).withSelfRel(),
                linkTo(methodOn(SeedRestController.class).readAll()).withRel("seeds")
        );
    }

    @Override
    public CollectionModel toCollectionModel(Iterable seeds) {
        return RepresentationModelAssembler.super.toCollectionModel(seeds);
    }
}
