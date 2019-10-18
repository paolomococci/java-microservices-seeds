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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
import lombok.Getter;

@Getter
public class DataRetriever {
    
    private String result = "";
    
    public void retriever(String resource) 
            throws MalformedURLException, 
            ProtocolException, 
            IOException {
        try {
            URL url = new URL(resource);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                try (Scanner scanner = new Scanner(url.openStream())) {
                    while (scanner.hasNext()) {
                        result+= scanner.next();
                    }
                    scanner.close();
                }
            } else {
                throw new RuntimeException("http response code: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException | RuntimeException e) {
            e.getMessage();
        }
    }
}
