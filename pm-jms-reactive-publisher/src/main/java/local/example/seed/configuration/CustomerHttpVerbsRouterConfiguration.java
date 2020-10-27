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

package local.example.seed.configuration;

import local.example.seed.handler.CustomerReactiveHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class CustomerHttpVerbsRouterConfiguration {

    private final MediaType mediaType = MediaTypes.HAL_JSON;

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction(CustomerReactiveHandler customerReactiveHandler) {
        return RouterFunctions
                .route(POST("/customers").and(accept(this.mediaType)), customerReactiveHandler::create)
                .andRoute(GET("/customers/{id}").and(accept(this.mediaType)), customerReactiveHandler::read)
                .andRoute(GET("/customers").and(accept(this.mediaType)), customerReactiveHandler::readAll)
                .andRoute(PUT("/customers").and(accept(this.mediaType)), customerReactiveHandler::update)
                .andRoute(DELETE("customers/{id}").and(accept(this.mediaType)), customerReactiveHandler::delete);
    }
}
