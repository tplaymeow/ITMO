import java.util.Objects;

public class Heart extends Organ {
    private int bpm;

    public Heart(String name, OrganType type, double fullPrice, double condition) {
        super(name, type, fullPrice, condition);
        this.setCharacteristic(new Characteristic(Size.LARGE, 200, true, Shape.OTHER_NON_SYMMETRICAL));
        bpm = 80;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Heart heart = (Heart) o;
        return bpm == heart.bpm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bpm);
    }

    @Override
    public String toString() {
        return "Heart{" +
                "bpm=" + bpm +
                '}';
    }
}