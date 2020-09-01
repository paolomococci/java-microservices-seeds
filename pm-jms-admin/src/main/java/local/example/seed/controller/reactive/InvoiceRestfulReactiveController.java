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

package local.example.seed.controller.reactive;

import local.example.seed.model.Invoice;
import org.springframework.web.reactive.function.client.WebClient;

public class InvoiceRestfulReactiveController {

    private static final String INVOICES_URI = "http://127.0.0.1:8081/invoices";
    private final WebClient webClient;

    public InvoiceRestfulReactiveController() {
        this.webClient = WebClient.create();
    }

    public void create(Invoice invoice) {
        // TODO
    }

    public Invoice read(String id) {
        // TODO
        return null;
    }

    public void readAll() {
        // TODO
    }

    public void update(Invoice invoice, String id) {
        // TODO
    }

    public void partialUpdate(Invoice invoice, String id) {
        // TODO
    }

    public void delete(String id) {
        // TODO
    }
}
