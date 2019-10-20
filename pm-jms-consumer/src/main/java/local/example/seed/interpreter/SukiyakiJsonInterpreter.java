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

package local.example.seed.interpreter;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.ReadContext;
import java.time.LocalDateTime;
import java.util.List;
import local.example.seed.model.Seed;
import local.example.seed.model.Seeds;

/**
 * 
 * This class use json-path
 */
public class SukiyakiJsonInterpreter {
    
    public static Seeds deserialize(String dataString) {
        try {
            Seeds seeds = new Seeds();
            String jsonDataString = dataString;
            String jsonPathExpression = "$._embedded.seeds[*]";
            JsonPath jsonPath = JsonPath.compile(jsonPathExpression);
            List<Seed> seedList = jsonPath.read(jsonDataString);
            int sizeOfSeedList = seedList.size();
            for (int i = 0; i < sizeOfSeedList; i++) {
                Seed s = new Seed();
                ReadContext rc = JsonPath.parse(jsonDataString);
                s.setId(Long.parseLong(rc.read("$._embedded.seeds[" + i + "].id").toString()));
                s.setName(rc.read("$._embedded.seeds[" + i + "].name"));
                s.setPercentage(Double.parseDouble(rc.read("$._embedded.seeds[" + i + "].percentage").toString()));
                s.setCreated(LocalDateTime.parse(rc.read("$._embedded.seeds[" + i + "].created").toString()));
                seeds.add(s);
            }
            return seeds;
        } catch (PathNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
