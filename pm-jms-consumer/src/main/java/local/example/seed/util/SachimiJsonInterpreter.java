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

package local.example.seed.util;

import java.time.LocalDateTime;
import local.example.seed.model.Seed;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**
 * 
 * This class use json-simple
 */
public class SachimiJsonInterpreter {
    
    public JSONObject serialize(Seed seed) {
        var jsonObject = new JSONObject();
        jsonObject.put("id", seed.getId());
        jsonObject.put("name", seed.getName());
        jsonObject.put("percentage", seed.getPercentage());
        jsonObject.put("created", seed.getCreated());
        return jsonObject;
    }
    
    public Seed deserialize(JSONObject jsonObject) 
            throws ParseException {
        Seed seed = new Seed();
        seed.setId(Long.parseLong(jsonObject.get("id").toString()));
        seed.setName(jsonObject.get("name").toString());
        seed.setPercentage(Double.parseDouble(jsonObject.get("percentage").toString()));
        seed.setCreated(LocalDateTime.parse(jsonObject.get("created").toString()));
        return seed;
    }
}
