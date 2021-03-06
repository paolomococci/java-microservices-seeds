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

import local.example.seed.embedded.ItemEmbedded;
import local.example.seed.model.Item;
import local.example.seed.response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class ItemRestfulController {

    @Autowired
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String ITEM_RESTFUL_BASE_URI = "http://127.0.0.1:8081/items";

    public void create(Item item)
            throws RestClientException {
        this.restTemplate.postForObject(
                ITEM_RESTFUL_BASE_URI,
                item,
                Item.class
        );
    }

    public Item read(String uri)
            throws RestClientException {
        return this.restTemplate.getForObject(
                uri,
                Item.class
        );
    }

    public Collection<Item> readAll()
            throws RestClientException {
        Collection<Item> items = new ArrayList<>();
        ResponseEntity<ItemResponse> responseEntity = this.restTemplate.getForEntity(
                ITEM_RESTFUL_BASE_URI,
                ItemResponse.class
        );
        ItemEmbedded embedded = Objects.requireNonNull(responseEntity.getBody()).get_embedded();
        items.addAll(embedded.getItems());
        return items;
    }

    public Item findByCode(String code)
            throws RestClientException {
        Map<String, String> param = new HashMap<>();
        param.put("code", code);
        // TODO
        return new Item();
    }

    public void update(Item item, String uri)
            throws RestClientException {
        this.restTemplate.put(uri, item);
    }

    public void partialUpdate(Item item, String uri)
            throws RestClientException {
        this.restTemplate.patchForObject(uri, item, Item.class);
    }

    public void delete(String uri)
            throws RestClientException {
        this.restTemplate.delete(uri);
    }
}
