public class OrganismPrinter {
    private Organism organism;

    public OrganismPrinter(Organism organism) {
        this.organism = organism;
    }

    public void printBiologyKingdom() {
        if (organism.getKingdom().length > 1) {
            System.out.println("Причислить " + organism.getName() + " куда-либо невозможно.");
            for (BiologyKingdom k : organism.getKingdom()) {
                System.out.println(organism.getName() + " относится к царству " + k + ".");
            }
        } else {
            System.out.println(organism.getName() + " относится к царству " + organism.getKingdom()[0] + ".");
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


}
