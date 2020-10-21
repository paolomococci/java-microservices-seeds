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

package local.example.seed.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import local.example.seed.model.util.Link;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@ToString
@NoArgsConstructor
public class Invoice {

    public Invoice(
            String code,
            LocalDate date,
            Double total,
            String customerId,
            Link links
    ) {
        this.code = code;
        this.date = Date.valueOf(date);
        this.total = total;
        this.customerId = customerId;
        this._links = links;
    }

    @Getter
    @Setter
    private String code;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @Getter
    @Setter
    private Double total;

    @Getter
    @Setter
    private String customerId;

    @Getter
    @Setter
    private Link _links;

    public LocalDate getDate() {
        return date.toLocalDate();
    }

    public void setDate(LocalDate localDate) {
        this.date = Date.valueOf(localDate);
    }
}
