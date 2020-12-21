import java.util.Date;
import java.util.Objects;

public class Mark {
    private final Organism author;
    private final Date date;

    public Mark(Organism author, Date date) {
        this.author = author;
        this.date = date;
    }


    @Override
    public String toString() {
        return "След оставлен " + author.getName() + " " + date.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return Objects.equals(getAuthor(), mark.getAuthor()) &&
                Objects.equals(getDate(), mark.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getDate());
    }

    public Organism getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }
}
