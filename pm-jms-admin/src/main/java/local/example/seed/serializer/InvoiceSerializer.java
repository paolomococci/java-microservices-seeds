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

package local.example.seed.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import local.example.seed.model.Invoice;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class InvoiceSerializer
        extends StdSerializer<Invoice> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    protected InvoiceSerializer(Class<Invoice> t) {
        super(t);
    }

    @Override
    public void serialize(
            Invoice invoice,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider
    ) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("code", invoice.getCode());
        jsonGenerator.writeStringField("date", invoice.getDate().format(this.dateTimeFormatter));
        jsonGenerator.writeNumberField("total", invoice.getTotal());
        jsonGenerator.writeStringField("customerId", invoice.getCustomerId());
        jsonGenerator.writeStringField("_links", invoice.get_links().getSelf().getHref());
        jsonGenerator.writeEndObject();
    }
}
