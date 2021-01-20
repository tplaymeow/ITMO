import java.util.Objects;

public class Nerves extends Organ {
    private int countOfNerves;

    public Nerves(String name, OrganType type, double fullPrice, double condition) {
        super(name, type, fullPrice, condition);
        this.setCharacteristic(new Characteristic(Size.LARGE, 200, true, Shape.OTHER_NON_SYMMETRICAL));
        countOfNerves = 99;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Nerves nerves = (Nerves) o;
        return countOfNerves == nerves.countOfNerves;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countOfNerves);
    }

    @Override
    public String toString() {
        return "Nerves{" +
                "countOfNerves=" + countOfNerves +
                '}';
    }

    public int getCountOfNerves() {
        return countOfNerves;
    }

    public void setCountOfNerves(int countOfNerves) {
        this.countOfNerves = countOfNerves;
    }
}
