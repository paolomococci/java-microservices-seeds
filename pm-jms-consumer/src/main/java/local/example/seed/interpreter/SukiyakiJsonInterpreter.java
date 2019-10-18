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
import com.jayway.jsonpath.ReadContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import local.example.seed.model.Seeds;

/**
 * 
 * This class use json-path
 */
public class SukiyakiJsonInterpreter {
    
    public List<String> retrieveListOfIds(String dataString) {
        ReadContext readContext = JsonPath.parse(dataString);
        List<String> seedIdList = readContext.read("$._embedded.seeds[*].id");
        return seedIdList;
    }
    
    public List<String> retrieveListOfNames(String dataString) {
        ReadContext readContext = JsonPath.parse(dataString);
        List<String> seedNameList = readContext.read("$._embedded.seeds[*].name");
        return seedNameList;
    }
    
    public List<String> retrieveListOfPercentages(String dataString) {
        ReadContext readContext = JsonPath.parse(dataString);
        List<String> seedPercentageList = readContext.read("$._embedded.seeds[*].percentage");
        return seedPercentageList;
    }
    
    public List<String> retrieveListOfCreateDates(String dataString) {
        ReadContext readContext = JsonPath.parse(dataString);
        List<String> seedCreatedList = readContext.read("$._embedded.seeds[*].created");
        return seedCreatedList;
    }
    
    public List<String> deserialize(String dataString) {
        ReadContext readContext = JsonPath.parse(dataString);
        return readContext.read("$._embedded.seeds[*]");
    }
    
    public static List<Map<String, Object>> map(String dataString) {
        return JsonPath.parse(dataString).read("$._embedded.seeds[*]");
    }
    
    public static Seeds toSeeds(String dataString) {
        ReadContext readContext = JsonPath.parse(dataString);
        Seeds seeds = readContext.read("$._embedded.seeds[*]", Seeds.class);
        System.out.println("at " + LocalDateTime.now() + " seeds is: " + seeds);
        return seeds;
    }
}
