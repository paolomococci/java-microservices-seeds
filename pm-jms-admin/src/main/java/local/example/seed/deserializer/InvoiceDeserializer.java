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

package local.example.seed.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import local.example.seed.model.Invoice;
import local.example.seed.model.util.Link;
import local.example.seed.model.util.Self;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvoiceDeserializer
        extends JsonDeserializer<Invoice> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Invoice deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);
        final String code = jsonNode.get("code").asText();
        final LocalDate date = LocalDate.parse(jsonNode.get("date").asText(), this.dateTimeFormatter);
        final Double total = jsonNode.get("total").asDouble();
        final String customerId = jsonNode.get("customerId").asText();
        final Link link = new Link(new Self(jsonNode.get("_links").asText()));

        return new Invoice(code, date, total, customerId, link);
    }
}
