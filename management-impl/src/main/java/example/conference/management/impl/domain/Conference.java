package example.conference.management.impl.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Conference {

    @Id
    private String id;

    @Basic(optional = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SeatType> seatTypes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<SeatType> getSeatTypes() {
        return seatTypes;
    }

    public void setSeatTypes(Set<SeatType> seatTypes) {
        this.seatTypes = seatTypes;
    }

    protected Conference() {
    }

}
