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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.time.LocalDateTime;
import local.example.seed.model.Seed;
import local.example.seed.util.SachimiJsonInterpreter;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SashimiController {
    
    private String urlResources = "http://127.0.0.1:9090/api/";
    private final JSONParser jsonParser = new JSONParser();
    
    
    @GetMapping(path = "/sashimi")
    public String root(
            @RequestParam(
                    name="name", 
                    required=false, 
                    defaultValue = "seeds"
            ) String name, Model model) 
            throws ProtocolException,
            IOException,
            MalformedURLException,
            ParseException {
        urlResources+= name;
        model.addAttribute("name", name);
        model.addAttribute("result", this.retriever());
        return "sashimi";
    }
    
    private Seed retriever() 
            throws MalformedURLException, 
            ProtocolException, 
            IOException,
            ParseException {
        SachimiJsonInterpreter sachimiJsonInterpreter = new SachimiJsonInterpreter();
        var jsonObject = sachimiJsonInterpreter.serialize(new Seed(1,"David",0.3,LocalDateTime.now()));
        return sachimiJsonInterpreter.deserialize(jsonObject);
    }
}
