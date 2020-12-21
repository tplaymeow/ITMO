public class Human extends Organism {
    public Human(String name, Place bornAt, Place location, Characteristic characteristic, BiologyKingdom kingdom, Organ[] organs) {
        super(name, bornAt, location, characteristic, kingdom, organs);
    }

    public void dreamAbout() {
        System.out.println(this.getName() + " думает о:");
    }
}
