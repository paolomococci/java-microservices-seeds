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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import local.example.seed.util.DateUtil;

public class Seed 
        implements Serializable {
    
    private LongProperty id;
    private StringProperty name;
    private DoubleProperty percentage;
    private ObjectProperty<LocalDateTime> created;

    public Seed() {
        this.id = null;
        this.name = null;
        this.percentage = null;
        this.created = null;
    }

    public Seed(
            LongProperty id
    ) {
        this.id = id;
        this.name = null;
        this.percentage = null;
        this.created = null;
    }

    public Seed(
            LongProperty id, 
            StringProperty name
    ) {
        this.id = id;
        this.name = name;
        this.percentage = null;
        this.created = null;
    }

    public Seed(
            Long id, 
            String name, 
            Double percentage
    ) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.percentage = new SimpleDoubleProperty(percentage);
        this.created = null;
    }

    public Seed(
            LongProperty id, 
            StringProperty name, 
            DoubleProperty percentage
    ) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.created = null;
    }

    public Seed(
            LongProperty id, 
            StringProperty name,
            DoubleProperty percentage, 
            ObjectProperty<LocalDateTime> created) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.created = created;
    }

    public LongProperty getId() {
        return id;
    }
    
    public String getIdValue() {
        if (this.id != null) {
            return this.id.asString().get();
        }
        return "";
    }

    public void setId(LongProperty id) {
        this.id = id;
    }

    public StringProperty getName() {
        return name;
    }
    
    public String getNameValue() {
        if (this.name != null) {
            return this.name.get();
        }
        return "";
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public DoubleProperty getPercentage() {
        return percentage;
    }
    
    public String getPercentageValue() {
        if (this.percentage != null) {
            return this.percentage.asString().get();
        }
        return "";
    }

    public void setPercentage(DoubleProperty percentage) {
        this.percentage = percentage;
    }

    public ObjectProperty<LocalDateTime> getCreated() {
        return created;
    }
    
    public String getCreatedValue() {
        if (this.created != null) {
            return DateUtil.format(this.created.get());
        }
        return "";
    }

    public void setCreated(ObjectProperty<LocalDateTime> created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Seed{" + "id=" + id + 
                ", name=" + name + 
                ", percentage=" + percentage + 
                ", created=" + created + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.percentage);
        hash = 37 * hash + Objects.hashCode(this.created);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Seed other = (Seed) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.percentage, other.percentage)) {
            return false;
        }
        return Objects.equals(this.created, other.created);
    }
}
