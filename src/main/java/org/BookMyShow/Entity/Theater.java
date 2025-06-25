package org.BookMyShow.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theater")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theater_name", nullable = false)
    private String theaterName;

    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MovieShow> showList = new ArrayList<>();

    public Theater() {
    }

    public void addShow(MovieShow show) {
        showList.add(show);
        show.setTheater(this);
    }

    public void removeShow(MovieShow show) {
        showList.remove(show);
        show.setTheater(null);
    }

    public Long getId() {
        return id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public String getLocation() {
        return location;
    }

    public List<MovieShow> getShowList() {
        return showList;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
