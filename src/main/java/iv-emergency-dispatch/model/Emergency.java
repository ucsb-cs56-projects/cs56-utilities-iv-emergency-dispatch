package ivemergencydispatch.model;

import java.util.Date;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="emergencies")
public class Emergency {
    @Id
    private Long id;

    @NotBlank
    @Indexed(unique=true)
    private String description;

    @NotBlank
    private String category;

    @NotBlank
    private String address;

    @NotBlank
    private Date time;

    public Emergency() {
    }

    public Emergency(Long id, String description, String category, String address, Date time) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.address = address;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format(
                "Emergency[id=%s, description='%s', category='%s', address='%s', time='%s']",
                id, description, category, address, time);
    }
}
