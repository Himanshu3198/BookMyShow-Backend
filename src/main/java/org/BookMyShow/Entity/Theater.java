package org.BookMyShow.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    List<Show> showList = new ArrayList<>();

    public void addShow(Show show){
        showList.add(show);
        show.setTheater(this);
    }

    public void removeShow(Show show){
        showList.remove(show);
        show.setTheater(this);
    }
}
