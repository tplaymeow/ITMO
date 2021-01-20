import java.util.Objects;

public class BiologicalAffiliation {
    private BiologyKingdom biologyKingdom;
    private double biologicalAffiliationLevel;

    public BiologicalAffiliation(BiologyKingdom biologyKingdom, double biologicalAffiliationLevel) {
        this.biologyKingdom = biologyKingdom;
        this.biologicalAffiliationLevel = biologicalAffiliationLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiologicalAffiliation that = (BiologicalAffiliation) o;
        return Double.compare(that.biologicalAffiliationLevel, biologicalAffiliationLevel) == 0 && biologyKingdom == that.biologyKingdom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(biologyKingdom, biologicalAffiliationLevel);
    }

    @Override
    public String toString() {
        return biologyKingdom + " на " + biologicalAffiliationLevel;
    }
}

enum BiologyKingdom {
    ANIMALIA("Животные"),
    PLANTAE("Растения"),
    FUNGI("Грибы"),
    ARCHAEA("Вирусы"),
    BACTERIA("Бактерии");

    private final String name;

    BiologyKingdom(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
