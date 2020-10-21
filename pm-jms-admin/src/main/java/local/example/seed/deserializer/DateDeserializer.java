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
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateDeserializer
        extends StdDeserializer<Date> {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    protected DateDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public Date deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException {
        String date = jsonParser.getText();
        try {
            return (Date) this.simpleDateFormat.parse(date);
        } catch (ParseException parseException) {
            throw new RuntimeException(parseException);
        }
    }
}
