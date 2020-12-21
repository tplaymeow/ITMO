import java.util.Objects;

public abstract class Organ {
    private String name;
    private OrganType type;
    private final double fullPrice;
    private double condition; // ot 0 do 1
    private Characteristic characteristic;

    public Organ(String name, OrganType type, double fullPrice, double condition) {
        this.name = name;
        this.type = type;
        this.fullPrice = fullPrice;
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organ organ = (Organ) o;
        return Double.compare(organ.fullPrice, fullPrice) == 0 &&
                Double.compare(organ.getCondition(), getCondition()) == 0 &&
                Objects.equals(getName(), organ.getName()) &&
                getType() == organ.getType() &&
                Objects.equals(characteristic, organ.characteristic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), fullPrice, getCondition(), characteristic);
    }

    @Override
    public String toString() {
        return "Organ: " +
                "name='" + name +
                " Price=" + price();
    }

    double price() {
        return fullPrice * condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganType getType() {
        return type;
    }

    public void setType(OrganType type) {
        this.type = type;
    }

    public double getCondition() {
        return condition;
    }

    public void setCondition(double condition) {
        if (condition >= 0 && condition < 1) {
            this.condition = condition;
        }
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }
}
