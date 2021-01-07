public class Evolution {
    public void getUp(Organism organism) {
        organism.addOrgan(new Wings("Крылья", OrganType.IN_USAGE, 1000, 1));
        System.out.println("Эволюция оторвала " + organism.getName() + " от земли.");
    }
}
