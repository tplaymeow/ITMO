import java.util.Objects;

public class CosmosObject {
    private final String name;
    private long age;
    private boolean isLives;

    public CosmosObject(String name, long age, boolean isLives) {
        this.name = name;
        this.age = age;
        this.isLives = isLives;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CosmosObject that = (CosmosObject) o;
        return age == that.age && isLives == that.isLives && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, isLives);
    }

    public String getName() {
        return name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public boolean isLives() {
        return isLives;
    }

    public void setLives(boolean lives) {
        isLives = lives;
    }
}