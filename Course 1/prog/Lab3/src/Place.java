import java.util.Objects;

public class Place {
    private final Planet planet;
    private final String country;
    private Mark mark;

    Place(Planet planet, String country) {
        this.planet = planet;
        this.country = country;
    }

    public Planet getPlanet() {
        return planet;
    }

    public String getCountry() {
        return country;
    }

    public Mark getMark()  {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return planet == place.planet &&
                Objects.equals(country, place.country) &&
                Objects.equals(mark, place.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planet, country, mark);
    }

    @Override
    public String toString() {
        return "Place{" +
                "planet=" + planet +
                ", country='" + country + '\'' +
                ", mark=" + mark +
                '}';
    }
}

enum Planet {
    EARTH,
    MARS,
    A_FAR_PLANET
}
