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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Stream;

public class ItemRestfulController {

    private static final String ITEM_RESTFUL_BASE_URI = "http://127.0.0.1:8080/items";

    private final RestTemplate restTemplate;

    public ItemRestfulController() {
        this.restTemplate = new RestTemplate();
    }

    public void create(Item item)
            throws RestClientException {
        this.restTemplate.postForObject(
                ITEM_RESTFUL_BASE_URI,
                item,
                Item.class
        );
    }

    public Item read(String id)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        return this.restTemplate.getForObject(
                ITEM_RESTFUL_BASE_URI,
                Item.class,
                param
        );
    }

    public List<Item> readAll()
            throws RestClientException {
        ResponseEntity<List<Item>> responseEntity = this.restTemplate
                .exchange(ITEM_RESTFUL_BASE_URI,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});
        return responseEntity.getBody();
    }

    public Collection<Item> collectionOfAllItems() {
        List<Item> items = this.readAll();
        Collection<Item> collectionOfItems = new ArrayList<>();
        for (Item item:items) {
            collectionOfItems.add(item);
        }
        return collectionOfItems;
    }

    public Stream<Item> streamOfAllItems() {
        List<Item> items = this.readAll();
        // TODO
        return null;
    }

    public Item findByCode(String code)
            throws RestClientException {
        // TODO
        return null;
    }

    public void update(Item item, String id)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        this.restTemplate.put(
                ITEM_RESTFUL_BASE_URI,
                item,
                param
        );
    }

    public void partialUpdate(Item item, String id)
            throws RestClientException {
        // TODO
    }

    public void delete(String id)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        this.restTemplate.delete(
                ITEM_RESTFUL_BASE_URI,
                param
        );
    }
}
