package model;

import java.time.LocalDateTime;

public class Flat {
    private int id;
    private String houseName;
    private Coordinates coordinates;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public Flat(int id, String houseName, Coordinates coordinates) {
        this.id = id;
        this.houseName = houseName;
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", houseName='" + houseName + '\'' +
                ", coordinates=" + coordinates +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
