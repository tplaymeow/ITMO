import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public abstract class Organism {
    private String name;
    private final Place bornAt;
    private Place location;
    private Characteristic characteristic;
    private final BiologyKingdom kingdom;
    private final Organ[] organs;

    public Organism(String name, Place bornAt,
                    Place location, Characteristic characteristic,
                    BiologyKingdom kingdom, Organ[] organs) {
        this.name = name;
        this.bornAt = bornAt;
        this.location = location;
        this.characteristic = characteristic;
        this.kingdom = kingdom;
        this.organs = organs;
        System.out.println("Организм " + name + " родился в: " + bornAt+", проживает в: " + location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organism organism = (Organism) o;
        return Objects.equals(getName(), organism.getName()) &&
                Objects.equals(getBornAt(), organism.getBornAt()) &&
                Objects.equals(getLocation(), organism.getLocation()) &&
                Objects.equals(getCharacteristic(), organism.getCharacteristic()) &&
                getKingdom() == organism.getKingdom() &&
                Arrays.equals(getOrgans(), organism.getOrgans());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getBornAt(), getLocation(), getCharacteristic(), getKingdom());
        result = 31 * result + Arrays.hashCode(getOrgans());
        return result;
    }

    @Override
    public String toString() {
        return "Organism{" +
                "name='" + name + '\'' +
                ", bornAt=" + bornAt +
                ", location=" + location +
                ", characteristic=" + characteristic +
                ", kingdom=" + kingdom +
                ", organs=" + Arrays.toString(organs) +
                '}';
    }

    public void leaveMark(Date date) {
        location.addMark(new Mark(this, date));
        System.out.println(name + " оставил след на " + this.location.toString());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public void setLocation(Place location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Place getBornAt() {
        return bornAt;
    }

    public Place getLocation() {
        return location;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public BiologyKingdom getKingdom() {
        return kingdom;
    }

    public Organ[] getOrgans() {
        return organs;
    }
}
