package ivemergencydispatch.models;

import java.util.Date;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="locations")
public class Location {
    @Id
    private String id;

    @NotBlank
    @Indexed(unique=true)
    private String description;

    @NotBlank
    private String category;

    @NotBlank
    private String address;

    @NotBlank
    private String time;

    public Location() {
        super();
    }

    public Location(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format(
                "Location[id=%s, description='%s', category='%s', address='%s', time='%s']",
                id, description, category, address, time);
    }
}
