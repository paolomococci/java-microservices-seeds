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

import local.example.seed.model.Item;
import org.springframework.web.reactive.function.client.WebClient;

public class ItemRestfulReactiveController {

    private static final String ITEMS_URI = "http://127.0.0.1:8081/items";
    private final WebClient webClient;

    public ItemRestfulReactiveController() {
        this.webClient = WebClient.builder().build();
    }

    public void create(Item item) {
        // TODO
    }

    public void read(String id) {
        // TODO
    }

    public void readAll() {
        // TODO
    }

    public void update(Item item, String id) {
        // TODO
    }

    public void partialUpdate(Item item, String id) {
        // TODO
    }

    public void delete(String id) {
        this.webClient.mutate().baseUrl(ITEMS_URI+"/"+id).build().delete();
    }
}
