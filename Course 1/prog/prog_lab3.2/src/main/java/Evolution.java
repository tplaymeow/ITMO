public class Evolution {
    public static void getUp(Organism organism) {
        organism.addOrgan(new Wings("Крылья", OrganType.IN_USAGE, 1000, 1));
        System.out.println("Эволюция оторвала " + organism.getName() + " от земли.");
    }

    public static void regress(Organism organism) {
        for (Organ organ:
                organism.getOrgans()) {
            organ.getCharacteristic().setShape(Shape.SIMPLE_SHAPE);
            System.out.println("\t -Размер " + organ.getName() + " у " + organism.getName() + " уменьшился.");
        }

        organism.getExternalCharacteristic().setShape(Shape.SIMPLE_SHAPE);
        if (organism.getComplexity() > 0) organism.setComplexity(organism.getComplexity() - 1);
        System.out.println("У " + organism.getName() + " упростилась форма и строение.");

        System.out.println("Регрессия имела место у " + organism.getName() + ".");
    }


}
