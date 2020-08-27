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

import local.example.seed.model.Item;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;

public class ItemRestfulController {

    private static final String ITEMS_URI = "http://127.0.0.1:8080/items";
    private final RestTemplate restTemplate;

    public ItemRestfulController() {
        this.restTemplate = new RestTemplate();
    }

    public void create(Item item) {
        // TODO
    }

    public Item read(String id)
            throws URISyntaxException {
        ResponseEntity<Item> responseEntity = this.restTemplate
                .exchange(ITEMS_URI +"/"+id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public List<Item> readAll()
            throws URISyntaxException {
        ResponseEntity<List<Item>> responseEntity = this.restTemplate
                .exchange(ITEMS_URI,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public void update(Item item, String id) {
        // TODO
    }

    public void partialUpdate(Item item, String id) {
        // TODO
    }

    public void delete(String id) {
        // TODO
    }
}