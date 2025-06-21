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
    List<MovieShow> showList = new ArrayList<>();

    public void addShow(MovieShow show){
        showList.add(show);
        show.setTheater(this);
    }

    public void removeShow(MovieShow show){
        showList.remove(show);
        show.setTheater(this);
    }
}
