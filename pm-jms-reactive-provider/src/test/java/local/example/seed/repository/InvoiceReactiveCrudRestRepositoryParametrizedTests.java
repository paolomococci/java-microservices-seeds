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

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.util.Objects;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InvoiceReactiveCrudRestRepositoryParametrizedTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String INVOICE_TEST_STRING =
            "{\"code\":\"00123456\",\"date\":\"\",\"total\":\"\"}";
    private static URI uri;

    @Test
    @Order(1)
    void createTest() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(post("/api/reactive/invoices").content(INVOICE_TEST_STRING))
                .andExpect(status().isCreated())
                .andReturn();
        setUri(new URI(
                Objects.requireNonNull(mvcResult.getResponse().getHeader("Location"))
        ));
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("initUri")
    void readTest() throws Exception {
        this.mockMvc.perform(get(getUri()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00123456"));
    }

    @Order(3)
    @ParameterizedTest
    @MethodSource("initUri")
    void readAllTest() throws Exception {
        this.mockMvc.perform(get("/api/reactive/invoices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").exists());
    }

    @Order(4)
    @ParameterizedTest
    @MethodSource("initUri")
    void updateTest() throws Exception {
        this.mockMvc.perform(put(getUri())
                .content("{\"code\":\"00987654\",\"date\":\"\",\"total\":\"\"}"))
                .andExpect(status().isNoContent());
        this.mockMvc.perform(get(getUri()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00987654"));
    }

    @Order(5)
    @ParameterizedTest
    @MethodSource("initUri")
    void partialUpdateTest() throws Exception {
        this.mockMvc.perform(patch(getUri())
                .content("{\"code\":\"00541236\"}"))
                .andExpect(status().isNoContent());
        this.mockMvc.perform(get(getUri()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00541236"));
    }

    @Order(6)
    @ParameterizedTest
    @MethodSource("initUri")
    void deleteTest() throws Exception {
        this.mockMvc.perform(delete(getUri()))
                .andExpect(status().isNoContent());
    }

    @Order(7)
    @ParameterizedTest
    @MethodSource("initUri")
    void notFoundTest() throws Exception {
        this.mockMvc.perform(get(getUri()))
                .andExpect(status().isNotFound());
    }

    private static void setUri(URI uri) {
        InvoiceReactiveCrudRestRepositoryParametrizedTests.uri = uri;
    }

    private static URI getUri() {
        return uri;
    }

    private static Stream<String> initUri() {
        return Stream.of(
                InvoiceReactiveCrudRestRepositoryParametrizedTests.getUri().getPath()
        );
    }
}
