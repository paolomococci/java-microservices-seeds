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

package local.example.seed.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ItemRestRepositoryParametrizedTests {

    @Autowired
    private MockMvc mockMvc;

    private static String ITEM_TEST_STRING =
            "{\"code\":\"00112233\",\"name\":\"\",\"description\":\"\",\"price\":\"\"}";
    private static URI uri;

    @Disabled
    @Test
    @Order(1)
    void createTest() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(post("/items").content(ITEM_TEST_STRING))
                .andExpect(status().isCreated())
                .andReturn();
        this.setUri(new URI(mvcResult.getResponse().getHeader("Location")));
    }

    @Disabled
    @Order(2)
    @ParameterizedTest
    @MethodSource("initUri")
    void readTest() throws Exception {
        this.mockMvc.perform(get(this.getUri()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00112233"));
    }

    @Disabled
    @Order(3)
    @ParameterizedTest
    @MethodSource("initUri")
    void readAllTest() throws Exception {
        this.mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").exists());
    }

    @Disabled
    @Order(4)
    @ParameterizedTest
    @MethodSource("initUri")
    void updateTest() throws Exception {
        this.mockMvc.perform(put(this.getUri())
                .content("{\"code\":\"00998877\",\"name\":\"\",\"description\":\"\",\"price\":\"\"}"))
                .andExpect(status().isNoContent());
        this.mockMvc.perform(get(this.getUri()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00998877"));
    }

    @Disabled
    @Order(5)
    @ParameterizedTest
    @MethodSource("initUri")
    void partialUpdateTest() throws Exception {
        this.mockMvc.perform(patch(this.getUri())
                .content("{\"code\":\"00554466\"}"))
                .andExpect(status().isNoContent());
        this.mockMvc.perform(get(this.getUri()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00554466"));
    }

    @Disabled
    @Order(6)
    @ParameterizedTest
    @MethodSource("initUri")
    void deleteTest() throws Exception {
        this.mockMvc.perform(delete(this.getUri()))
                .andExpect(status().isNoContent());
    }

    private static void setUri(URI uri) {
        ItemRestRepositoryParametrizedTests.uri = uri;
    }

    private static URI getUri() {
        return uri;
    }

    private static Stream<String> initUri() {
        return Stream.of(
                ItemRestRepositoryParametrizedTests.getUri().getPath()
        );
    }
}
