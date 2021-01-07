import java.util.ArrayList;
import java.util.Objects;

public class Place {
    private String name;
    private final CosmosObject cosmosObject;
    private final double xCoordinate;
    private final double yCoordinate;
    private ArrayList<Mark> marks;

    public Place(String name, CosmosObject cosmosObject, double xCoordinate, double yCoordinate) {
        this.name = name;
        this.cosmosObject = cosmosObject;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.marks = new ArrayList<Mark>();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Double.compare(place.xCoordinate, xCoordinate) == 0 && Double.compare(place.yCoordinate, yCoordinate) == 0 && cosmosObject == place.cosmosObject && Objects.equals(marks, place.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cosmosObject, xCoordinate, yCoordinate, marks);
    }

    public void addMark(Mark mark){
        marks.add(mark);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Mark> getMarks() {
        return marks;
    }

    public CosmosObject getCosmosObject() {
        return cosmosObject;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }
}
