import java.util.Objects;

public class OrganismPrinter {
    private Organism organism;

    public OrganismPrinter(Organism organism) {
        this.organism = organism;
    }

    public void printBiologyKingdom() {
        if (organism.getKingdom().length > 1) {
            System.out.println("Причислить " + organism.getName() + " куда-либо невозможно.");
            for (BiologicalAffiliation k : organism.getKingdom()) {
                System.out.println("\t-" + organism.getName() + " принадлежит " + k + ".");
            }
        } else {
            System.out.println(organism.getName() + " обладает признаками " + organism.getKingdom()[0] + ".");
        }
    }

    public void looksLike(Organism anotherOrganism) {
        if (organism.getExternalCharacteristic().equals(anotherOrganism.getExternalCharacteristic())) {
            System.out.println("Внешне " + organism.getName() + " выглядит как " + anotherOrganism.getName() + ".");
        } else {
            System.out.println("Внешне " + organism.getName() + " не выглядит как " + anotherOrganism.getName() + ".");
        }
    }

    public void comparison(Organism anotherOrganism) {
        if (organism.getComplexity() > anotherOrganism.getComplexity()) {
            System.out.println(organism.getName() + " более высокий организм, в сравнении с " + anotherOrganism.getName() + ".");
        } else if (organism.getComplexity() == anotherOrganism.getComplexity()) {
            System.out.println(organism.getName() + " и " + anotherOrganism.getName() + " одинаковы по уровню развития.");
        } else {
            System.out.println(organism.getName() + " менее высокий организм, в сравнении с " + anotherOrganism.getName() + ".");
        }
    }

    public void isWater() {
        int count = 0;
        if (organism.getExternalCharacteristic().getShape() == Shape.OTHER_SYMMETRICAL) count++;
        if (organism.getExternalCharacteristic().isVisible()) count++;
        if (organism.getComplexity() <= 5) count++;

        if (count == 3) {
            System.out.println(organism.getName() + " является морским животным. Об этом говорят:");
            System.out.println("\t-" + organism.getExternalCharacteristic().getShape());
            System.out.println("\t-" + "Видимость");
            System.out.println("\t-" + "Невысокий уровень развития");
        } else if ((count <= 2) && (count > 0)) {
            System.out.println(organism.getName() + " имеет морское происхождение. Об этом говорят:");
            if (organism.getExternalCharacteristic().getShape() == Shape.OTHER_SYMMETRICAL) System.out.println("\t-" + organism.getExternalCharacteristic().getShape());
            if (organism.getExternalCharacteristic().isVisible()) System.out.println("\t-" + "Видимость");
            if (organism.getComplexity() <= 5) System.out.println("\t-" + "Невысокий уровень развития");
            System.out.println("Однако далее " + organism.getName() + " развивался(-ась) в других направлениях.");
        } else {
            System.out.println(organism.getName() + " не имеет признаков морского животного.");
        }
    }

    public void mostPopularOrganType() {
        int rud = 0;
        int atr = 0;
        int use = 0;

        for (Organ organ:
             organism.getOrgans()) {
            if (organ.getType() == OrganType.RUDIMENT) rud++;
            else if (organ.getType() == OrganType.ATROPH) atr++;
            else if (organ.getType() == OrganType.IN_USAGE) use ++;
        }
        System.out.print("У " + organism.getName() + " преобладают ");
        if (rud >= atr && rud >= use) System.out.print(OrganType.RUDIMENT + " ");
        if (atr >= rud && atr >= use) System.out.print(OrganType.ATROPH + " ");
        if (use >= atr && use >= rud) System.out.print(OrganType.RUDIMENT + " ");
        System.out.println("органы.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganismPrinter printer = (OrganismPrinter) o;
        return Objects.equals(organism, printer.organism);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organism);
    }

    @Override
    public String toString() {
        return "Принтер: " + organism.getName();
    }
}
