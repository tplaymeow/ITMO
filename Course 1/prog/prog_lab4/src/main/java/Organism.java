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
    private final BiologicalAffiliation[] kingdom;
    private ArrayList<Organ> organs;
    boolean isWet;

    public Organism(String name, Place bornAt, Characteristic externalCharacteristic, int complexity, BiologicalAffiliation[] kingdom, Organ[] organs) {
        this.name = name;
        this.bornAt = bornAt;
        this.location = bornAt;
        this.externalCharacteristic = externalCharacteristic;
        this.complexity = complexity;
        this.kingdom = kingdom;
        this.organs = new ArrayList<Organ>(Arrays.asList(organs));
        System.out.println("Организм " + name + " родился в: " + bornAt + ".");
        isWet = false;
    }

    public Organism(String name, Place bornAt, Characteristic externalCharacteristic, int complexity, BiologicalAffiliation kingdom, Organ[] organs) {
        this.name = name;
        this.bornAt = bornAt;
        this.location = bornAt;
        this.externalCharacteristic = externalCharacteristic;
        this.complexity = complexity;
        this.kingdom = new BiologicalAffiliation[]{kingdom};
        this.organs = new ArrayList<Organ>(Arrays.asList(organs));
        System.out.println("Организм " + name + " родился в: " + bornAt + ".");
        isWet=false;
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

    public void leaveMark() {
        location.addMark(new Mark(this));
        if (location.getCosmosObject().getAge() < 10000000L) System.out.println(name + " оставил след на " + location.getName() + " (" + location.getCosmosObject() + "ещё молодая).");
        else System.out.println(name + " оставил след на " + location.getName() + " (" + location.getCosmosObject() + "уже не молодая).");
    }

    public abstract void moveTo(Place location); //Разные организмы могут перемещатся на разные дистанции

    public void addOrgan(Organ organ) {
        this.organs.add(organ);
        System.out.println("У " + this.getName() + " выросло(-и) " + organ.getName() + ".");
    }

    public void removeOrgan(Organ organ) throws MandatoryOrganException {
        if (organ.isMandatory()) throw new MandatoryOrganException();
        organs.remove(organ);
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

    public BiologicalAffiliation[] getKingdom() {
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

    public boolean isWet() {
        return isWet;
    }

    public void setWet(boolean wet) {
        isWet = wet;
    }
}
