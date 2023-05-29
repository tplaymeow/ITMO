package model;

import annotations.Id;
import annotations.Table;
import annotations.converterAnnotations.NotWrite;
import annotations.relationshipType.Element;

import java.io.Serializable;

@Table("Locations")
public class Location implements Serializable {
    @Element
    private Float x;
    @Element
    private Long y;
    @Element
    private long z;
    @Element
    private String name;
    @Id
    @NotWrite
    private int id;

    public Location() {
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}
