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

package local.example.seed.controller;

import local.example.seed.interpreter.SukiyakiJsonInterpreter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SukiyakiController {
    
    private String urlResources = "http://127.0.0.1:9090/api/";
    
    @GetMapping(path = "/sukiyaki")
    public String sukiyaki(
            @RequestParam(
                    name="name", 
                    required=false, 
                    defaultValue = "seeds"
            ) String name, Model model) {
        urlResources+= name;
        model.addAttribute("name", name);
        model.addAttribute("urlResources", urlResources);
        model.addAttribute("seeds", SukiyakiJsonInterpreter.deserialize(DATA_EXAMPLE));
        return "sukiyaki";
    }

    /* data strings for off-line verify */
    //private final String DATA_EXAMPLE = "{'_embedded':{'seeds':[{'id':1,'name':'seedOne','percentage':0.14,'created':'2019-10-16T17:40:45.381287','_links':{'self':{'href':'http://127.0.0.1:9090/api/seeds/1'},'seeds':{'href':'http://127.0.0.1:9090/api/seeds'}}},{'id':2,'name':'seedTwo','percentage':0.23,'created':'2019-10-16T17:41:17.718917','_links':{'self':{'href':'http://127.0.0.1:9090/api/seeds/2'},'seeds':{'href':'http://127.0.0.1:9090/api/seeds'}}},{'id':3,'name':'seedThree','percentage':0.42,'created':'2019-10-16T17:42:34.500347','_links':{'self':{'href':'http://127.0.0.1:9090/api/seeds/3'},'seeds':{'href':'http://127.0.0.1:9090/api/seeds'}}},{'id':4,'name':'seedFour','percentage':0.31,'created':'2019-10-16T17:43:05.586839','_links':{'self':{'href':'http://127.0.0.1:9090/api/seeds/4'},'seeds':{'href':'http://127.0.0.1:9090/api/seeds'}}},{'id':5,'name':'seedFive','percentage':0.58,'created':'2019-10-16T17:43:27.943723','_links':{'self':{'href':'http://127.0.0.1:9090/api/seeds/5'},'seeds':{'href':'http://127.0.0.1:9090/api/seeds'}}}]}}";
    private final String DATA_EXAMPLE = "{}";
}
