import java.util.Objects;

public abstract class Organ {
    private final String name;
    private OrganType type;
    private final double fullPrice;
    private double condition; // from 0 to 1
    private Characteristic characteristic;
    boolean isMandatory;

    public Organ(String name, OrganType type, double fullPrice, double condition) {
        this.name = name;
        this.type = type;
        this.fullPrice = fullPrice;
        this.condition = condition;
        isMandatory = true;
    }

    @Override
    public String toString() {
        return name + ". Качество: " + condition + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organ organ = (Organ) o;
        return Double.compare(organ.fullPrice, fullPrice) == 0 && Double.compare(organ.condition, condition) == 0 && Objects.equals(name, organ.name) && type == organ.type && Objects.equals(characteristic, organ.characteristic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, fullPrice, condition, characteristic);
    }

    public String getName() {
        return name;
    }

    public OrganType getType() {
        return type;
    }

    public void setType(OrganType type) {
        this.type = type;
    }

    public double getPrice() {
        return fullPrice * condition;
    }

    public double getCondition() {
        return condition;
    }

    public void setCondition(double condition) {
        if (condition >= 0 && condition <= 1) this.condition = condition;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }
}
