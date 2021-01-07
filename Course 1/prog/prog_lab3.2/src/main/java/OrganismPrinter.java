public class OrganismPrinter {
    private Organism organism;

    public OrganismPrinter(Organism organism) {
        this.organism = organism;
    }

    public void printBiologyKingdom() {
        if (organism.getKingdom().length > 1) {
            System.out.println("Причислить " + organism.getName() + " куда-либо невозможно.");
            for (BiologyKingdom k : organism.getKingdom()) {
                System.out.println("\t" + organism.getName() + " обладает признаками " + k + ".");
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
            System.out.println("\t" + organism.getExternalCharacteristic().getShape());
            System.out.println("\t" + "Видимость");
            System.out.println("\t" + "Невысокий уровень развития");
        } else if ((count <= 2) && (count > 0)) {
            System.out.println(organism.getName() + " имеет морское происхождение. Об этом говорят:");
            if (organism.getExternalCharacteristic().getShape() == Shape.OTHER_SYMMETRICAL) System.out.println("\t" + organism.getExternalCharacteristic().getShape());
            if (organism.getExternalCharacteristic().isVisible()) System.out.println("\t" + "Видимость");
            if (organism.getComplexity() <= 5) System.out.println("\t" + "Невысокий уровень развития");
            System.out.println("Однако далее " + organism.getName() + " развивался(-ась) в других направлениях.");
        } else {
            System.out.println(organism.getName() + " не имеет признаков морского животного.");
        }
    }
}
