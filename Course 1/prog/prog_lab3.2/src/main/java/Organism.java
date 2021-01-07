import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public abstract class Organism {
    private String name;
    private final Place bornAt;
    private Place location;
    private Characteristic externalCharacteristic;
    private int complexity;
    private final BiologyKingdom[] kingdom;
    private ArrayList<Organ> organs;

    public Organism(String name, Place bornAt, Characteristic externalCharacteristic, int complexity, BiologyKingdom[] kingdom, Organ[] organs) {
        this.name = name;
        this.bornAt = bornAt;
        this.location = bornAt;
        this.externalCharacteristic = externalCharacteristic;
        this.complexity = complexity;
        this.kingdom = kingdom;
        this.organs = new ArrayList<Organ>(Arrays.asList(organs));
        System.out.println("Организм " + name + " родился в: " + bornAt + ".");
    }

    public Organism(String name, Place bornAt, Characteristic externalCharacteristic, int complexity, BiologyKingdom kingdom, Organ[] organs) {
        this.name = name;
        this.bornAt = bornAt;
        this.location = bornAt;
        this.externalCharacteristic = externalCharacteristic;
        this.complexity = complexity;
        this.kingdom = new BiologyKingdom[]{kingdom};
        this.organs = new ArrayList<Organ>(Arrays.asList(organs));
        System.out.println("Организм " + name + " родился в: " + bornAt + ".");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organism organism = (Organism) o;
        return complexity == organism.complexity && Objects.equals(name, organism.name) && Objects.equals(bornAt, organism.bornAt) && Objects.equals(location, organism.location) && Objects.equals(externalCharacteristic, organism.externalCharacteristic) && Arrays.equals(kingdom, organism.kingdom) && Objects.equals(organs, organism.organs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, bornAt, location, externalCharacteristic, complexity, organs);
        result = 31 * result + Arrays.hashCode(kingdom);
        return result;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ". Место рождения: " + bornAt + ".";
    }

    public void leaveMark(long date) {
        long deltaAge = location.getCosmosObject().getAge() - 2020 - ((date > 0) ? date : -1 * date);
        if (deltaAge < 500000000 && deltaAge > 0) {
            location.addMark(new Mark(this, date));
            System.out.println(name + " оставил след на " + location + " (" + location.getCosmosObject() + " еще молодая).");
        } else if (deltaAge > 0) {
            location.addMark(new Mark(this, date));
            System.out.println(name + " оставил след на " + location + " (" + location.getCosmosObject() + " уже не молодая).");
        }
    }

    public abstract void moveTo(Place location); //Разные организмы могут перемещатся на разные дистанции

    public void addOrgan(Organ organ) {
        this.organs.add(organ);
        System.out.println("У " + this.getName() + " выросло(-и) " + organ.getName() + ".");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place getBornAt() {
        return bornAt;
    }

    public Place getLocation() {
        return location;
    }

    public void setLocation(Place location) {
        this.location = location;
    }

    public Characteristic getExternalCharacteristic() {
        return externalCharacteristic;
    }

    public void setExternalCharacteristic(Characteristic externalCharacteristic) {
        this.externalCharacteristic = externalCharacteristic;
    }

    public BiologyKingdom[] getKingdom() {
        return kingdom;
    }

    public ArrayList<Organ> getOrgans() {
        return organs;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }
}
