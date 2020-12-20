import java.util.Objects;

public class Mark {
    private final Organism author;
    private final Place place;

    Mark(Organism author, Place place) {
        this.author = author;
        this.place = place;
    }

    public Organism getAuthor() {
        return author;
    }

    public Place getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return Objects.equals(author, mark.author) &&
                Objects.equals(place, mark.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, place);
    }
}
