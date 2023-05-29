import java.util.Objects;

public class Wings extends Organ {
    private boolean hasFeathers;

    public Wings(String name, OrganType type, double fullPrice, double condition) {
        super(name, type, fullPrice, condition);
        this.setCharacteristic(new Characteristic(Size.LARGE, 200, true, Shape.OTHER_NON_SYMMETRICAL));
        hasFeathers = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Wings wings = (Wings) o;
        return hasFeathers == wings.hasFeathers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasFeathers);
    }

    @Override
    public String toString() {
        return "Wings{" +
                "hasFeathers=" + hasFeathers +
                '}';
    }
}
