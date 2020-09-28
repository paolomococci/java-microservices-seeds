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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CustomerRestRepositoryParametrizedTests {

    @Autowired
    private MockMvc mockMvc;

    private static String CUSTOMER_TEST_STRING =
            "{\"name\":\"John\",\"surname\":\"Jumper\",\"email\":\"johnjumper@example.local\"}";
    private static URI uri;

    @Disabled
    @Test
    @Order(1)
    void createTest() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(post("/customers").content(CUSTOMER_TEST_STRING))
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
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Jumper"))
                .andExpect(jsonPath("$.email").value("johnjumper@example.local"));
    }

    @Disabled
    @Order(3)
    @ParameterizedTest
    @MethodSource("initUri")
    void readAllTest() throws Exception {
        this.mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").exists());
    }

    @Disabled
    @Order(4)
    @ParameterizedTest
    @MethodSource("initUri")
    void updateTest() throws Exception {
        // TODO
    }

    @Disabled
    @Order(5)
    @ParameterizedTest
    @MethodSource("initUri")
    void partialUpdateTest() throws Exception {
        // TODO
    }

    @Disabled
    @Order(6)
    @ParameterizedTest
    @MethodSource("initUri")
    void deleteTest() throws Exception {
        // TODO
    }

    private static void setUri(URI uri) {
        CustomerRestRepositoryParametrizedTests.uri = uri;
    }

    private static URI getUri() {
        return uri;
    }

    private static Stream<String> initUri() {
        return Stream.of(
                CustomerRestRepositoryParametrizedTests.getUri().getPath()
        );
    }
}
