import java.util.ArrayList;
import java.util.Objects;

public class Place {
    private final CosmosObject cosmosObject;
    private final double longitude;
    private final double latitude;
    ArrayList<Mark> marks;

    public Place(CosmosObject cosmosObject, double longitude, double latitude) {
        this.cosmosObject = cosmosObject;
        this.longitude = longitude;
        this.latitude = latitude;
        this.marks = new ArrayList<Mark>();
    }

    @Override
    public String toString() {
        return "Планета: " + cosmosObject + ", X: " + latitude + ", Y: " + longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Double.compare(place.longitude, longitude) == 0 &&
                Double.compare(place.latitude, latitude) == 0 &&
                cosmosObject == place.cosmosObject &&
                Objects.equals(getMarks(), place.getMarks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cosmosObject, longitude, latitude, getMarks());
    }

    public void addMark(Mark mark) {
        marks.add(mark);
    }

    public ArrayList<Mark> getMarks() {
        return marks;
    }
}
