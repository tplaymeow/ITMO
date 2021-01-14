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
        return Double.compare(place.xCoordinate, xCoordinate) == 0 && Double.compare(place.yCoordinate, yCoordinate) == 0 && Objects.equals(name, place.name) && Objects.equals(cosmosObject, place.cosmosObject) && Objects.equals(marks, place.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cosmosObject, xCoordinate, yCoordinate, marks);
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

    public class Weather {
        double temperature;

        public Weather(double temperature) {
            this.temperature = temperature;
        }

        public void applySunEffect(Organism organism) {
            class SunRay {
                int brightness;

                public SunRay(int brightness) {
                    this.brightness = brightness;
                }

                public void applySunRayEffect(Organism organism) {
                    organism.setWet(true);
                    System.out.println(organism.getName() + " несколько обмяк под лучами");

                    int count = 3;
                    for (int i = 0; i < count; i++) {
                        organism.getOrgans().get(i).getCharacteristic().setShape(Shape.STRAIGHT);
                        System.out.println("\t-" + organism.getOrgans().get(i).getName() + " выпрямился под лучами");
                    }
                }

                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (o == null || getClass() != o.getClass()) return false;
                    SunRay sunRay = (SunRay) o;
                    return brightness == sunRay.brightness;
                }

                @Override
                public int hashCode() {
                    return Objects.hash(brightness);
                }

                @Override
                public String toString() {
                    return "SunRay{" +
                            "brightness=" + brightness +
                            '}';
                }
            }

            SunRay ray = new SunRay(10);
            ray.applySunRayEffect(organism);
        }
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
