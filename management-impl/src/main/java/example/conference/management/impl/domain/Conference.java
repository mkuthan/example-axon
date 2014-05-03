package example.conference.management.impl.domain;

import java.util.ArrayList;
import java.util.List;

public class Conference {

    private String id;

    private String name;

    private List<SeatType> seatTypes = new ArrayList<>();

    public Conference(String id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public List<SeatType> getSeatTypes() {
        return seatTypes;
    }

    public void setSeatTypes(List<SeatType> seatTypes) {
        this.seatTypes = seatTypes;
    }

    public void addSeatType(SeatType seatType) {
        seatTypes.add(seatType);
    }

    protected Conference() {
    }

}
