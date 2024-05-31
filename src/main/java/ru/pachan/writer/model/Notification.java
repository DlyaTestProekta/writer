package ru.pachan.writer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notifications")
public class Notification {

    @Column(nullable = false, unique = true)
    private long personId;

    @Column(nullable = false)
    private long count;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
}
