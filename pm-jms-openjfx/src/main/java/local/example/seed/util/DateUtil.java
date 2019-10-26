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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    
    private static final DateTimeFormatter 
            DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    
    public static String format(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }
    
    public static LocalDateTime parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, (temporal) -> 
                    LocalDateTime.from(temporal)
            );
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    public static boolean validDate(String dateString) {
        return DateUtil.parse(dateString) != null;
    }
}
