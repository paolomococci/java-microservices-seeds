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

import local.example.seed.controller.ItemReactiveRestController;
import local.example.seed.document.Item;
import lombok.SneakyThrows;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemRepresentationModelAssembler
        implements RepresentationModelAssembler<Item, EntityModel<Item>> {

    @Override
    @SneakyThrows
    public EntityModel<Item> toModel(Item item) {
        return EntityModel.of(item,
                linkTo(methodOn(ItemReactiveRestController.class).read(item.getId())).withSelfRel(),
                linkTo(methodOn(ItemReactiveRestController.class).readAll()).withRel("items"));
    }

    @Override
    public CollectionModel<EntityModel<Item>>
            toCollectionModel(Iterable<? extends Item> items) {
        return RepresentationModelAssembler.super.toCollectionModel(items);
    }
}
