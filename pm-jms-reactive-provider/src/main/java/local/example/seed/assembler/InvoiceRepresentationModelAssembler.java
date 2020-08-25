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

import local.example.seed.controller.InvoiceReactiveRestController;
import local.example.seed.document.Invoice;
import lombok.SneakyThrows;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InvoiceRepresentationModelAssembler
        implements RepresentationModelAssembler<Invoice, EntityModel<Invoice>> {

    @Override
    @SneakyThrows
    public EntityModel<Invoice> toModel(Invoice invoice) {
        return EntityModel.of(invoice,
                linkTo(methodOn(InvoiceReactiveRestController.class).read(invoice.getId())).withSelfRel(),
                linkTo(methodOn(InvoiceReactiveRestController.class).readAll()).withRel("invoices"));
    }

    @Override
    public CollectionModel<EntityModel<Invoice>>
            toCollectionModel(Iterable<? extends Invoice> invoices) {
        return RepresentationModelAssembler.super.toCollectionModel(invoices);
    }
}
