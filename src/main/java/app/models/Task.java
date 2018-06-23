package app.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    @Column(nullable = false, length = 20)
    private String status = "created";

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp = new Date();

    public UUID getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        return isoFormat.format(timestamp);
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Task() {
    }

}
