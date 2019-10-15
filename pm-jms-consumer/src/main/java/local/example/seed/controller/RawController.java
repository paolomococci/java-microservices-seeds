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
import local.example.seed.util.DataRetriever;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RawController {
    
    private String urlResources = "http://127.0.0.1:9090/api/";
    
    @GetMapping(path = "/raw")
    public String root(
            @RequestParam(
                    name="name", 
                    required=false, 
                    defaultValue = "seeds"
            ) String name, Model model) 
            throws ProtocolException,
            IOException {
        urlResources+= name;
        model.addAttribute("name", name);
        model.addAttribute("result", this.retriever());
        return "raw";
    }
    
    private String retriever() 
            throws MalformedURLException, 
            ProtocolException, 
            IOException {
        DataRetriever dataRetriever = new DataRetriever();
        dataRetriever.retriever(urlResources);
        return  dataRetriever.getResult();
    }
}
