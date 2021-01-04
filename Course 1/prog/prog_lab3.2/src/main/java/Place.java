import java.util.ArrayList;
import java.util.Objects;

public class Place {
    private final CosmosObject cosmosObject;
    private final double xCoordinate;
    private final double yCoordinate;
    private ArrayList<Mark> marks;

    public Place(CosmosObject cosmosObject, double xCoordinate, double yCoordinate) {
        this.cosmosObject = cosmosObject;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.marks = new ArrayList<Mark>();
    }

    @Override
    public String toString() {
        return "Планета: " + cosmosObject + ", X: " + xCoordinate + ", Y: " + yCoordinate;
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

    public ArrayList<Mark> getMarks() {
        return marks;
    }
}
