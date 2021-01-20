import java.util.Objects;

public class Mark {
    private final Organism author;

    public Mark(Organism author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "След был оставлен " + author.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return Objects.equals(author, mark.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author);
    }

    public Organism getAuthor() {
        return author;
    }
}
