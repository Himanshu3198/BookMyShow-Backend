package org.BookMyShow.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Theater")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theater_name")
    @JsonProperty(value = "theaterName")
    private String theaterName;

    @Column(name = "location")
    private String location;

    @One
}
