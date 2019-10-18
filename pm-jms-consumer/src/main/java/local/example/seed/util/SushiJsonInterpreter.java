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

import com.google.gson.Gson;
import local.example.seed.model.Seed;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * This class use com.google.code.gson gson
 */
@Getter
public class SushiJsonInterpreter {
    
    @Autowired
    private final Gson gson = new Gson();
    
    public String serialize(Seed temp) {
        return gson.toJson(temp);
    }
    
    public Seed deserialize(String temp) {
        return gson.fromJson(temp, Seed.class);
    }
}
